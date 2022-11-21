import connection.SQLConnection;
import connection.jdbc.JDBCHelper;
import menu.AppMenu;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/**
 * Program logic. Contains the {@link #main(String[]) method}.
 *
 * @author Victor Piles
 */
public class App
{
	private final String databaseURL;
	private final AppMenu menu = new AppMenu();
	private final SQLConnection connection = new SQLConnection();
	private JDBCHelper helper;

	public App(String databaseURL)
	{
		this.databaseURL = databaseURL;
	}

	/* ==================== SQL Procedures ==================== */

	private void createCallableProcedures() throws SQLException
	{
		String sql;

		System.out.println("(INFO) Creating procedures...");

		System.out.println("                          ...set_new_department");
		sql = "DROP PROCEDURE set_new_department IF EXISTS;\n" +
				"CREATE PROCEDURE set_new_department(dept_num INT, name VARCHAR(20), office VARCHAR(20))\n" +
				"    MODIFIES SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC INSERT INTO departments VALUES (dept_num, name, office);\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...set_new_teacher");
		sql = "DROP PROCEDURE set_new_teacher IF EXISTS;\n" +
				"CREATE PROCEDURE set_new_teacher(id INT, name VARCHAR(20), surname VARCHAR(20), email VARCHAR(20), start_date DATE,\n" +
				"                                 dept_num INT, salary INT)\n" +
				"    MODIFIES SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC INSERT INTO teachers VALUES (id, name, surname, email, start_date, dept_num, salary);\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...set_salary");
		sql = "DROP PROCEDURE set_salary IF EXISTS;\n" +
				"CREATE PROCEDURE set_salary(k INT)\n" +
				"    MODIFIES SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC\n" +
				"    UPDATE teachers SET salary = k;\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...rise_salary_prct");
		sql = "DROP PROCEDURE rise_salary_prct IF EXISTS;\n" +
				"CREATE PROCEDURE rise_salary_prct(p FLOAT)\n" +
				"    MODIFIES SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC\n" +
				"    UPDATE teachers\n" +
				"    SET salary = (salary + ((salary * p) / 100));\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...rise_salary_per_dept");
		sql = "DROP PROCEDURE rise_salary_per_dept IF EXISTS;\n" +
				"CREATE PROCEDURE rise_salary_per_dept(p FLOAT, dept_name VARCHAR(20))\n" +
				"    MODIFIES SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC\n" +
				"    UPDATE teachers\n" +
				"    SET salary = (salary + ((salary * p) / 100))\n" +
				"    WHERE dept_num = (SELECT dept_num\n" +
				"                      FROM departments\n" +
				"                      WHERE departments.name = dept_name);\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...get_newest_teacher");
		sql = "DROP PROCEDURE get_newest_teacher IF EXISTS;\n" +
				"CREATE PROCEDURE get_newest_teacher(OUT last_teacher VARCHAR(20))\n" +
				"    READS SQL DATA \n" +
				"BEGIN\n" +
				"    ATOMIC\n" +
				"    SET last_teacher = (SELECT surname FROM teachers ORDER BY id DESC LIMIT 1);\n" +
				"END;";
		connection.updateQuery(sql);

		System.out.println("                          ...count_teachers");
		sql = "DROP PROCEDURE count_teachers IF EXISTS;\n" +
				"CREATE PROCEDURE count_teachers(IN dept_name VARCHAR(20), OUT n_teachers INT)\n" +
				"    READS SQL DATA\n" +
				"BEGIN\n" +
				"    ATOMIC\n" +
				"    SET n_teachers =\n" +
				"            (SELECT COUNT(*)\n" +
				"             FROM teachers\n" +
				"             WHERE dept_num = (SELECT dept_num\n" +
				"                               FROM departments\n" +
				"                               WHERE name = dept_name));\n" +
				"END;";
		connection.updateQuery(sql);
	}

	/* ==================== Extra menu methods ==================== */

	/**
	 * Calls the {@link App} methods that displayed on the {@link App#menu}.
	 *
	 * @param option Method chosen by the user.
	 *
	 * @return If the program should exit or not.
	 */
	private boolean runOption(int option)
	{
		switch (option) {
			case 1:
				setNewDepartment();
				return false;
			case 2:
				setNewTeacher();
				return false;
			case 3:
				setSalary();
				return false;
			case 4:
				riseSalaryPrct();
				return false;
			case 5:
				riseSalaryPerDept();
				return false;
			case 6:
				getNewestTeacher();
				return false;
			case 7:
				countTeachers();
				return false;
			default:
				return true;
		}
	}

	/* ==================== Options ==================== */

	private void setNewDepartment()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("DeptNum -> ");
		int deptNum = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Name -> ");
		String deptName = scanner.nextLine();
		System.out.print("Office -> ");
		String deptOffice = scanner.nextLine();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL set_new_department(?, ?, ?);");
			statement.setInt(1, deptNum);
			statement.setString(2, deptName);
			statement.setString(3, deptOffice);
			/* call */
			statement.execute();
			/* print */
			helper.printResultSet(connection.selectQuery("SELECT * FROM departments;"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void setNewTeacher()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("Id -> ");
		int teacherId = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Name -> ");
		String teacherName = scanner.nextLine();
		System.out.print("Surame -> ");
		String teacherSurname = scanner.nextLine();
		System.out.print("Email -> ");
		String teacherEmail = scanner.nextLine();
		System.out.print("Start date -> ");
		Date teacherDate = Date.valueOf(scanner.nextLine());
		System.out.print("Dept num -> ");
		int teacherDeptNum = scanner.nextInt();
		System.out.print("Salary -> ");
		float teacherSalary = scanner.nextFloat();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL set_new_teacher(?, ?, ?, ?, ?, ?, ?);");
			statement.setInt(1, teacherId);
			statement.setString(2, teacherName);
			statement.setString(3, teacherSurname);
			statement.setString(4, teacherEmail);
			statement.setDate(5, teacherDate);
			statement.setInt(6, teacherDeptNum);
			statement.setFloat(7, teacherSalary);
			/* call */
			statement.execute();
			/* print */
			helper.printResultSet(connection.selectQuery("SELECT * FROM teachers;"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void setSalary()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("Salary -> ");
		int salary = scanner.nextInt();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL set_salary(?)");
			statement.setInt(1, salary);
			/* call */
			statement.execute();
			/* print */
			helper.printResultSet(connection.selectQuery("SELECT * FROM teachers;"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void riseSalaryPrct()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("Salary rise % -> ");
		float salaryPrct = scanner.nextFloat();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL rise_salary_prct(?)");
			statement.setFloat(1, salaryPrct);
			/* call */
			statement.execute();
			/* print */
			helper.printResultSet(connection.selectQuery("SELECT * FROM teachers;"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void riseSalaryPerDept()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("Salary rise % -> ");
		float salaryPrct = scanner.nextFloat();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Department name % -> ");
		String departmentName = scanner.nextLine();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL rise_salary_per_dept(?, ?)");
			statement.setFloat(1, salaryPrct);
			statement.setString(2, departmentName);
			/* call */
			statement.execute();
			/* print */
			helper.printResultSet(connection.selectQuery("SELECT * FROM teachers;"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void getNewestTeacher()
	{
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL get_newest_teacher(?)");
			statement.registerOutParameter(1, Types.VARCHAR);
			/* call */
			statement.execute();
			/* print */
			System.out.println("Newest teacher surname: " + statement.getString(1));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void countTeachers()
	{
		Scanner scanner = new Scanner(System.in);
		/* get data */
		System.out.print("Department name -> ");
		String departmentName = scanner.nextLine();
		try {
			/* prepare */
			CallableStatement statement = connection.getConnection()
					.prepareCall("CALL count_teachers(?, ?)");
			statement.setString(1, departmentName);
			statement.registerOutParameter(2, Types.INTEGER);
			/* call */
			statement.execute();
			/* print */
			System.out.printf("Teachers count for department %s: %s\n", departmentName, statement.getInt(2));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* ==================== Main App ==================== */

	/**
	 * Prints a message with the name of the program, use this at the beginning.
	 */
	private void printWelcomeMessage()
	{
		System.out.println("====================================");
		System.out.println(" Activity 3.4 - Callable Statements ");
		System.out.println("====================================");
	}

	public static void main(String[] args)
	{
		String path = "src/main/resources/database/db";
		String jdbc = "jdbc:hsqldb:";
		App app = new App(jdbc + path);

		try {
			int option;
			boolean exit;

			/* ========== Create the database ========== */
			System.out.printf("(INFO) Connecting to %s%s database...\n", jdbc, path);
			app.connection.initConnection(app.databaseURL);
			app.helper = new JDBCHelper(app.connection.getConnection());

			System.out.println("====================================");
			System.out.println("        Database information        ");
			System.out.println("====================================");
			app.helper.printDatabaseMetadata();

			app.createCallableProcedures();

			do {
				app.printWelcomeMessage();
				System.out.println(app.menu);
				option = app.menu.inputMenuOption();
				exit = app.runOption(option);
			} while (!exit);

			/* ========== Close the database ========== */
			System.out.printf("(INFO) Disconnecting from %s%s database...\n", jdbc, path);
			app.connection.getConnection().close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}