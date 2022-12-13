package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        byte option = menu();
        switch (option) {
            case 1 -> {
                GenericSQLConnection sqliteConnection = new GenericSQLConnection("jdbc:sqlite:src/main/resources/database/sqlite/sqlite.db");

                sqliteConnection.printDatabaseInfo();
                sqliteConnection.printTableInfo(null);
                String[] tables = sqliteConnection.getDatabaseTables(null);
                sqliteConnection.printColumnInfo(tables);

                sqliteConnection.close();
            }
            case 2 -> {
                GenericSQLConnection derbyConnection = new GenericSQLConnection("jdbc:derby:src/main/resources/database/derby/");

                derbyConnection.printDatabaseInfo();
                derbyConnection.printTableInfo("APP");
                String[] tables = derbyConnection.getDatabaseTables("APP");
                derbyConnection.printColumnInfo(tables);

                derbyConnection.close();
            }
            case 3 -> {
                GenericSQLConnection hyperConnection = new GenericSQLConnection("jdbc:hsqldb:src/main/resources/database/hsqldb/");

                hyperConnection.printDatabaseInfo();
                hyperConnection.printTableInfo("PUBLIC");
                String[] tables = hyperConnection.getDatabaseTables("PUBLIC");
                hyperConnection.printColumnInfo(tables);

                hyperConnection.close();
            }
            case 4 -> {/* Do nothing here */}
            default -> throw new IllegalArgumentException("Incorrect database option");
        }
    }

    private static byte menu() {
        Scanner scanner = new Scanner(System.in);
        byte option;

        System.out.println("Choose the database:");
        System.out.println("1. SQLite Database");
        System.out.println("2. Derby Database");
        System.out.println("3. HSQLDB Database");
        System.out.println("4. Exit");

        System.out.print("> ");
        option = scanner.nextByte();

        return switch (option) {
            case 1, 2, 3, 4 -> option;
            default -> menu();
        };
    }
}