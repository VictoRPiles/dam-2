import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int nCars;
		String[] carNames;

		System.out.println("=========================");
		System.out.println("I        CARRERA        I");
		System.out.println("=========================");

		System.out.print("Introdueix la quantitat de cotxes -> ");
		nCars = scanner.nextInt();

		carNames = new String[nCars];

		scanner.nextLine(); /* neteja buffer */
		for (int i = 0; i < carNames.length; i++) {
			System.out.printf("Introdueix el nom per al cotxe %s -> ", i + 1);
			carNames[i] = scanner.nextLine();
		}

		for (String carName : carNames) {
			new Car(carName).start();
		}
	}
}