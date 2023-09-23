package com.upload.file.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	private FileLoader fileLoader;
	
	@Value("${spring.datasource.path}")
    private String folderPath;

    @Autowired
    public void FileController(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

	@Scheduled(fixedRate = (1000 * 60))
	public void reportCurrentTime() {
	    File folder = new File(folderPath);
	    
	    if (folder.exists() && folder.isDirectory()) {
	    	log.info("Searching for files to process");
            File[] files = folder.listFiles((dir, name) -> !name.endsWith(".p"));

            if (files != null && files.length > 0) {
            	log.info("Starting file processing");
            	for (File upload_file : files) {
            		log.info(upload_file.getName());
                    fileLoader.loadFileToDatabase(upload_file.toPath().toString(), upload_file.getName());
                    try {
                        // changing extension to not process again
                        Files.move(
                        		Paths.get(upload_file.toPath().toString()),
                        		Paths.get(upload_file.toPath().toString() + ".p") 
                        	);
                    } catch (IOException e) {
                    	log.error("Error renaming file: " + e.getMessage());
                    }
                }
            	log.info("Finish file processing");
            }
            else {
            	log.info("No files found to process");
            }
	    }
	}
}

