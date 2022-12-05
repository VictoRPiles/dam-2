import entity.DepartmentsEntity;
import org.hibernate.Session;
import query.DepartmentsQuery;
import query.TeachersQuery;
import util.HibernateUtil;

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

		TeachersQuery.session = session;
		DepartmentsQuery.session = session;
	}

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

			// Evaluate the option
			switch (option) {
				case 1:
					// TODO: 5/12/2022
					break;
				case 2: // TODO: 5/12/2022
				case 3: // TODO: 5/12/2022
				case 4: // TODO: 5/12/2022
				case 5: // TODO: 5/12/2022
				case 6: // TODO: 5/12/2022
				case 7: // TODO: 5/12/2022
				case 8: // TODO: 5/12/2022
				case 9: // TODO: 5/12/2022
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