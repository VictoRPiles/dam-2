import entity.DepartmentsEntity;
import entity.TeachersEntity;
import jakarta.persistence.PersistenceException;
import menu.AppMenu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.sql.Date;
import java.time.Year;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
@SuppressWarnings("DuplicatedCode")
public class App {

	private final AppMenu menu;

	private final Session session;
	private Transaction transaction;

	/**
	 * Initializes the {@link Session}.
	 *
	 * @see HibernateUtil#getSessionFactory()
	 */
	public App() {
		this.menu = new AppMenu();

		System.out.println("(INFO) Creating Hibernate session...");
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Prints the program header.
	 */
	private static void printWelcomeMessage() {
		System.out.println("======================================");
		System.out.println("  Act4.2 CRUD with Hibernate objects  ");
		System.out.println("======================================");
	}

	/**
	 * Evaluates the option and calls the corresponding method.
	 *
	 * @param option The option.
	 */
	private void runOption(int option) {
		switch (option) {
			case 1 -> showDepartment();
			case 2 -> showTeacher();
			case 3 -> showTeachersInDepartment();
			case 4 -> createDepartment();
			case 5 -> createTeacherAndDepartment();
			case 6 -> createTeacherInExistingDepartment();
			case 7 -> deleteTeacher();
			case 8 -> deleteDepartment();
			case 9 -> setSalaryOfDepartment();
			case 10 -> riseSalaryOfDepartmentSeniors();
			default -> System.out.println("(ERROR) Invalid option.");
		}
	}

	/**
	 * Print the {@link DepartmentsEntity department} with the requested {@link DepartmentsEntity#getDeptNum() deptNum}.
	 * <p>
	 * Print an error message if it does not exist.
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
	 * Print an error message if it does not exist.
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
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't insert " + department + ".");
			transaction.rollback();
		}
	}


	/**
	 * Creates a {@link DepartmentsEntity department} and a {@link TeachersEntity teacher} from the data requested.
	 */
	private void createTeacherAndDepartment() {
		Scanner scanner = new Scanner(System.in);

		int id, salary;
		String teacherName, surname, email;
		Date startDate;

		int deptNum;
		String departmentName;
		String office;

		System.out.println("Department");
		System.out.print("Department number: ");
		deptNum = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Department name: ");
		departmentName = scanner.nextLine();
		System.out.print("Department office: ");
		office = scanner.nextLine();

		System.out.println("Teacher");
		System.out.print("Teacher ID: ");
		id = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Teacher name: ");
		teacherName = scanner.nextLine();
		System.out.print("Teacher surname: ");
		surname = scanner.nextLine();
		System.out.print("Teacher email: ");
		email = scanner.nextLine();
		System.out.print("Teacher date (YYYY-MM-DD): ");
		startDate = Date.valueOf(scanner.nextLine());
		System.out.print("Teacher salary: ");
		salary = scanner.nextInt();

		DepartmentsEntity department = new DepartmentsEntity(deptNum, departmentName, office);
		TeachersEntity teacher = new TeachersEntity(id, teacherName, surname, email, startDate, deptNum, salary);

		session.persist(department);
		System.out.println("Inserting " + department + "...");
		session.persist(teacher);
		System.out.println("Inserting " + teacher + "...");
		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't insert " + teacher + ".");
			System.out.println("(ERROR) Can't insert " + department + ".");
			transaction.rollback();
		}
	}

