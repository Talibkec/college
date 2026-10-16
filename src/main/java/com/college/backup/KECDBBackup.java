package com.college.backup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smattme.MysqlExportService;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@EnableScheduling
public class KECDBBackup {
    private static final Logger logger = LoggerFactory.getLogger(KECDBBackup.class);


    public void setupBackupPropperties() {

        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.JDBC_DRIVER_NAME, "com.mysql.jdbc.Driver");
        properties.setProperty(MysqlExportService.DB_NAME, env("BACKUP_DB_NAME", "college"));
        properties.setProperty(MysqlExportService.DB_USERNAME, env("DB_USERNAME", "system"));
        properties.setProperty(MysqlExportService.DB_PASSWORD, env("DB_PASSWORD", ""));

        //properties relating to email config
        properties.setProperty(MysqlExportService.EMAIL_HOST, "smtp.gmail.com");
        properties.setProperty(MysqlExportService.EMAIL_PORT, "587");
        properties.setProperty(MysqlExportService.EMAIL_USERNAME, env("MAIL_USERNAME", "Test@keck.ac.in"));
        properties.setProperty(MysqlExportService.EMAIL_PASSWORD, env("MAIL_PASSWORD", ""));
        properties.setProperty(MysqlExportService.EMAIL_FROM, env("MAIL_USERNAME", "Test@keck.ac.in"));
        properties.setProperty(MysqlExportService.EMAIL_TO, env("BACKUP_MAIL_TO", "Test@keck.ac.in"));
        properties.setProperty(MysqlExportService.DELETE_EXISTING_DATA, "true");

        //set the outputs temp dir
        properties.setProperty(MysqlExportService.TEMP_DIR, new File("external").getPath());
        MysqlExportService mysqlExportService = new MysqlExportService(properties);
        try {
            mysqlExportService.export();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            logger.error("Database backup failed", e);
        }

    }

    /** Credentials come from the environment (same variables as the web application); never hard-code them. */
    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    public static void main(String[] args) {
        KECDBBackup kecdbBackup = new KECDBBackup();
        kecdbBackup.setupBackupPropperties();
    }
}
