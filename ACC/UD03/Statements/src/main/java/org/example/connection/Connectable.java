package org.example.connection;

import java.sql.SQLException;

/**
 * @author Victor Piles
 */
public interface Connectable {
    /**
     * Configures the database connection with the URL provided.
     *
     * @param databaseURL URL of the database, should include the jdbc driver.
     *
     * @throws SQLException If there is an error connecting with de database.
     */
    void initConnection(String databaseURL) throws SQLException;

    /**
     * Closes the database connection.
     *
     * @throws SQLException If there is an error closing the database connection.
     */
    void closeConnection() throws SQLException;
}