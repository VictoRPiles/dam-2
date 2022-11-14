/**
 * @author Victor Piles
 */
public class Caixer extends Thread {
	private final Compte compte;

	public Caixer(Compte compte) {
		this.compte = compte;
	}

	public Compte getCompte() {
		return compte;
	}

	/**
	 * Retira mitjançant bitllets de 10 € una quantitat de 30 €.
	 * <p>
	 * Imprimeix el {@link Compte#getSaldo()} saldo} del compte abans i després de fer les operacions.
	 * <p>
	 * Indica si el {@link Compte} s'ha quedat en nombres rojos.
	 */
	@Override
	public void run() {
		synchronized (compte) {
			final int quantitat = 30;

			System.out.println("El compte té: " + compte.getSaldo() + "€");

			for (int i = 10; i <= quantitat; i += 10) {
				System.out.println(getName() + " ha tret " + i + "€");
				compte.setSaldo(compte.getSaldo() - 10);
			}

			System.out.println("El compte té: " + compte.getSaldo() + "€");

			if (compte.getSaldo() < 0) {
				System.out.println("Compte en nombre rojos");
				compte.setDeutor(true);
			}
		}
	}
}