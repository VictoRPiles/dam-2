import entity.DepartamentosEntity;
import entity.EmpleadosEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class App {

	private final SessionFactory sessionFactory;
	private final Session session;
	private final Transaction transaction;

	/**
	 * Initializes hibernate.
	 */
	public App() {
		sessionFactory = HibernateUtil.getSession();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	/**
	 * Prints the {@link DepartamentosEntity#getDnombre()} department name}.
	 * <p>
	 * Prints the employee {@link EmpleadosEntity#getApellido() surname} and {@link EmpleadosEntity#getApellido() salary}
	 * for each {@link DepartamentosEntity#getEmpleadosByDeptNo() employee in the deparment}.
	 *
	 * @param department The department.
	 */
	private void dataFromDepartment(DepartamentosEntity department) {
		System.out.println("============================");
		System.out.println("   Datos del departamento   ");
		System.out.println("============================");

		System.out.println("Nombre: " + department.getDnombre());
		System.out.println("Localidad: " + department.getLoc());
		System.out.println("Empleados: " + department.getEmpleadosByDeptNo().size() + " {");
		for (EmpleadosEntity empleado : department.getEmpleadosByDeptNo()) {
			System.out.println("\tApellido: " + empleado.getApellido() + ", Salario: " + empleado.getSalario());
		}
		System.out.println("}");
	}

	/**
	 * Requests data and inserts a new {@link EmpleadosEntity employee} in the {@link DepartamentosEntity department}.
	 *
	 * @param department The department.
	 */
	private void insertNewEmployee(DepartamentosEntity department) {
		Scanner scanner = new Scanner(System.in);
		int empNo;
		String surname;
		double salary;

		System.out.print("ID Empleado: ");
		empNo = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */

		System.out.print("Apellido: ");
		surname = scanner.nextLine();

		System.out.print("Salario: ");
		salary = scanner.nextDouble();

		session.persist(new EmpleadosEntity(empNo, surname, salary, department.getDeptNo()));

		transaction.commit();
	}

	/**
	 * Closes hibernate.
	 */
	private void closeHibernate() {
		session.close();
		sessionFactory.close();
	}

	public static void main(String[] args) {
		App app = new App();

		DepartamentosEntity department = app.session.get(DepartamentosEntity.class, 1);

		app.dataFromDepartment(department);
		app.insertNewEmployee(department);

		app.closeHibernate();
	}
}