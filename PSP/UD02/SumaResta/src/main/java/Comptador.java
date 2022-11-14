/**
 * @author Victor Piles
 */
public class Comptador {
	private int valor = 100;

	/**
	 * Incrementa el {@link #valor} en 1.
	 */
	public void incrementar() {
		valor++;
	}

	/**
	 * Decrementa el {@link #valor} en 1.
	 */
	public void decrementar() {
		valor--;
	}

	@Override
	public String toString() {
		return "Comptador{" +
				"valor=" + valor +
				'}';
	}
}