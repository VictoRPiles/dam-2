package query;

import entity.TeachersEntity;
import org.hibernate.Session;

/**
 * @author Victor Piles
 */
public class TeachersQuery {

	public static Session session;

	public static void showTeacher(TeachersEntity teacher) {
		// TODO: 5/12/2022
	}

	public static TeachersEntity[] getAllTeachers() {
		// TODO: 5/12/2022
		return null;
	}

	public static TeachersEntity getMostVeteranTeacher() {
		// TODO: 5/12/2022
		return null;
	}

	public static int setSalary(int newSalary) {
		// TODO: 5/12/2022
		return 0;
	}

	public static int riseSalaryOfSeniors(int numOfYearsToBeSenior, int prctRise) {
		// TODO: 5/12/2022
		return 0;
	}

	public static int deleteTeachersOfDepartment(String depName) {
		// TODO: 5/12/2022
		return 0;
	}
}