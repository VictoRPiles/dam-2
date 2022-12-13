public class Compte {

	private int saldo;
	private final int maxSaldo;

	public Compte(int saldo, int maxSaldo) {
		this.saldo = saldo;
		this.maxSaldo = maxSaldo;
	}

	/**
	 * Si el saldo més la quantitat és major que el saldo màxim, mostra un missatge d'error i bloqueja el fil.
	 * <p>
	 * Si no, suma la quantitat al saldo, imprimeix el missatge de l'operació i avisa a l'altre fil que ha acabat.
	 *
	 * @param nom       El nom de la persona.
	 * @param quantitat La quantitat.
	 */
	public synchronized void ingressar(String nom, int quantitat) {
		System.out.println("Es van a ingressar diners. Saldo actual: " + saldo + "€");
		// Si el saldo més la quantitat és major que el saldo màxim
		if ((saldo + quantitat) > maxSaldo) {
			// Mostra un missatge d'error
			System.out.println(nom + " vol ingressar " + quantitat + "€. Massa saldo. Saldo actual: " + saldo + "€");
			try {
				// Bloqueja el fil
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return;
		}
		// Suma la quantitat al saldo
		saldo += quantitat;
		// Imprimeix el missatge de l'operació
		System.out.println(nom + " ingressa => " + quantitat + "€. Saldo actual: " + saldo + "€");
		// Avisa a l'altre fil que ha acabat.
		notify();
	}

	/**
	 * Si el saldo menos la quantitat és menor que 0, mostra un missatge d'error i bloqueja el fil.
	 * <p>
	 * Si no, resta la quantitat al saldo, imprimeix el missatge de l'operació i avisa a l'altre fil que ha acabat.
	 *
	 * @param nom       El nom de la persona.
	 * @param quantitat La quantitat.
	 */
	public synchronized void retirar(String nom, int quantitat) {
		System.out.println("Es van a retirar diners. Saldo actual: " + saldo + "€");
		// Si el saldo menor la quantitat és menor que 0
		if ((saldo - quantitat) < 0) {
			// Mostra un missatge d'error
			System.out.println(nom + " vol retirar " + quantitat + "€. No hi ha prou saldo. Saldo actual: " + saldo + "€");
			try {
				// Bloqueja el fil
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return;
		}
		// Resta la quantitat al saldo
		saldo -= quantitat;
		// Imprimeix el missatge de l'operació
		System.out.println(nom + " retira => " + quantitat + "€. Saldo actual: " + saldo + "€");
		// Avisa a l'altre fil que ha acabat.
		notify();
	}
}