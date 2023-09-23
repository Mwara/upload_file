package com.upload.file.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileLoader {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void loadFileToDatabase(String filePath, String Filename) {
    	
    	int successCount = 0;
    	int failCount = 0;

    	LoadDataBase loaddb = new LoadDataBase(jdbcTemplate);
    	
    	// create log file
    	int id = insertCdrLog(Filename, date_now());
    	
    	// reading file and insert in database
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	try {
            		if(loaddb.load_in_database(line, id)) {
            			successCount += 1;
            		}
            		else {
            			failCount += 1;
            		}
            		
            	} catch (Exception e) {
            		System.out.println(e.getMessage());
            		failCount += 1;
            	}
            }
            //System.out.println("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File upload failed");
        }
        
        // updating log file
        jdbcTemplate.update("UPDATE cdr_logs set "
        		+ "upload_finish_date = ?::timestamp,"
        		+ "success_register_number = ?,"
        		+ "fail_register_number= ?,"
        		+ "upload_register_total= ?"
        		+ "WHERE id = ?",
        		date_now(),
        		successCount,
        		failCount,
        		(successCount + failCount),
        		id);
    }
    
    public int insertCdrLog(String filename, String start_date) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cdr_logs")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("file_name", filename);
        parameters.put("upload_start_date", start_date);

        Number generatedId = jdbcInsert.executeAndReturnKey(parameters);

        return (generatedId != null) ? (int) generatedId : null;
    }
    
    public String date_now()
    {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
 
}

