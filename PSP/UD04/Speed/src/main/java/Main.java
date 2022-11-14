public class Main {
	public static void main(String[] args) {
		Autobus autobus = new Autobus("6969 BUS");

		Speed accelerar = new Speed("accelerar", autobus);
		Speed frenar = new Speed("frenar", autobus);

		System.out.println("L'autobús '" + autobus.getMatricula() + "' està a la carretera!!!");
		System.out.println("Pressiona 'Control + C' per a parar el programa");

		accelerar.start();
		frenar.start();
	}
}