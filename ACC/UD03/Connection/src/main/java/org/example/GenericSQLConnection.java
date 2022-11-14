package org.example;

import java.sql.*;
import java.util.ArrayList;

public class GenericSQLConnection implements SQLConnection {
    private final String URL;
    private Connection connection;
    private DatabaseMetaData databaseMetaData;

    /**
     * Defines the database URL.
     * Calls {@link #initConnection()}.
     *
     * @param URL The database URL.
     */
    public GenericSQLConnection(String URL) {
        this.URL = URL;
        initConnection();
    }

    /**
     * Establishes the connection with the database.
     */
    @Override
    public void initConnection() {
        try {
            System.out.printf("INFO: Connecting to %s database...\n", URL);
            connection = DriverManager.getConnection(URL);
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the database metadata.
     */
    @Override
    public void printDatabaseInfo() {
        String databaseName, driverName, driverVersion, databaseURL, databaseUser;
        try {
            /* storing metadata */
            databaseName = databaseMetaData.getDatabaseProductName();
            driverName = databaseMetaData.getDriverName();
            driverVersion = databaseMetaData.getDriverVersion();
            databaseURL = databaseMetaData.getURL();
            databaseUser = databaseMetaData.getUserName();

            /* printing metadata */
            System.out.println("----------< DATABASE INFORMATION >----------");
            System.out.printf("Database Name -> %s\n", databaseName);
            System.out.printf("Driver -> %s %s\n", driverName, driverVersion);
            System.out.printf("Database URL -> %s\n", databaseURL);
            System.out.printf("Database User Name -> %s\n", databaseUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the tables metadata in the given schema.
     *
     * @param schema Schema from which the tables are to be taken. Pass {@code null} for all schemas.
     */
    @Override
    public void printTableInfo(String schema) {
        ResultSet resultSet;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> catalogs = new ArrayList<>();
        ArrayList<String> schemas = new ArrayList<>();
        try {
            resultSet = databaseMetaData.getTables(null, schema, null, null);
            System.out.printf("----------< TABLE INFORMATION FOR %s SCHEMA >----------\n", schema);
            while (resultSet.next()) {
                /* storing metadata */
                names.add(resultSet.getString("TABLE_NAME"));
                types.add(resultSet.getString("TABLE_TYPE"));
                catalogs.add(resultSet.getString("TABLE_CAT"));
                schemas.add(resultSet.getString("TABLE_SCHEM"));
            }

            /* printing metadata */
            System.out.printf("Name -> %s\n", names);
            System.out.printf("Catalog -> %s\n", catalogs);
            System.out.printf("Schema -> %s\n", schemas);
            System.out.printf("Type -> %s\n", types);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param schema Schema from which the tables are to be taken. Pass {@code null} for all schemas.
     * @return Array with the name of all tables in the schema.
     */
    public String[] getDatabaseTables(String schema) {
        ArrayList<String> tables = new ArrayList<>();
        ResultSet resultSet;
        try {
            /* getting metadata */
            resultSet = databaseMetaData.getTables(null, schema, null, null);
            JDBCHelper.showResultSet(resultSet);

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                /* adding this table to the table list */
                tables.add(tableName);
            }

            return tables.toArray(new String[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the metadata of the columns in the given tables.
     *
     * @param tables Tables from which the columns are to be taken.
     */
    @Override
    public void printColumnInfo(String[] tables) {
        ResultSet resultSet;
        try {
            for (String table : tables) {
                ArrayList<String> names = new ArrayList<>();
                ArrayList<String> types = new ArrayList<>();
                ArrayList<String> sizes = new ArrayList<>();
                ArrayList<String> areNullable = new ArrayList<>();

                resultSet = databaseMetaData.getColumns(null, null, table, null);

                while (resultSet.next()) {
                    /* storing metadata */
                    names.add(resultSet.getString("COLUMN_NAME"));
                    types.add(resultSet.getString("TYPE_NAME"));
                    sizes.add(resultSet.getString("COLUMN_SIZE"));
                    areNullable.add(resultSet.getString("IS_NULLABLE"));
                }

                /* printing metadata */
                System.out.printf("----------< COLUMN INFORMATION FOR %s TABLE >----------\n", table);
                System.out.printf("Name -> %s\n", names);
                System.out.printf("Type -> %s\n", types);
                System.out.printf("Size -> %s\n", sizes);
                System.out.printf("Null -> %s\n", areNullable);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the database connection.
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
