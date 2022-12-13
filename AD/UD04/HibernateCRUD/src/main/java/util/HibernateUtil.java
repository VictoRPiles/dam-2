package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Victor Piles
 */
public abstract class HibernateUtil {

	private final static SessionFactory factory;

	static {
		factory = new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}
}