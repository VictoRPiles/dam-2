/**
 * Lògica de programa. Conté el mètode {@link #main(String[])}.
 *
 * @author Victor Piles
 */
public class App {
	public static void main(String[] args) {
		String inputMenu = "src/main/resources/menu.txt";

		MenuOrganizer[] threads = {
				new MenuOrganizer("primers", inputMenu, "src/main/resources/primers.txt", "1"),
				new MenuOrganizer("segons", inputMenu, "src/main/resources/segons.txt", "2"),
				new MenuOrganizer("postres", inputMenu, "src/main/resources/postres.txt", "3")
		};

		for (MenuOrganizer thread : threads) {
			thread.start();
		}
	}
}