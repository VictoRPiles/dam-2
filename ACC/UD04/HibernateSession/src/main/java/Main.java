import org.hibernate.SessionFactory;

/**
 * @author Victor Piles
 */
public class Main {
	public static void main(String[] args) {
		SessionFactory session = HibernateUtil.getSession();
	}
}