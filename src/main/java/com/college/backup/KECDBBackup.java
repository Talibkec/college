package com.college.backup;

import com.smattme.MysqlExportService;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


@EnableScheduling
public class KECDBBackup {

    public void setupBackupPropperties() {

        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.JDBC_DRIVER_NAME, "com.mysql.jdbc.Driver");
        properties.setProperty(MysqlExportService.DB_NAME, "college");
        properties.setProperty(MysqlExportService.DB_USERNAME, "system");
        properties.setProperty(MysqlExportService.DB_PASSWORD, "Password@123");

        //properties relating to email config
        properties.setProperty(MysqlExportService.EMAIL_HOST, "smtp.gmail.com");
        properties.setProperty(MysqlExportService.EMAIL_PORT, "587");
        properties.setProperty(MysqlExportService.EMAIL_USERNAME, "Test@localhost");
        properties.setProperty(MysqlExportService.EMAIL_PASSWORD, "Test@123");
        properties.setProperty(MysqlExportService.EMAIL_FROM, "Test@localhost");
        properties.setProperty(MysqlExportService.EMAIL_TO, "Test@localhost");
        properties.setProperty(MysqlExportService.DELETE_EXISTING_DATA, "true");

        //set the outputs temp dir
        properties.setProperty(MysqlExportService.TEMP_DIR, new File("external").getPath());
        MysqlExportService mysqlExportService = new MysqlExportService(properties);
        try {
            mysqlExportService.export();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        KECDBBackup kecdbBackup = new KECDBBackup();
        kecdbBackup.setupBackupPropperties();
    }
}
