import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class Main {
	private static ArrayList<Compte> comptes = new ArrayList<>();

	/**
	 * Imprimeix les opcions del programa. Permet escollir-ne una.
	 *
	 * @return Opció escollida per l'usuari.
	 */
	private static int opcions() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("==============================");
		System.out.println("1) Donar de alta un compte");
		System.out.println("2) Resumen de comptes");
		System.out.println("3) Traure diners del compte");
		System.out.println("4) Eixir");
		System.out.println("==============================");
		System.out.println();
		System.out.print("Opció: ");

		return scanner.nextInt();
	}


	/**
	 * Demana les dades per a donar d'alta un {@link Compte}.
	 *
	 * @return {@link Compte} creat amb les dades.
	 *
	 * @see Compte#Compte(List, float, boolean)
	 */
	public static Compte altaCompte() {
		Scanner scanner = new Scanner(System.in);
		int nBeneficiaris;
		Client[] beneficiaris;

		System.out.print("Nombre de beneficiaris: ");
		nBeneficiaris = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */
		beneficiaris = new Client[nBeneficiaris];

		for (int i = 0; i < nBeneficiaris; i++) {
			String nom;
			int edat;
			boolean espanyol;

			System.out.println("Beneficiari " + (i + 1));
			System.out.print("Nom del beneficiari: ");
			nom = scanner.nextLine();

			System.out.print("Edat del beneficiari: ");
			edat = scanner.nextInt();
			scanner.nextLine(); /* flush buffer */

			System.out.print("Nacionalitat del beneficiari espanyola? (S/N): ");
			espanyol = scanner.nextLine().equalsIgnoreCase("s");

			beneficiaris[i] = new Client(nom, edat, espanyol);
		}

		return new Compte(List.of(beneficiaris), 100f, false);
	}

	/**
	 * Demana el {@link Compte#getId() id} del {@link Compte} al que es vol accedir.
	 * <p>
	 * Comprova que el {@link Compte} té {@link Compte#getSaldo() diners}.
	 * <p>
	 * Per a cada {@link Compte#getBeneficiaris() beneficiari}, crea un {@link Caixer} fil} i crida al mètode {@link Caixer#start()}.
	 *
	 * @see Compte#Compte(Long)
	 * @see Caixer#Caixer(Compte)
	 */
	public static void traureDiners() {
		Scanner scanner = new Scanner(System.in);
		long id;

		System.out.print("ID del compte: ");
		id = scanner.nextLong();

		Compte compte = comptes.get(comptes.indexOf(new Compte(id)));

		if (compte.getSaldo() < 0) {
			System.out.println("El compte no té diners");
			return;
		}

		compte.getBeneficiaris().forEach(beneficiari -> {
			new Caixer(compte).start();
		});
	}

	/**
	 * Executa les {@link #opcions() opcions} fins que s'ix del programa.
	 */
	public static void main(String[] args) {
		boolean eixir = false;

		do {
			/* ========== OPCIONS ========== */
			switch (opcions()) {
				/* ========== ALTA ========== */
				case 1 -> {
					System.out.println("--- ALTA DE COMPTE ---");
					comptes.add(altaCompte());
				}
				/* ========== RESUM ========== */
				case 2 -> {
					System.out.println("--- RESUM DE COMPTES ---");
					for (Compte compte : comptes) {
						System.out.println(compte);
					}
				}
				/* ========== RETIRADA ========== */
				case 3 -> {
					System.out.println("--- RETIRADA DE DINERS ---");
					traureDiners();
				}
				/* ========== EIXIDA ========== */
				default -> {
					System.out.println("Gràcies, continue endeutant-se...");
					eixir = true;
				}
			}
		} while (!eixir);
	}
}