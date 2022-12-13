/**
 * @author Victor Piles
 */
public abstract class Robot extends Thread {

	protected final Cinta cinta;
	protected final int tiempo;

	protected Robot(Cinta cinta, int tiempo) {
		this.cinta = cinta;
		this.tiempo = tiempo;
	}
}