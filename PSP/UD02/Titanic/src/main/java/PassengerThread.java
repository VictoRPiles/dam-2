import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Piles
 */
public class PassengerThread extends Thread
{
	private final List<Passenger> passengers;
	private final int travelClass;
	private int nPassengers;
	private int nSurvivors;
	private int nDeaths;

	public PassengerThread(List<Passenger> passengers, int travelClass)
	{
		this.passengers = passengers;
		this.travelClass = travelClass;
	}

	/** @return The passengers of the {@link #travelClass travel class}. */
	public List<Passenger> getPassengersOfClass()
	{
		ArrayList<Passenger> passengerOfClass = new ArrayList<>();
		passengers.forEach(passenger -> {
			if (passenger.getTravelClass() == travelClass) {
				passengerOfClass.add(passenger);
			}
		});
		return passengerOfClass;
	}

	@Override
	public void start()
	{
		List<Passenger> passengersOfClass = getPassengersOfClass();

		/* Nombre de passatgers */
		nPassengers = passengersOfClass.size();
		nSurvivors = 0;

		nDeaths = 0;
		/* Comptador de supervivents i morts */
		passengersOfClass.forEach(passenger -> {
			if (passenger.hasSurvived()) nSurvivors++;
			else nDeaths++;
		});
		/* Ratios */
		float survivorsRatio = ((float) nSurvivors / nPassengers) * 100;
		float deathsRatio = ((float) nDeaths / nPassengers) * 100;

		System.out.printf("Passatgers de %d classe: %d, Supervivents: %d (%f), Morts: %d(%f)\n", travelClass, nPassengers, nSurvivors, survivorsRatio, nDeaths, deathsRatio);
	}
}