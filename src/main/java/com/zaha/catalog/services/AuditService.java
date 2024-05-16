package com.zaha.catalog.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@Log
public class AuditService {

    private static final String CSV_FILE_PATH = "src/main/resources/audit.csv";
    private static final String CSV_HEADER = "IP,ACTION,TIMESTAMP";

    private boolean csvFileExists() {
        return new File(CSV_FILE_PATH).exists();
    }

    // TODO: move data handling to a repository
    public void log(String ip, String action) {
        boolean fileExists = csvFileExists();
        try (FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true)) {
            if (!fileExists) {
                log.info("Creating new audit.csv file");
                fileWriter.append(CSV_HEADER);
                fileWriter.append(";\n");
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            fileWriter.append(ip);
            fileWriter.append(",");
            fileWriter.append(action);
            fileWriter.append(",");
            fileWriter.append(timestamp);
            fileWriter.append(";\n");
        } catch (IOException e) {
            log.warning("Failed to write to audit.csv: " + Arrays.toString(e.getStackTrace()));
        }

        log.info("Logged action: " + action + " from IP: " + ip);
    }

}
