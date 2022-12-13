package connection.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Piles
 */
public class JDBCHelper
{
	private final Connection connection;

	public JDBCHelper(Connection connection) throws SQLException
	{
		this.connection = connection;
	}

	/**
	 * For each record in a {@link ResultSet}, prints the records count and all it's columns.
	 *
	 * @param resultSet The {@link ResultSet} to print.
	 *
	 * @throws SQLException If an SQL error happens.
	 */
	public void printResultSet(ResultSet resultSet) throws SQLException
	{
		ResultSetMetaData metadata = resultSet.getMetaData();
		int columnCount = metadata.getColumnCount();
		int recordCount = 0;

		while (resultSet.next()) {
			recordCount++;
			System.out.println("===================================");
			System.out.printf("            Record %02d             \n", recordCount);
			System.out.println("===================================");

			for (int i = 1; i <= columnCount; i++) {
				System.out.println(metadata.getColumnName(i) + " -> " + resultSet.getString(i));
			}
		}
	}

	/**
	 * Prints the {@link DatabaseMetaData#getDriverName() database driver name},
	 * the {@link DatabaseMetaData#getDriverVersion() database driver version} and
	 * the {@link DatabaseMetaData#getURL() databse URL}.
	 *
	 * @throws SQLException If an SQL error happens.
	 */
	public void printDatabaseMetadata() throws SQLException
	{
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println("Driver -> " + metaData.getDriverName() + " " + metaData.getDriverVersion());
		System.out.println("Database URL -> " + metaData.getURL());
	}

	/**
	 * Get all columns name from a table.
	 *
	 * @param table The table.
	 *
	 * @return All columns name as a {@link List<String>}
	 *
	 * @throws SQLException If an SQL error happens.
	 */
	public List<String> getColumnNamesFromTable(String table) throws SQLException
	{
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + table);
		ResultSetMetaData metadata = resultSet.getMetaData();
		List<String> columns = new ArrayList<>();
		int columnCount = metadata.getColumnCount();

		for (int i = 1; i <= columnCount; i++) {
			columns.add(metadata.getColumnName(i));
		}

		return columns;
	}
}