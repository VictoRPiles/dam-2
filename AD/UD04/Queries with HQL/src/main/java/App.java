import entity.DepartmentsEntity;
import entity.TeachersEntity;
import org.hibernate.Session;
import query.DepartmentsQuery;
import query.TeachersQuery;
import util.HibernateUtil;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class App {

	private static final Menu menu;
	private static final Session session;

	static {
		menu = new Menu();
		session = HibernateUtil.getSessionFactory().openSession();

		// Provide the Query classes with a session
		TeachersQuery.setSession(session);
		DepartmentsQuery.setSession(session);
	}

	/**
	 * Closes the {@link #session session}.
	 */
	public static void close() {
		if (session.isOpen()) session.close();
	}

	public static void main(String[] args) {
		int option;

		do {
			// Request the option
			Scanner scanner = new Scanner(System.in);
			menu.show();
			System.out.print("> ");
			option = scanner.nextInt();
			// Flush buffer
			scanner.nextLine();

			// Evaluate the option
			switch (option) {
				case 1:
					int i = 0;
					// For each department
					for (DepartmentsEntity department : DepartmentsQuery.getAllDepartments()) {
						System.out.printf("===== Record %02d =====\n", ++i);
						DepartmentsQuery.showDepartment(department);
					}
					break;
				case 2:
					// Request department name
					System.out.print("Department name: ");
					String name = scanner.nextLine();

					// Get the department
					DepartmentsEntity departmentByName = DepartmentsQuery.getDepartmentByName(name);

					// If no department found
					if (departmentByName == null) {
						System.out.println("There is no department that matches the name " + name);
						break;
					}

					DepartmentsQuery.showDepartment(departmentByName);
					break;
				case 3: // Request department name
					System.out.print("Department name: ");
					name = scanner.nextLine();

					// Get the average salary
					double averageSalary = DepartmentsQuery.getAverageSalaryOfDepartment(name);

					System.out.printf("Average salary of department %s: %s%n", name, averageSalary);
					break;
				case 4:
					HashMap<String, Double> departmentNameAndAverageSalary = DepartmentsQuery.getAverageSalaryPerDept();
					System.out.println(departmentNameAndAverageSalary);
					break;
				case 5:
					i = 0;
					// For each teacher
					for (TeachersEntity teacher : TeachersQuery.getAllTeachers()) {
						System.out.printf("===== Record %02d =====\n", ++i);
						TeachersQuery.showTeacher(teacher);
					}
					break;
				case 6:
					TeachersQuery.showTeacher(TeachersQuery.getMostVeteranTeacher());
					break;
				case 7:
					// Request the new salary
					System.out.print("New salary: ");
					int newSalary = scanner.nextInt();
					// Flush buffer
					scanner.nextLine();

					// Set the salary
					int rows = TeachersQuery.setSalary(newSalary);
					System.out.println(rows + " rows affected");
					break;
				case 8:
					// Request the data
					System.out.print("Years to be senior: ");
					int numOfYearsToBeSenior = scanner.nextInt();
					System.out.print("Salary percentage rise: ");
					int prctRise = scanner.nextInt();
					// Flush buffer
					scanner.nextLine();

					// Rise the salary
					rows = TeachersQuery.riseSalaryOfSeniors(numOfYearsToBeSenior, prctRise);
					System.out.println(rows + " rows affected");
					break;
				case 9:
					// Request the department name
					System.out.print("Department name: ");
					name = scanner.nextLine();

					// Delete the teachers
					rows = TeachersQuery.deleteTeachersOfDepartment(name);
					System.out.println(rows + " rows affected");
					break;
				case 10: // TODO: 5/12/2022
					System.out.println("Closing session...");
					close();
					System.out.println("Session closed.");
					break;
				default:
					System.out.println("Invalid option!");
					break;
			}
		} while (option != menu.EXIT_OPTION);
	}

	static class Menu {

		final int EXIT_OPTION = 10;

		/**
		 * Prints the program menu.
		 */
		void show() {
			System.out.println("===============================");
			System.out.println("    Act4.3 Queries with HQL    ");
			System.out.println("===============================");
			// @formatter:off
			System.out.println(
					"1. Show all departments\n" +
					"2. Show department whose name matches a pattern\n" +
					"3. Get average salary of a department (by name)\n" +
					"4. Show average salary of each department\n" +
					"5. Show all teachers\n" +
					"6. Show most veteran teacher\n" +
					"7. Set salary\n" +
					"8. Rise salary of senior teachers\n" +
					"9. Delete teachers of a department\n" +
					"10. Quit"
			);
			// @formatter:on
		}
	}
}