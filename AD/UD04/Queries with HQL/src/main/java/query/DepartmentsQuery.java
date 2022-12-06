package query;

import entity.DepartmentsEntity;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;

/**
 * @author Victor Piles
 */
public class DepartmentsQuery {

	public static Session session;

	/**
	 * Prints in the console the {@link DepartmentsEntity#getDeptNum() department number}, the
	 * {@link DepartmentsEntity#getName() department name}, the {@link DepartmentsEntity#getOffice() office} and the
	 * {@link DepartmentsEntity#getTeachersByDeptNum() number of teachers} belonging to the department.
	 *
	 * @param department The department.
	 */
	public static void showDepartment(DepartmentsEntity department) {
		// @formatter:off
		System.out.println(
				"DeptNum: " + department.getDeptNum() + "\n" +
				"Name: " + department.getName() + "\n" +
				"Teachers Count: " + department.getTeachersByDeptNum().size()
		);
		// @formatter:on
	}

	/**
	 * Uses a {@link Query query} in HQL to retrieve all {@link DepartmentsEntity departments}.
	 *
	 * @return All {@link DepartmentsEntity departments}.
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
	 * Be careful if several results are returned, catch the exception and return only the first result.
	 *
	 * @param patternName The pattern with the name.
	 *
	 * @return The {@link DepartmentsEntity department} whose {@link DepartmentsEntity#getName() name} is LIKE the
	 * pattern introduced as argument.
	 *
	 * @see Query#uniqueResult()
	 */
	public static DepartmentsEntity getDepartmentByName(String patternName) {
		String hql = "from DepartmentsEntity where name like '" + patternName + "'";

		Query<DepartmentsEntity> query = session.createQuery(hql, DepartmentsEntity.class);
		System.out.println("HQL: " + query.getQueryString());

		try {
			return query.uniqueResult();
		} catch (NonUniqueResultException e) {
			System.err.println("Multiples departments match the name " + patternName);
			return query.setMaxResults(1).uniqueResult();
		}
	}

	public static double getAverageSalaryOfDepartment(String depName) {
		// TODO: 5/12/2022
		return 0;
	}

	public static HashMap<String, Double> getAverageSalaryPerDept() {
		// TODO: 5/12/2022
		return null;
	}
}