package menu;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public abstract class Menu {

	private final HashMap<Integer, String> options = new HashMap<>();

	/**
	 * Adds a new option to the {@link #options} list.
	 *
	 * @param key   Key of the option.
	 * @param value Value of the option.
	 *
	 * @return True if the option is added, false if the option key is duplicated.
	 */
	public boolean addOption(int key, String value) {
		if (options.containsKey(key)) {
			return false;
		}
		options.put(key, value);
		return true;
	}

	/**
	 * Check if the key passed is already being used.
	 *
	 * @param key The key to evaluate.
	 *
	 * @return If the key is already being used.
	 */
	public boolean containsOptionWithKey(int key) {
		return options.containsKey(key);
	}

	/**
	 * Asks the user for a menu option.
	 * <p>
	 * If the option is not in the {@link #options}, shows a message and recursively asks again.
	 *
	 * @return A valid menu option.
	 */
	public int inputMenuOption() {
		Scanner scanner = new Scanner(System.in);
		int option;

		System.out.print("Choose a menu option -> ");
		option = scanner.nextInt();
		scanner.nextLine(); /* flush buffer */

		if (this.containsOptionWithKey(option)) {
			return option;
		} else {
			System.out.println("Invalid menu option");
			return inputMenuOption();
		}
	}

	public HashMap<Integer, String> getOptions() {
		return options;
	}

	@Override
	public String toString() {
		StringBuilder message = new StringBuilder();

		for (int i = 1; i <= getOptions().size(); i++) {
			message.append(String.format("%02d", i)).append(" - ").append(options.get(i)).append("\n");
		}

		return message.toString();
	}
}