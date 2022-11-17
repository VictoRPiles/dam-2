import entity.DepartamentosEntity;
import entity.EmpleadosEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * @author Victor Piles
 */
public class App {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();
		Transaction transaction;

		transaction = session.beginTransaction();

		DepartamentosEntity departamento = new DepartamentosEntity();
		departamento.setDeptNo(100);
		departamento.setDnombre("Mi departamento");

		EmpleadosEntity empleado = new EmpleadosEntity();
		empleado.setEmpNo(100);
		empleado.setApellido("piles");
		empleado.setDepartamentosByDeptNo(departamento);

		session.persist(departamento);
		session.persist(empleado);

		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}