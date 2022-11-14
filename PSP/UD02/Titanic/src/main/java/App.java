import com.opencsv.exceptions.CsvException;

import java.io.IOException;

/**
 * @author Victor Piles
 */
public class App
{
	private final PassengerController controller = new PassengerController();

	public static void main(String[] args)
	{
		App app = new App();

		System.out.println("BENVINGUT AL TITANIC");

		/* Llegir el CSV */
		try {
			app.controller.loadFromCsvFile("src/main/resources/titanic.csv");
		} catch (IOException | CsvException e) {
			System.out.println("(ERROR) Problema llegint el fitxer: " + e.getMessage());
		}
		/* Crear fils */
		PassengerThread thread01 = new PassengerThread(app.controller.getPassengers(), 1);
		thread01.setPriority(Thread.MAX_PRIORITY);
		PassengerThread thread02 = new PassengerThread(app.controller.getPassengers(), 2);
		PassengerThread thread03 = new PassengerThread(app.controller.getPassengers(), 3);
		/* Iniciar fils */
		thread01.start();
		thread02.start();
		thread03.start();

		System.out.println("FÍ DE LA TRAVESSIA");
	}
}