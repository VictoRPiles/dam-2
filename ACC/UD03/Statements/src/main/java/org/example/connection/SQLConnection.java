package org.example.connection;

import java.sql.*;

/**
 * @author Victor Piles
 */
public class SQLConnection implements Connectable {
    private Connection connection;

    @Override
    public void initConnection(String databaseURL) throws SQLException {
        connection = DriverManager.getConnection(databaseURL);
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    /**
     * Checks if the passed query is a SELECT query, if so, runs the query on the database.
     *
     * @param query The query to run.
     *
     * @return A {@link ResultSet} with the result of the query or {@code null} if it's not a SELECT query.
     *
     * @throws SQLException If an SQL error happens.
     */
    public ResultSet selectQuery(String query) throws SQLException {
        if (query.toUpperCase().startsWith("SELECT")) {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
        return null;
    }

    /**
     * Checks if the passed query is not a SELECT query, if so, runs the query on the database.
     *
     * @param query The query to run.
     *
     * @return Number of rows affected by the query or -1 if the query was a SELECT query.
     *
     * @throws SQLException If an SQL error happens.
     */
    public int updateQuery(String query) throws SQLException {
        if (!query.toUpperCase().startsWith("SELECT")) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        }
        return -1;
    }

    public Connection getConnection() {
        return connection;
    }
}