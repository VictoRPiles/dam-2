/**
 * Classe d'ajuda per imprimir missatges amb color.
 *
 * @author victor
 */
public class Color {
	@SuppressWarnings("unused")
	private static final String ANSI_RESET = "\u001B[0m";
	@SuppressWarnings("unused")
	private static final String ANSI_BLACK = "\u001B[30m";
	@SuppressWarnings("unused")
	private static final String ANSI_RED = "\u001B[31m";
	@SuppressWarnings("unused")
	private static final String ANSI_GREEN = "\u001B[32m";
	@SuppressWarnings("unused")
	private static final String ANSI_YELLOW = "\u001B[33m";
	@SuppressWarnings("unused")
	private static final String ANSI_BLUE = "\u001B[34m";
	@SuppressWarnings("unused")
	private static final String ANSI_PURPLE = "\u001B[35m";
	@SuppressWarnings("unused")
	private static final String ANSI_CYAN = "\u001B[36m";
	@SuppressWarnings("unused")
	private static final String ANSI_WHITE = "\u001B[37m";

	@SuppressWarnings("unused")
	public static void printGreenMessage(String message) {
		System.out.println(ANSI_GREEN + message + ANSI_RESET);
	}

	@SuppressWarnings("unused")
	public static void printRedMessage(String message) {
		System.out.println(ANSI_RED + message + ANSI_RESET);
	}

	@SuppressWarnings("unused")
	public static void printYellowMessage(String message) {
		System.out.println(ANSI_YELLOW + message + ANSI_RESET);
	}
}
