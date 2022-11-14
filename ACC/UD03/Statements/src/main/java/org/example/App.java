package org.example;

import org.example.connection.SQLConnection;
import org.example.connection.jdbc.JDBCHelper;
import org.example.menu.AppMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class of the application. Program logic.
 *
 * @author Victor Piles
 */
public class App {
    private final String databaseURL;
    private final AppMenu appMenu = new AppMenu();
    private final SQLConnection connection = new SQLConnection();
    private JDBCHelper helper;

    public App(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    /* ==================== SQL Stuff ==================== */

    /**
     * Select all from a table in the {@link #connection database}, call {@link JDBCHelper#printResultSet(ResultSet) with the result of the query}.
     *
     * @param table The table.
     *
     * @see JDBCHelper#printResultSet(ResultSet)
     * @see SQLConnection#selectQuery(String)
     */
    private void showAllFromTable(String table) {
        try {
            System.out.printf("(INFO) Selecting all %s...\n", table);

            String query = String.format("SELECT * FROM %s;", table);
            System.out.println(query);

            ResultSet resultSet = connection.selectQuery(query);
            if (resultSet != null) {
                helper.printResultSet(resultSet);
            }
            else System.out.println("(ERROR) Error while running the query.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new record in the specified table, with the specified columns and the specified values.
     * <p>
     * The number of columns must be equal to the number of values.
     *
     * @param table  The table.
     * @param values The values for each column.
     */
    private void addNewInTable(String table, List<String> values) {
        System.out.printf("(INFO) Inserting new record in %s...\n", table);

        List<String> columns;
        try {
            columns = helper.getColumnNamesFromTable(table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (columns.size() != values.size()) {
            System.out.println("(ERROR) Different number of columns and values.");
            return;
        }

        StringBuilder columnsBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();

        for (int i = 0; i < columns.size(); i++) {
            /* append the column name surrounded by quotes */
            columnsBuilder.append("'").append(columns.get(i)).append("'");
            /* if not the final column, append comma */
            if (i != columns.size() - 1) {
                columnsBuilder.append(",");
            }
        }
        for (int i = 0; i < values.size(); i++) {
            /* append the value surrounded by quotes */
            valuesBuilder.append("'").append(values.get(i)).append("'");
            /* if not the final value, append comma */
            if (i != values.size() - 1) {
                valuesBuilder.append(",");
            }
        }

        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table, columnsBuilder, valuesBuilder);
        System.out.println(query);

        try {
            int rowsAffected = connection.updateQuery(query);
            if (rowsAffected > -1) {
                System.out.printf("(INFO) %d rows affected in %s...\n", rowsAffected, table);
            }
            else System.out.println("(ERROR) Error while running the query.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a column with the specified name and type to the specified table.
     *
     * @param table  The table.
     * @param column The column name.
     * @param type   The column data type.
     */
    private void addColumnToTable(String table, String column, String type) {
        System.out.printf("(INFO) Inserting new column %s %s in %s...\n", column, type, table);

        String query = String.format("ALTER TABLE %s ADD %s %s;", table, column, type);

        try {
            int success = connection.updateQuery(query);
            if (success == 0) {
                System.out.printf("(INFO) Column %s %s inserted.\n", column, type);
            }
            else System.out.printf("(ERROR) Error while inserting the %s %s column.\n", column, type);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the SELECT query passed as parameter.
     *
     * @param query The query to run.
     *
     * @see SQLConnection#selectQuery(String)
     * @see JDBCHelper#printResultSet(ResultSet)
     */
    private void evaluateCustomQuery(String query) {
        try {
            System.out.println("(INFO) Running query...");
            System.out.println(query);

            ResultSet resultSet = connection.selectQuery(query);
            if (resultSet != null) {
                helper.printResultSet(resultSet);
            }
            else System.out.println("(ERROR) Error while running the query.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls {@link SQLConnection#closeConnection()}.
     */
    private void quit() {
        try {
            System.out.println("(INFO) Closing database connection...");
            connection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Asks the user for a value for each column of the table.
     *
     * @param table The table.
     *
     * @return A list with the values.
     *
     * @see JDBCHelper#getColumnNamesFromTable(String)
     */
    private List<String> getValuesForTableColumns(String table) {
        Scanner scanner = new Scanner(System.in);
        List<String> values = new ArrayList<>();
        try {
            for (String column : helper.getColumnNamesFromTable(table)) {
                System.out.print(column + " -> ");
                values.add(scanner.nextLine());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    /* ==================== Extra menu methods ==================== */

    /**
     * Asks the user for a menu option.
     * <p>
     * If the option is not in the {@link #appMenu menu}, shows a message and recursively asks again.
     *
     * @return A valid menu option.
     */
    private int inputMenuOption() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.print("Choose a menu option -> ");
        option = scanner.nextInt();

        if (appMenu.containsOptionWithKey(option)) {
            return option;
        }
        else {
            System.out.println("Invalid menu option");
            return inputMenuOption();
        }
    }

    /**
     * Calls the {@link App} methods that displayed on the {@link App#appMenu}.
     *
     * @param option Method chosen by the user.
     *
     * @return If the program should exit or not.
     */
    private boolean runOption(int option) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1:
                showAllFromTable("teachers");
                return false;
            case 2:
                showAllFromTable("departments");
                return false;
            case 3:
                addNewInTable("teachers", getValuesForTableColumns("teachers"));
                return false;
            case 4:
                addNewInTable("departments", getValuesForTableColumns("departments"));
                return false;
            case 5:
                addColumnToTable("teachers", "salary", "DECIMAL");
                return false;
            case 6:
                System.out.print("Custom query -> ");
                String query = scanner.nextLine();

                evaluateCustomQuery(query);
                return false;
            default:
                return true;
        }
    }

    /* ==================== Main App ==================== */

    /**
     * Prints a message with the name of the program, use this at the beginning.
     */
    private void printWelcomeMessage() {
        System.out.println("===================================");
        System.out.println("     Activity 3.2 - Statements     ");
        System.out.println("===================================");
    }

    public static void main(String[] args) {
        App app = new App("jdbc:sqlite:src/main/resources/database/database.db");

        try {
            app.connection.initConnection(app.databaseURL);
            app.helper = new JDBCHelper(app.connection.getConnection());

            System.out.println("===================================");
            System.out.println("       Database  information       ");
            System.out.println("===================================");
            app.helper.printDatabaseMetadata();

            int option;
            boolean exit;
            do {
                app.printWelcomeMessage();
                System.out.println(app.appMenu);
                option = app.inputMenuOption();
                exit = app.runOption(option);
            } while (!exit);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            app.quit();
        }
    }
}