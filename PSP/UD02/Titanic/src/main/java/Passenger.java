import java.lang.reflect.Constructor;
import java.util.InputMismatchException;

/**
 * @author Victor Piles
 */
public class Passenger
{
	private final int travelClass;
	private final String surname;
	private final String name;
	private final int age;
	private final String boardingPlace;
	private final boolean survived;

	/**
	 * Constructor with all parameters.
	 *
	 * @param travelClass   Travel class, should be 1, 2 or 3.
	 * @param surname       Passenger surname.
	 * @param name          Passenger name.
	 * @param age           Passenger age.
	 * @param boardingPlace Passenger boarding place.
	 * @param survived      If the passenger has survived.
	 */
	public Passenger(int travelClass, String surname, String name, int age, String boardingPlace, boolean survived)
	{
		this.travelClass = travelClass;
		this.surname = surname;
		this.name = name;
		this.age = age;
		this.boardingPlace = boardingPlace;
		this.survived = survived;
	}

	/**
	 * Constructor from a given array of parameters.
	 * <p>
	 * If the array of parameters length doesn't match constructor parameter number, throws an {@link InputMismatchException}.
	 *
	 * @param parameters Array of parameters.
	 *
	 * @see Class#getDeclaredConstructors()
	 * @see Constructor#getParameterCount()
	 */
	public Passenger(String[] parameters)
	{
		/* Quantitat d'arguments al constructor */
		int constructorParameterCount = this.
				getClass().
				getDeclaredConstructors()[0].
				getParameterCount();

		if (parameters.length != constructorParameterCount) {
			throw new InputMismatchException("Argument number doesn't match constructor parameter number");
		}

		this.travelClass = Integer.parseInt(parameters[0]);
		this.surname = parameters[1];
		this.name = parameters[2];
		this.age = Integer.parseInt(parameters[3]);
		this.boardingPlace = parameters[4];
		this.survived = parameters[5].equals("S");
	}

	public int getTravelClass()
	{
		return travelClass;
	}

	public boolean hasSurvived()
	{
		return survived;
	}

	@Override
	public String toString()
	{
		return "Passenger{" +
				"travelClass=" + travelClass +
				", surname='" + surname + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				", boardingPlace='" + boardingPlace + '\'' +
				", hasSurvived=" + survived +
				'}';
	}
}