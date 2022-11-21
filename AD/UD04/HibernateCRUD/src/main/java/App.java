import entity.DepartmentsEntity;
import entity.TeachersEntity;
import jakarta.persistence.PersistenceException;
import menu.AppMenu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class App {

	private final AppMenu menu;

	private final Session session;
	private final Transaction transaction;

	public App() {
		this.menu = new AppMenu();

		System.out.println("(INFO) Creating Hibernate session...");
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.transaction = session.beginTransaction();
	}

	/**
	 * Prints the program header.
	 */
	private static void printWelcomeMessage() {
		System.out.println("======================================");
		System.out.println("  Act4.2 CRUD with Hibernate objects  ");
		System.out.println("======================================");
	}

	private void runOption(int option) {
		switch (option) {
			case 1 -> {
				showDepartment();
			}
			case 2 -> {
				showTeacher();
			}
			case 3 -> {
				showTeachersInDepartment();
			}
			case 4 -> {
				createDepartment();
			}
			case 5 -> {
				// TODO: 21/11/2022
			}
			case 6 -> {
				// TODO: 21/11/2022
			}
			case 7 -> {
				// TODO: 21/11/2022
			}
			case 8 -> {
				// TODO: 21/11/2022
			}
			case 9 -> {
				// TODO: 21/11/2022
			}
			case 10 -> {
				// TODO: 21/11/2022
			}
			default -> {
				// TODO: 21/11/2022
			}
		}
	}

	/**
	 * Print the {@link DepartmentsEntity department} with the requested {@link DepartmentsEntity#getDeptNum() deptNum}.
	 * <p>
	 * Print an error message if does not exist.
	 *
	 * @see DepartmentsEntity#toString()
	 */
	private void showDepartment() {
		Scanner scanner = new Scanner(System.in);
		DepartmentsEntity department;
		int id;

		System.out.print("Department number: ");
		id = scanner.nextInt();

		if ((department = session.get(DepartmentsEntity.class, id)) == null) {
			System.out.println("(ERROR) No such department.");
			return;
		}

		System.out.println("(INFO) Showing department...");
		System.out.println(department);
	}

	/**
	 * Print the {@link TeachersEntity teacher} with the requested {@link TeachersEntity#getId()} id}.
	 * <p>
	 * Print an error message if does not exist.
	 *
	 * @see TeachersEntity#toString()
	 */
	private void showTeacher() {
		Scanner scanner = new Scanner(System.in);
		TeachersEntity teacher;
		int id;

		System.out.print("Teacher ID: ");
		id = scanner.nextInt();

		if ((teacher = session.get(TeachersEntity.class, id)) == null) {
			System.out.println("(ERROR) No such teacher.");
			return;
		}

		System.out.println("(INFO) Showing teacher...");
		System.out.println(teacher);
	}

	/**
	 * Prints all {@link TeachersEntity teachers} of the {@link DepartmentsEntity department} with the requested
	 * {@link DepartmentsEntity#getDeptNum()} id}.
	 * <p>
	 * Print an error message if the {@link DepartmentsEntity department} does not exist.
	 * <p>
	 * Print an error message if the {@link DepartmentsEntity department} has no {@link TeachersEntity teachers}.
	 *
	 * @see DepartmentsEntity#getTeachersByDeptNum()
	 */
	private void showTeachersInDepartment() {
		Scanner scanner = new Scanner(System.in);
		DepartmentsEntity departments;
		int id;

		System.out.print("Department number: ");
		id = scanner.nextInt();

		if ((departments = session.get(DepartmentsEntity.class, id)) == null) {
			System.out.println("(ERROR) No such teacher.");
			return;
		}

		if (departments.getTeachersByDeptNum().size() == 0) {
			System.out.println("(INFO) The department " + id + " has no teachers.");
			return;
		}

		System.out.println("(INFO) Showing teacher in department " + id + "...");
		for (TeachersEntity teacher : departments.getTeachersByDeptNum()) {
			System.out.println(teacher);
		}
	}

	/**
	 * {@link DepartmentsEntity#DepartmentsEntity(int, String, String) Creates},
	 * {@link Session#persist(Object) persists} and {@link Transaction#commit() inserts}
	 * a new {@link DepartmentsEntity} with the requested data.
	 */
	private void createDepartment() {
		Scanner scanner = new Scanner(System.in);
		int deptNum;
		String name;
		String office;

		System.out.print("Department number: ");
		deptNum = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Department name: ");
		name = scanner.nextLine();
		System.out.print("Department office: ");
		office = scanner.nextLine();

		DepartmentsEntity department = new DepartmentsEntity(deptNum, name, office);
		System.out.println("Inserting " + department + "...");
		session.persist(department);
		try {
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't insert " + department + ".");
		}
	}

	/**
	 * Closes the {@link #session} and prints a message.
	 *
	 * @see Session#close()
	 */
	private void close() {
		System.out.println("(INFO) Closing Hibernate session...");
		session.close();
	}

	public static void main(String[] args) {
		App app = new App();
		int option;

		/* Menu */
		do {
			printWelcomeMessage();
			System.out.println(app.menu);
			option = app.menu.inputMenuOption();
			app.runOption(option);
		} while (option != 11);

		app.close();
	}
}