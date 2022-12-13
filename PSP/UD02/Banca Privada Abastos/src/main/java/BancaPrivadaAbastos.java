public class BancaPrivadaAbastos {

	public static void main(String[] args) {
		Compte compte = new Compte(40, 500);
		Persona anna = new Persona("Anna", compte);
		Persona joan = new Persona("Joan", compte);

		anna.start();
		joan.start();
	}
}