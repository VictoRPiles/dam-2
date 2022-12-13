package query;

import entity.DepartmentsEntity;
import entity.TeachersEntity;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Victor Piles
 */
public class TeachersQuery {

	/**
	 * The Hibernate session.
	 */
	private static Session session;

	/**
	 * Prints in the console the {@link TeachersEntity#getName() name}, {@link TeachersEntity#getSurname() surname},
	 * {@link TeachersEntity#getEmail() email}, {@link TeachersEntity#getStartDate() startDate},
	 * {@link TeachersEntity#getSalary() salary} and the {@link DepartmentsEntity#getName() name of the department} to
	 * which the teacher {@link TeachersEntity#getDepartmentsByDeptNum() belongs}.
	 *
	 * @param teacher The {@link TeachersEntity teacher}.
	 */
	public static void showTeacher(TeachersEntity teacher) {
		// Ensure the data is refreshed
		session.refresh(teacher);

		System.out.printf("Name: %s\nSurname: %s\nEmail: %s\nStart date: %s\nSalary: %d\nDepartment: %s%n",
				teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getStartDate(), teacher.getSalary(), teacher.getDepartmentsByDeptNum().getName());
	}

	/**
	 * Uses a {@link Query query} in HQL to retrieve all {@link TeachersEntity teachers}.
	 *
	 * @return All {@link TeachersEntity teachers}.
	 *
	 * @see TeachersQuery#showTeacher(TeachersEntity)
	 */
	public static TeachersEntity[] getAllTeachers() {
		String hql = "from TeachersEntity";

		Query<TeachersEntity> query = session.createQuery(hql, TeachersEntity.class);
		System.out.println("HQL: " + query.getQueryString());

		return query
				.list()
				.toArray(new TeachersEntity[0]);
	}

	/**
	 * Uses a {@link Query query} in HQL to retrieve the most veteran {@link TeachersEntity teacher} (minimum
	 * {@link TeachersEntity#getStartDate() startDate}).
	 * <p>
	 * Use {@link Query#uniqueResult()}.
	 * <p>
	 * Be careful if several results are  returned, catch the {@link NonUniqueResultException exception} and return only
	 * the first result.
	 *
	 * @return The most veteran {@link TeachersEntity teacher}.
	 *
	 * @see Query#uniqueResult()
	 */
	public static TeachersEntity getMostVeteranTeacher() {
		String hql = "from TeachersEntity where startDate = (select min(startDate) from TeachersEntity)";

		Query<TeachersEntity> query = session.createQuery(hql, TeachersEntity.class);
		System.out.println("HQL: " + query.getQueryString());

		try {
			return query.uniqueResult();
		} catch (NonUniqueResultException e) {
			System.out.println("Multiples teachers have the minimum startDate");
			return query.setMaxResults(1).uniqueResult();
		}
	}

	/**
	 * Uses a {@link Query query} in HQL to set the {@link TeachersEntity#setSalary(Integer) salary} of all
	 * {@link TeachersEntity teachers} to the one introduced as argument.
	 * <p>
	 * Use parameters with the :name syntax.
	 *
	 * @param newSalary The new salary.
	 *
	 * @return The number of updated rows.
	 */
	public static int setSalary(int newSalary) {
		Transaction tx = session.beginTransaction();
		String hql = "update TeachersEntity set salary = :newSalary";

		// Query query = session.createQuery(String s) is deprecated, but I can't find the non-deprecated way to do it
		@Deprecated
		@SuppressWarnings("rawtypes")
		Query query = session
				.createQuery(hql)
				.setParameter("newSalary", newSalary);
		System.out.println("HQL: " + query.getQueryString());

		int rows = query.executeUpdate();
		tx.commit();

		return rows;
	}

	/**
	 * Uses a {@link Query query} in HQL to rise the {@link TeachersEntity#setSalary(Integer) salary} in a percentage
	 * indicated by prctRise of those {@link TeachersEntity teachers} that have a
	 * {@link TeachersEntity#getStartDate() seniority} of at least numOfYearToBeSenior to the one introduced as
	 * argument.
	 * <p>
	 * Use parameters with the :name syntax.
	 *
	 * @param numOfYearsToBeSenior Years to be considered as senior.
	 * @param prctRise             The percentage that the {@link TeachersEntity#setSalary(Integer) salary} will be
	 *                             increased
	 *
	 * @return The number of updated rows.
	 */
	public static int riseSalaryOfSeniors(int numOfYearsToBeSenior, int prctRise) {
		Transaction tx = session.beginTransaction();
		// @formatter:off
		String hql = "update TeachersEntity set salary = (salary + salary * (:prctRise / 100)) " +
					 "where year(current_date) - year(startDate) >= :numOfYearsToBeSenior";
		// @formatter:on

		// Query query = session.createQuery(String s) is deprecated, but I can't find the non-deprecated way to do it
		@Deprecated
		@SuppressWarnings("rawtypes")
		Query query = session
				.createQuery(hql)
				.setParameter("prctRise", prctRise)
				.setParameter("numOfYearsToBeSenior", numOfYearsToBeSenior);
		System.out.println("HQL: " + query.getQueryString());

		int rows = query.executeUpdate();
		tx.commit();

		return rows;
	}

	/**
	 * Uses a {@link Query query} in HQL to delete all {@link TeachersEntity teachers}
	 * {@link TeachersEntity#getDepartmentsByDeptNum() belonging} to the {@link DepartmentsEntity department} whose
	 * {@link DepartmentsEntity#getName() name} is THE SAME AS the name introduced as argument.
	 * <p>
	 * Use parameters with the :name syntax.
	 *
	 * @param depName The {@link DepartmentsEntity#getName() department name}.
	 *
	 * @return The number of updated rows.
	 *
	 * @see DepartmentsQuery#getDepartmentByName(String)
	 */
	public static int deleteTeachersOfDepartment(String depName) {
		Transaction tx = session.beginTransaction();
		DepartmentsEntity department = DepartmentsQuery.getDepartmentByName(depName);

		String hql = "delete TeachersEntity where departmentsByDeptNum = :department";

		// Query query = session.createQuery(String s) is deprecated, but I can't find the non-deprecated way to do it
		@Deprecated
		@SuppressWarnings("rawtypes")
		Query query = session
				.createQuery(hql)
				.setParameter("department", department);
		System.out.println("HQL: " + query.getQueryString());

		int rows = query.executeUpdate();
		tx.commit();

		return rows;
	}

	/**
	 * Setter for {@link #session}.
	 *
	 * @param session The session.
	 */
	public static void setSession(Session session) {
		TeachersQuery.session = session;
	}
}