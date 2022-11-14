package org.example;

/* WARNING: Don't confuse this interface with java.sql.Connection */
public interface SQLConnection {
    void initConnection();

    void printDatabaseInfo();

    void printTableInfo(String schema);

    void printColumnInfo(String[] tables);

    void close();
}
