package org.example;

import org.apache.commons.io.FileUtils;
import org.example.connection.SQLConnection;
import org.example.connection.jdbc.JDBCHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
	private final String databaseURL;
	private final SQLConnection connection = new SQLConnection();
	private JDBCHelper helper;

	public App(String databaseURL) {
		this.databaseURL = databaseURL;
	}

	/* ==================== Database Stuff ==================== */

	/**
	 * Deletes the files in the directory if the {@link File directory} exists.
	 *
	 * @param path The path to the directory.
	 *
	 * @see FileUtils#deleteDirectory(File)
	 */
	private void deleteDirectoryIfExists(String path) {
		File directory = new File(path);

		if (!directory.exists()) {
			System.out.printf("(WARNING) %s doesn't exist. Not deleting.\n", path);
			return;
		}

		if (!directory.isDirectory()) {
			System.out.printf("(WARNING) %s is not a directory. Not deleting.\n", path);
			return;
		}

		System.out.printf("(INFO) Deleting database %s...\n", path);

		try {
			FileUtils.deleteDirectory(directory);
			System.out.printf("(INFO) Database %s has been deleted.\n", path);
		}
		catch (IOException e) {
			System.out.println("(ERROR) " + e.getMessage());
		}
	}

	/**
	 * Returns all lines from a file.
	 *
	 * @param path The file path.
	 *
	 * @return Array with all the lines.
	 */
	private String[] getValuesFromFile(String path) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		catch (IOException e) {
			System.out.println("(ERROR) " + e.getMessage());
		}

		return lines.toArray(new String[0]);
	}

	/**
	 * Creates a table in the {@link #connection database}.
	 *
	 * @param table the table.
	 *
	 * @see SQLConnection#updateQuery(String)
	 */
	private void createTable(String table, List<String> columns) {
		String query = String.format("CREATE TABLE %s (", table);
		StringBuilder queryBuilder = new StringBuilder(query);

		for (int i = 0; i < columns.size(); i++) {
			queryBuilder.append(columns.get(i));
			if (i < columns.size() - 1) {
				queryBuilder.append(",");
			}
		}
		queryBuilder.append(")");

		System.out.println(queryBuilder);

		try {
			System.out.printf("(INFO) Creating %s table...\n", table);

			int success = connection.updateQuery(queryBuilder.toString());
			if (success == 0) {
				System.out.printf("(INFO) %s table has been created.\n", table);
			}
			else System.out.printf("(ERROR) Error while creating %s table.\n", table);
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("(ERROR) " + e.getMessage());
		}
	}

	/**
	 * Inserts in the table a row with the values and types provided.
	 *
	 * @param table  The table.
	 * @param values The values.
	 * @param types  The values type.
	 */
	private void insertIntoTableValues(String table, List<String> values, List<Class<?>> types) {
		if (values.size() != types.size()) {
			System.out.println("(ERROR) Different number of values and types.");
		}

		/* build the query */
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + table + " VALUES (");
		for (int i = 0; i < values.size(); i++) {
			queryBuilder.append("?");
			if (i < values.size() - 1) {
				queryBuilder.append(",");
			}
		}
		queryBuilder.append(")");

		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(queryBuilder.toString());

			/* get value types */
			for (int i = 0; i < values.size(); i++) {
				/* if null */
				if (values.get(i).equals("")) {
					statement.setNull(i + 1, Types.VARCHAR);
				}
				else if (types.get(i) == int.class) {
					statement.setInt(i + 1, Integer.parseInt(values.get(i)));
				}
				else if (types.get(i) == String.class) {
					statement.setString(i + 1, values.get(i));
				}
				else if (types.get(i) == Date.class) {
					statement.setDate(i + 1, Date.valueOf(values.get(i)));
				}
				/* add more types here */
				else throw new ClassNotFoundException(String.format("Class %s not supported", types.get(i)));
			}

			System.out.println(queryBuilder);

			/* run query */
			int affectedRows = statement.executeUpdate();
			System.out.printf("(INFO) %s rows affected.\n", affectedRows);
		}
		catch (SQLException | ClassNotFoundException e) {
			System.out.println("(ERROR) " + e.getMessage());
		}
	}

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

			String query = String.format("SELECT * FROM %s", table);
			System.out.println(query);

			ResultSet resultSet = connection.selectQuery(query);
			if (resultSet != null) {
				helper.printResultSet(resultSet);
			}
			else System.out.println("(ERROR) Error while running the query.");

		}
		catch (SQLException e) {
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
		}
		catch (SQLException e) {
			System.out.println("(ERROR) " + e.getMessage());
		}
	}

	/* ==================== Main App ==================== */

	/**
	 * Prints a message with the name of the program, use this at the beginning.
	 */
	private void printWelcomeMessage() {
		System.out.println("====================================");
		System.out.println(" Activity 3.3 - Prepared Statements ");
		System.out.println("====================================");
	}

	public static void main(String[] args) {
		String path = "src/main/resources/database";
		String departmentPath = "src/main/resources/files/departments.txt";
		String teachersPath = "src/main/resources/files/teachers.txt";

		App app = new App("jdbc:derby:" + path + ";create=true");

		app.printWelcomeMessage();

		try {
			/* ========== Check if the database path exists. If so, delete it ========== */
			app.deleteDirectoryIfExists(path);
			/* ========== Create the database ========== */
			app.connection.initConnection(app.databaseURL);
			app.helper = new JDBCHelper(app.connection.getConnection());

			System.out.println("====================================");
			System.out.println("        Database information        ");
			System.out.println("====================================");
			app.helper.printDatabaseMetadata();

			/* ========== Create departments table and fill ========== */
			app.createTable("departments",
					List.of("dept_num INT PRIMARY KEY",
							"name VARCHAR(100)",
							"office VARCHAR(100)")
			);
			String[] departmentValues = app.getValuesFromFile(departmentPath);

			for (String line : departmentValues) {
				String[] value = line.split(",");
				app.insertIntoTableValues("departments",
						List.of(value[0], value[1], value[2]), List.of(int.class, String.class, String.class));
			}

			/* ========== Show the contents of departments ========== */
			app.showAllFromTable("departments");

			/* ========== Create teachers table and fill ========== */
			app.createTable("teachers",
					List.of("id INT PRIMARY KEY",
							"name VARCHAR(100)",
							"surname VARCHAR(100)",
							"email VARCHAR(100)",
							"start_date DATE",
							"dept_num INT",
							"FOREIGN KEY (dept_num) REFERENCES departments (dept_num)")
			);
			String[] teachersValues = app.getValuesFromFile(teachersPath);

			for (String line : teachersValues) {
				String[] value = line.split(",");
				app.insertIntoTableValues("teachers", List.of(value[0], value[1], value[2], value[3], value[4], value[5]),
						List.of(int.class, String.class, String.class, String.class, Date.class, int.class));
			}

			/* ========== Show the contents of teachers ========== */
			app.showAllFromTable("teachers");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			app.quit();
		}
	}
}