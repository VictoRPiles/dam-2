/**
 * @author Victor Piles
 */
public class Cinta {

	private int kilos = 0;

	public void cargar(int kilos) {
		this.kilos += kilos;
		System.out.printf("Cinta: Se ponen %d kilos. Quedan %d kilos en la cinta.\n", kilos, this.kilos);
	}

	public void descargar(int kilos) {
		this.kilos -= kilos;
		System.out.printf("Cinta: Se sacan %d kilos. Quedan %d kilos en la cinta.\n", kilos, this.kilos);
	}

	public int getKilos() {
		return kilos;
	}
}