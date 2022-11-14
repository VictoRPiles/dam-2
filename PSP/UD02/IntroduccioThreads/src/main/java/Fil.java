/**
 * @author Victor Piles
 */
public class Fil extends Thread {
	public Fil(String name) {
		super(name);
	}

	@Override
	public void run() {
		String nom = this.getName();

		for (int valor = 0; valor <= 4; valor++) {
			System.out.printf("%s amb valor = %d\n", nom, valor);
		}
	}
}