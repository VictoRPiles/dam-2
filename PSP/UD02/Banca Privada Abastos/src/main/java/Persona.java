import java.util.Random;

public class Persona extends Thread {

	private final String nom;
	private final Compte compte;

	public Persona(String nom, Compte compte) {
		this.nom = nom;
		this.compte = compte;
	}

	/**
	 * Genera un nombre random entre 1 i 500.
	 *
	 * @return El nombre.
	 */
	private int generarRandom() {
		return (int) (new Random().nextDouble() * 501.0);
	}

	/**
	 * Fa dos ingressos i dos retirades alternadament.
	 */
	@Override
	public void run() {
		// Ingrés 1
		compte.ingressar(nom, generarRandom());
		// Retirada 1
		compte.retirar(nom, generarRandom());
		// Ingrés 2
		compte.ingressar(nom, generarRandom());
		// Retirada 2
		compte.retirar(nom, generarRandom());
	}
}