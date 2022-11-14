import java.util.Scanner;

/**
 * @author Victor Piles
 */
public class Main {
	public static void main(String[] args) {
		Comptador comptador = new Comptador();
		Scanner scanner = new Scanner(System.in);
		int suma, resta;

		System.out.print("Quantitat a sumar: ");
		suma = scanner.nextInt();
		System.out.print("Quantitat a restar: ");
		resta = scanner.nextInt();

		new ThreadSuma("Thread Suma", comptador, suma).start();
		new ThreadResta("Thread Resta", comptador, resta).start();
	}
}