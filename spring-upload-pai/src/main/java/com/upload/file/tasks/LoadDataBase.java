package com.upload.file.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoadDataBase {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
    public LoadDataBase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public Boolean load_in_database(String line, int fileId) {
		try {
            String[] values = line.split("\\|");

            // parsing data
            Timestamp recordDate = Timestamp.valueOf(values[0].replace(",", "."));
            Integer lSpc = values[1].isEmpty() ? null : Integer.parseInt(values[1]);
            Integer lSsn = values[2].isEmpty() ? null : Integer.parseInt(values[2]);
            Integer lRi = values[3].isEmpty() ? null : Integer.parseInt(values[3]);
            Integer lGtI = values[4].isEmpty() ? null : Integer.parseInt(values[4]);
            String lGtDigits = values[5];
            Integer rSpc = values[6].isEmpty() ? null : Integer.parseInt(values[6]);
            Integer rSsn = values[7].isEmpty() ? null : Integer.parseInt(values[7]);
            Integer rRi = values[8].isEmpty() ? null : Integer.parseInt(values[8]);
            Integer rGtI = values[9].isEmpty() ? null : Integer.parseInt(values[9]);
            String rGtDigits = values[10];
            String serviceCode = values[11];
            Integer orNature = values[12].isEmpty() ? null : Integer.parseInt(values[12]);
            Integer orPlan = values[13].isEmpty() ? null : Integer.parseInt(values[13]);
            String orDigits = values[14];
            Integer deNature = values[15].isEmpty() ? null : Integer.parseInt(values[15]);
            Integer dePlan = values[16].isEmpty() ? null : Integer.parseInt(values[16]);
            String deDigits = values[17];
            Integer isdnNature = values[18].isEmpty() ? null : Integer.parseInt(values[18]);
            Integer isdnPlan = values[19].isEmpty() ? null : Integer.parseInt(values[19]);
            String msisdn = values[20];
            Integer vlrNature = values[21].isEmpty() ? null : Integer.parseInt(values[21]);
            Integer vlrPlan = values[22].isEmpty() ? null : Integer.parseInt(values[22]);
            String vlrDigits = values[23];
            String imsi = values[24];
            String status = values[25];
            String type = values[26];
            Timestamp tstamp = Timestamp.valueOf(values[27]);
            long localDialogId = Long.parseLong(values[28]);
            long remoteDialogId = Long.parseLong(values[29]);
            long dialogDuration = Long.parseLong(values[30]);
            String ussdString = values[31];
            String id = values[32];
            
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            		.withTableName("call_detail_records");
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("RECORD_DATE", recordDate);
            parameters.put("L_SPC", lSpc);
            parameters.put("L_SSN", lSsn);
            parameters.put("L_RI", lRi);
            parameters.put("L_GT_I", lGtI);
            parameters.put("L_GT_DIGITS", lGtDigits);
            parameters.put("R_SPC", rSpc);
            parameters.put("R_SSN", rSsn);
            parameters.put("R_RI", rRi);
            parameters.put("R_GT_I", rGtI);
            parameters.put("R_GT_DIGITS", rGtDigits);
            parameters.put("SERVICE_CODE", serviceCode);
            parameters.put("OR_NATURE", orNature);
            parameters.put("OR_PLAN", orPlan);
            parameters.put("OR_DIGITS", orDigits);
            parameters.put("DE_NATURE", deNature);
            parameters.put("DE_PLAN", dePlan);
            parameters.put("DE_DIGITS", deDigits);
            parameters.put("ISDN_NATURE", isdnNature);
            parameters.put("ISDN_PLAN", isdnPlan);
            parameters.put("MSISDN", msisdn);
            parameters.put("VLR_NATURE", vlrNature);
            parameters.put("VLR_PLAN", vlrPlan);
            parameters.put("VLR_DIGITS", vlrDigits);
            parameters.put("IMSI", imsi);
            parameters.put("STATUS", status);
            parameters.put("TYPE", type);
            parameters.put("TSTAMP", tstamp);
            parameters.put("LOCAL_DIALOG_ID", localDialogId);
            parameters.put("REMOTE_DIALOG_ID", remoteDialogId);
            parameters.put("DIALOG_DURATION", dialogDuration);
            parameters.put("USSD_STRING", ussdString);
            parameters.put("ID", id);
            parameters.put("FILE_ID", fileId);

            jdbcInsert.execute(parameters);
            
            return true;
        } catch (Exception e) {
        	return false;
        }
	}

}
