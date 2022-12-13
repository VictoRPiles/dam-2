/**
 * @author Victor Piles
 */
public class ThreadSuma extends Thread {
	private final Comptador comptador;
	private final int quantitat;

	public ThreadSuma(String name, Comptador comptador, int quantitat) {
		super(name);
		this.comptador = comptador;
		this.quantitat = quantitat;
	}

	/**
	 * Crida a {@link Comptador#incrementar()} i a {@link Comptador#toString()} {@link #quantitat n} vegades.
	 * <p>
	 * Bloqueja el {@link #comptador} fins que acaba.
	 *
	 * @see Comptador
	 */
	@Override
	public void run() {
		synchronized (comptador) {
			for (int i = 0; i < quantitat; i++) {
				comptador.incrementar();
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