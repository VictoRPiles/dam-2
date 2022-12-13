package query;

import entity.DepartmentsEntity;
import entity.TeachersEntity;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;

/**
 * @author Victor Piles
 */
public class DepartmentsQuery {

	/**
	 * The Hibernate session.
	 */
	private static Session session;

	/**
	 * Prints in the console the {@link DepartmentsEntity#getDeptNum() department number}, the
	 * {@link DepartmentsEntity#getName() department name}, the {@link DepartmentsEntity#getOffice() office} and the
	 * {@link DepartmentsEntity#getTeachersByDeptNum() number of teachers} belonging to the department.
	 *
	 * @param department The department.
	 */
	public static void showDepartment(DepartmentsEntity department) {
		// Ensure the data is refreshed
		session.refresh(department);

		System.out.printf("DeptNum: %d\nName: %s\nTeachers Count: %d%n",
		                  department.getDeptNum(), department.getName(), department.getTeachersByDeptNum().size());
	}

	/**
	 * Uses a {@link Query query} in HQL to retrieve all {@link DepartmentsEntity departments}.
	 *
	 * @return All {@link DepartmentsEntity departments}.
	 *
	 * @see DepartmentsQuery#showDepartment(DepartmentsEntity)
	 */
	public static DepartmentsEntity[] getAllDepartments() {
		/*
		 This is deprecated since 6.0:
		 Query query = session.createQuery("from departments");

		 Instead, use:
		 Query<DepartmentsEntity> query = session.createQuery("from departments", DepartmentsEntity.class);
		 */
		String hql = "from DepartmentsEntity";

		Query<DepartmentsEntity> query = session.createQuery(hql, DepartmentsEntity.class);
		System.out.println("HQL: " + query.getQueryString());

		return query
				.list()
				.toArray(new DepartmentsEntity[0]);
	}

	/**
	 * Uses a {@link Query query} in HQL to retrieve the {@link DepartmentsEntity department} whose
	 * {@link DepartmentsEntity#getName() name} is LIKE the pattern introduced as argument.
	 * <p>
	 * Use parameters with the :name syntax. Use {@link Query#uniqueResult()}.
	 * <p>
	 * Be careful if several results are returned, catch the {@link NonUniqueResultException exception} and return only
	 * the first result.
	 *
	 * @param patternName The pattern with the name.
	 *
	 * @return The {@link DepartmentsEntity department} whose {@link DepartmentsEntity#getName() name} is LIKE the
	 * pattern introduced as argument.
	 *
	 * @see Query#uniqueResult()
	 */
	public static DepartmentsEntity getDepartmentByName(String patternName) {
		String hql = "from DepartmentsEntity where name like :name";

		Query<DepartmentsEntity> query = session
				.createQuery(hql, DepartmentsEntity.class)
				.setParameter("name", patternName);
		System.out.println("HQL: " + query.getQueryString());

		try {
			return query.uniqueResult();
		} catch (NonUniqueResultException e) {
			System.out.println("Multiples departments match the name " + patternName);
			return query.setMaxResults(1).uniqueResult();
		}
	}

	/**
	 * Uses a {@link Query query} in HQL to obtain the average {@link TeachersEntity#getSalary() salary} of the
	 * {@link DepartmentsEntity department} whose {@link DepartmentsEntity#getName() name} is THE SAME AS the name
	 * introduced as argument.
	 * <p>
	 * Use parameters with the :name syntax. Use {@link Query#uniqueResult()}.
	 *
	 * @param depName The name.
	 *
	 * @return The average {@link TeachersEntity#getSalary() salary} of the {@link DepartmentsEntity department}.
	 *
	 * @see Query#uniqueResult()
	 * @see DepartmentsQuery#getDepartmentByName(String)
	 */
	public static double getAverageSalaryOfDepartment(String depName) {
		DepartmentsEntity department = getDepartmentByName(depName);

		// If no department found
		if (department == null) {
			System.out.println("There is no department that matches the name " + depName);
			return 0;
		}

		String hql = "select avg (salary) from TeachersEntity where departmentsByDeptNum = :department";

		Query<Double> query = session
				.createQuery(hql, Double.class)
				.setParameter("department", department);
		System.out.println("HQL: " + query.getQueryString());

		// If no teachers found in department
		if (department.getTeachersByDeptNum().size() == 0) {
			System.out.println("There are no teachers in the department " + depName);
			return 0;
		}

		try {
			return query.uniqueResult();
		}
		// If all teachers have null salary
		catch (NullPointerException e) {
			System.out.printf("All teachers in department %s have null salary%n", depName);
			return 0;
		}
	}

	/**
	 * Uses a {@link Query query} in HQL to obtain the {@link DepartmentsEntity#getName() name} of each department and
	 * its average {@link TeachersEntity#getSalary() salary}.
	 *
	 * @return A {@link HashMap} that relates the {@link DepartmentsEntity#getName() name} of the department with the
	 * average {@link TeachersEntity#getSalary() salary}.
	 *
	 * @see DepartmentsQuery#getAverageSalaryOfDepartment(String)
	 */
	public static HashMap<String, Double> getAverageSalaryPerDept() {
		HashMap<String, Double> departmentNameAndAverageSalary = new HashMap<>();
		for (DepartmentsEntity department : getAllDepartments()) {
			Double salary = getAverageSalaryOfDepartment(department.getName());
			departmentNameAndAverageSalary.put(department.getName(), salary);
		}
		return departmentNameAndAverageSalary;
	}

	/**
	 * Setter for {@link #session}.
	 *
	 * @param session The session.
	 */
	public static void setSession(Session session) {
		DepartmentsQuery.session = session;
	}
}