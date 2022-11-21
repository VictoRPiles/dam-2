package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    /* ANSI colors */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        final String databaseUrl = "jdbc:hsqldb:src/main/resources/database/hsqldb/";
        try {
            System.out.printf("%sINFO: Connecting to %s database...%s\n", ANSI_GREEN, databaseUrl, ANSI_RESET);
            Connection connection = DriverManager.getConnection(databaseUrl);

            System.out.printf("Connected to %s schema\n", connection.getSchema());

            System.out.printf("%sINFO: Getting database metadata...%s\n", ANSI_GREEN, ANSI_RESET);
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.printf("Database product name -> %s\n", metaData.getDatabaseProductName());
            System.out.printf("Database driver name -> %s\n", metaData.getDriverName());
            System.out.printf("Database driver version -> %s\n", metaData.getDriverVersion());
            System.out.printf("Database URL -> %s\n", metaData.getURL());
            System.out.printf("Database user name -> %s\n", metaData.getUserName());

            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Couldn't connect to database", e);
            throw new RuntimeException(e);
        }
    }
}