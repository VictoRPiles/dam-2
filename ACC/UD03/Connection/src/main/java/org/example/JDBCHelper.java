package org.example;

import java.sql.*;
import java.util.ArrayList;

public abstract class JDBCHelper {
    /**
     * Prints all metadata from a {@link ResultSet}.
     *
     * @param res The {@link ResultSet} to print.
     * @throws SQLException
     */
    public static void showResultSet(ResultSet res) throws SQLException {
        ResultSetMetaData metadata = res.getMetaData();
        int columnCount = metadata.getColumnCount();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        while (res.next()) {
            for (int i = 1; i <= columnCount; i++) {
                names.add(metadata.getColumnName(i));
                values.add(res.getString(i));
            }
        }

        System.out.println(names);
        System.out.println(values);
    }

    /**
     * Returns if the connected database contains a certain table.
     *
     * @param con       The connected database.
     * @param tableName The table to search.
     * @return Boolean value.
     * @throws SQLException
     */
    public static boolean containsTable(Connection con, String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = con.getMetaData();
        ResultSet res = databaseMetaData.getTables(null, null, null, null);

        while (res.next()) {
            if (tableName.equalsIgnoreCase(res.getString("TABLE_NAME")))
                return true;
        }

        return false;
    }
}
