import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Victor Piles
 */
public class HibernateUtil {
	private static final SessionFactory session;

	static {
		session = new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSession() {
		return session;
	}
}