	/**
	 * Creates a new {@link TeachersEntity teacher} with the requested data.
	 * <p>
	 * If the {@link TeachersEntity#getDeptNum() department number} is not a valid {@link DepartmentsEntity#getDeptNum() foreign key},
	 * the {@link TeachersEntity teacher} will not be persisted.
	 */
	private void createTeacherInExistingDepartment() {
		Scanner scanner = new Scanner(System.in);
		int id, deptNum, salary;
		String name, surname, email;
		Date startDate;

		System.out.print("Teacher ID: ");
		id = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		System.out.print("Teacher name: ");
		name = scanner.nextLine();
		System.out.print("Teacher surname: ");
		surname = scanner.nextLine();
		System.out.print("Teacher email: ");
		email = scanner.nextLine();
		System.out.print("Teacher date (YYYY-MM-DD): ");
		startDate = Date.valueOf(scanner.nextLine());
		System.out.print("Teacher department number: ");
		deptNum = scanner.nextInt();
		System.out.print("Teacher salary: ");
		salary = scanner.nextInt();

		TeachersEntity teacher = new TeachersEntity(id, name, surname, email, startDate, deptNum, salary);
		System.out.println("Inserting " + teacher + "...");

		if ((session.get(DepartmentsEntity.class, teacher.getDeptNum())) == null) {
			System.out.println("(ERROR) No such department.");
			return;
		}

		session.persist(teacher);
		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't insert " + teacher + ".");
			transaction.rollback();
		}
	}

	/**
	 * Deletes, if exists, the {@link TeachersEntity teacher} with the requested {@link TeachersEntity#getId() id}.
	 */
	private void deleteTeacher() {
		Scanner scanner = new Scanner(System.in);
		int id;
		TeachersEntity teacher;

		System.out.print("Teacher ID: ");
		id = scanner.nextInt();

		if ((teacher = session.get(TeachersEntity.class, id)) == null) {
			System.out.println("(ERROR) No such teacher.");
			return;
		}

		System.out.println("Deleting " + teacher);

		session.remove(teacher);
		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't delete " + teacher + ".");
			transaction.rollback();
		}
	}

	/**
	 * Deletes, if exists, the {@link DepartmentsEntity department} with the requested ç
	 * {@link DepartmentsEntity#getDeptNum() department number}.
	 */
	private void deleteDepartment() {
		Scanner scanner = new Scanner(System.in);
		int deptNum;
		DepartmentsEntity department;

		System.out.print("Department number: ");
		deptNum = scanner.nextInt();

		if ((department = session.get(DepartmentsEntity.class, deptNum)) == null) {
			System.out.println("(ERROR) No such department.");
			return;
		}

		System.out.println("Deleting " + department);

		session.remove(department);
		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't delete " + department + ".");
			transaction.rollback();
		}
	}

	/**
	 * Updates the {@link TeachersEntity#setSalary(Integer) salary} to the requested salary for each {@link TeachersEntity teacher} of the {@link DepartmentsEntity department},
	 * with the requested {@link DepartmentsEntity#getDeptNum() department number}.
	 */
	private void setSalaryOfDepartment() {
		Scanner scanner = new Scanner(System.in);
		int deptNum, salary;
		DepartmentsEntity department;

		System.out.print("Department number: ");
		deptNum = scanner.nextInt();

		System.out.print("New salary: ");
		salary = scanner.nextInt();

		if ((department = session.get(DepartmentsEntity.class, deptNum)) == null) {
			System.out.println("(ERROR) No such department.");
			return;
		}

		System.out.println("Updating salary to " + salary + " for:");
		for (TeachersEntity teacher : department.getTeachersByDeptNum()) {
			teacher.setSalary(salary);
			System.out.println(teacher);
		}

		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't update salary for " + department + ".");
			transaction.rollback();
		}
	}

	/**
	 * Rises the {@link TeachersEntity#getSalary() salary} by a % of each {@link TeachersEntity teacher} of a {@link DepartmentsEntity department}.
	 */
	private void riseSalaryOfDepartmentSeniors() {
		Scanner scanner = new Scanner(System.in);
		int deptNum, salary, years;
		DepartmentsEntity department;

		System.out.print("Department number: ");
		deptNum = scanner.nextInt();

		System.out.print("Rise salary %: ");
		salary = scanner.nextInt();

		System.out.print("Years for senior: ");
		years = scanner.nextInt();

		if ((department = session.get(DepartmentsEntity.class, deptNum)) == null) {
			System.out.println("(ERROR) No such department.");
			return;
		}

		System.out.println("Updating salary to " + salary + "% for:");
		for (TeachersEntity teacher : department.getTeachersByDeptNum()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(teacher.getStartDate());
			int currentYear = Year.now().getValue();
			if (cal.get(Calendar.YEAR) > (currentYear - years)) {
				continue;
			}
			teacher.setSalary((int) (teacher.getSalary() + (teacher.getSalary() * (salary / 100f))));
			System.out.println(teacher);
		}

		try {
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (PersistenceException e) {
			System.out.println("(ERROR) Can't update salary for " + department + ".");
			transaction.rollback();
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