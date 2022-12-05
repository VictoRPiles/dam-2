package query;

import entity.DepartmentsEntity;
import org.hibernate.Session;

import java.util.HashMap;

/**
 * @author Victor Piles
 */
public class DepartmentsQuery {

	public static Session session;

	/**
	 * Prints in the console the department number, the department name,
	 * the office and the number of teachers belonging to the department.
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

	public static DepartmentsEntity[] getAllDepartments() {
		// TODO: 5/12/2022
		return null;
	}

	public static DepartmentsEntity getDepartmentByName(String patternName) {
		// TODO: 5/12/2022
		return null;
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