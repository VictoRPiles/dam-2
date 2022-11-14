import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Piles
 */
public class PassengerController
{
	private final ArrayList<Passenger> passengers = new ArrayList<>();

	/**
	 * Reads all lines from a CSV file and creates a {@link Passenger} from each row values.
	 * <p>
	 * Adds the {@link Passenger} to the {@link #passengers} {@link ArrayList}.
	 *
	 * @param path The path to the CSV file.
	 *
	 * @throws IOException  If the file can't be read.
	 * @throws CsvException If an CSV error happens.
	 * @see Passenger#Passenger(String[])
	 */
	public void loadFromCsvFile(String path) throws IOException, CsvException
	{
		try (CSVReader reader =
					 new CSVReaderBuilder(
							 new FileReader(path))
							 /* Ometre la capçalera */
							 .withSkipLines(1)
							 .build()) {
			/* Llegir les línies */
			List<String[]> rows = reader.readAll();
			/* Crear els passatgers */
			rows.forEach(row -> passengers.add(new Passenger(row)));
		}
	}

	public ArrayList<Passenger> getPassengers()
	{
		return passengers;
	}

	@Override
	public String toString()
	{
		StringBuilder passengersBuilder = new StringBuilder("\n");

		passengers.forEach(passenger -> passengersBuilder.append("\t").append(passenger).append("\n"));

		return "PassengerController{" +
				"passengers=" + passengersBuilder +
				'}';
	}
}