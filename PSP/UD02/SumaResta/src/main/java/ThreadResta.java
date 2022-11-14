/**
 * @author Victor Piles
 */
public class ThreadResta extends Thread {
	private final Comptador comptador;
	private final int quantitat;

	public ThreadResta(String name, Comptador comptador, int quantitat) {
		super(name);
		this.comptador = comptador;
		this.quantitat = quantitat;
	}

	/**
	 * Crida a {@link Comptador#decrementar()} i a {@link Comptador#toString()} {@link #quantitat n} vegades.
	 * <p>
	 * Bloqueja el {@link #comptador} fins que acaba.
	 *
	 * @see Comptador
	 */
	@Override
	public void run() {
		for (int i = 0; i < quantitat; i++) {
			synchronized (comptador) {
				comptador.decrementar();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println(getName());
				System.out.println(comptador);
			}
		}
	}
}