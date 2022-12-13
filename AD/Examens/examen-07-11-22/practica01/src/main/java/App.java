import connection.SQLConnection;
import connection.jdbc.JDBCHelper;
import menu.DatabaseMenu;
import menu.OptionsMenu;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class App
{
	private String databaseURL;
	private final OptionsMenu optionsMenu = new OptionsMenu();
	private final DatabaseMenu databaseMenu = new DatabaseMenu();
	private JDBCHelper helper;

	/**
	 * @param database Database chosen in the {@link App#databaseMenu}.
	 *
	 * @return The URL of the database chosen in the {@link App#databaseMenu}.
	 */
	public String getChosenDatabaseURL(int database)
	{
		switch (database) {
			case 1:
				return "jdbc:sqlite:src/main/resources/sqlite/database";
			case 2:
				return "jdbc:derby:src/main/resources/derby/database";
			case 3:
				return "jdbc:hsqldb:src/main/resources/hsqldb/database";
			default:
				return "";
		}
	}

	/**
	 * Calls the {@link App} methods that displayed on the {@link App#optionsMenu}.
	 *
	 * @param option     Method chosen by the user.
	 * @param connection The connection to the database.
	 *
	 * @return If the program should exit or not.
	 */
	private boolean runOption(SQLConnection connection, int option) throws SQLException
	{
		switch (option) {
			case 1:
				mostrarAlumnos(connection);
				return false;
			case 2:
				mostrarMunicipios(connection);
				return false;
			case 3:
				introduceAlumno(connection);
				return false;
			case 4:
				introduceMunicipio(connection);
				return false;
			default:
				return true;
		}
	}

	/* ========== Options Menu ========== */

	private void mostrarAlumnos(SQLConnection connection) throws SQLException
	{
		String sql = ("SELECT * FROM alumnos");
		System.out.println(sql);
		helper.printResultSet(connection.selectQuery(sql));
		System.out.println();
	}

	private void mostrarMunicipios(SQLConnection connection) throws SQLException
	{
		String sql = ("SELECT * FROM municipios");
		System.out.println(sql);
		helper.printResultSet(connection.selectQuery(sql));
		System.out.println();
	}

	private void introduceAlumno(SQLConnection connection) throws SQLException
	{
		/* ========== Get the data ========== */
		Scanner scanner = new Scanner(System.in);
		int nia, idMunicipio;
		String nombre, apellidos;

		System.out.print("NIA -> ");
		nia = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Nombre -> ");
		nombre = scanner.nextLine();
		System.out.print("Apellidos -> ");
		apellidos = scanner.nextLine();
		System.out.print("ID Municipio -> ");
		idMunicipio = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */

		/* ========== Prepare the statement ========== */
		String sql = ("INSERT INTO alumnos VALUES (?,?,?,?)");

		PreparedStatement statement = connection.getConnection().prepareStatement(sql);
		statement.setInt(1, nia);
		statement.setString(2, nombre);
		statement.setString(3, apellidos);
		statement.setInt(4, idMunicipio);

		/* ========== Insert the data ========== */
		System.out.println(sql);
		int affectedRows = statement.executeUpdate();
		System.out.printf("(INFO) %s rows affected.\n", affectedRows);

		statement.close();
	}

	private void introduceMunicipio(SQLConnection connection) throws SQLException
	{
		if (!connection.getConnection().getMetaData().getDriverName().toUpperCase().contains("HSQL")) {
			System.out.println("(ERROR) La conexión debe ser de tipo HSQL");
			return;
		}

		/* ========== Get the data ========== */
		Scanner scanner = new Scanner(System.in);
		int idMunicipio, nHabitantes;
		String nombre;

		System.out.print("ID Municipio -> ");
		idMunicipio = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Nombre -> ");
		nombre = scanner.nextLine();
		System.out.print("Habitantes -> ");
		nHabitantes = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */


		/* ========== Prepare the statement ========== */
		String sql = ("CALL nuevo_municipio(?, ?, ?);");

		CallableStatement statement = connection.getConnection()
				.prepareCall(sql);
		statement.setInt(1, idMunicipio);
		statement.setString(2, nombre);
		statement.setInt(3, nHabitantes);

		/* ========== Insert the data ========== */
		System.out.println(sql);
		int affectedRows = statement.executeUpdate();
		System.out.printf("(INFO) %s rows affected.\n", affectedRows);

		statement.close();
	}

	public static void main(String[] args)
	{
		App app = new App();
		final SQLConnection connection = new SQLConnection();
		boolean exit;

		/* ========== Choose the database ========== */
		System.out.println("¿Con que SGDB quiere conectar?");
		System.out.print(app.databaseMenu);
		int databaseOption = app.databaseMenu.inputMenuOption();
		app.databaseURL = app.getChosenDatabaseURL(databaseOption);

		try {
			/* ========== Connect to database ========== */
			System.out.printf("(INFO) Connecting to %s database...\n", app.databaseURL);
			connection.initConnection(app.databaseURL);
			app.helper = new JDBCHelper(connection.getConnection());

			/* ========== Print database connection ========== */
			System.out.println("====================================");
			System.out.println("        Database information        ");
			System.out.println("====================================");
			app.helper.printDatabaseMetadata();
			System.out.println();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		/* ========== Choose the option ========== */
		do {
			System.out.println("¿Que operación desea realizar?");
			System.out.print(app.optionsMenu);
			int optionsOption = app.optionsMenu.inputMenuOption();
			try {
				exit = app.runOption(connection, optionsOption);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} while (!exit);

		/* ========== Close the database ========== */
		System.out.printf("(INFO) Disconnecting from %s database...\n", app.databaseURL);
		try {
			connection.getConnection().close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}