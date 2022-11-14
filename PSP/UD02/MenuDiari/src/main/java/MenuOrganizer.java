import java.io.*;
import java.util.ArrayList;

/**
 * @author Victor Piles
 */
public class MenuOrganizer extends Thread {
	/** Fitxer d'entrada */
	private final String inputMenu;
	/** Fitxer d'eixida */
	private final String outputMenu;
	/** Cadena a buscar */
	private final String regex;

	public MenuOrganizer(String name, String inputMenu, String outputMenu, String regex) {
		super(name);
		this.inputMenu = inputMenu;
		this.outputMenu = outputMenu;
		this.regex = regex;
	}

	/**
	 * Llig els plats del {@link #inputMenu menu} que comencen per {@link #regex l'index} especificat.
	 *
	 * @return Torna els plats.
	 *
	 * @throws IOException Si hi ha algun error de entrada/eixida.
	 */
	private String[] readFromMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inputMenu));
		String line;
		ArrayList<String> lines = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			/* si la línia comença per l'expressió */
			if (line.startsWith(regex)) {
				/* afegeix la segona part de la cadena */
				lines.add(line.split("-", 2)[1]);
			}
		}

		System.out.printf("(INFO) Llegint els %ss plats: %s...\n", regex, lines);

		reader.close();

		return lines.toArray(new String[0]);
	}

	/**
	 * Escriu al {@link #outputMenu fitxer} els plats.
	 *
	 * @param lines Conjunt de plats a escriure.
	 *
	 * @throws IOException Si hi ha algun error de entrada/eixida.
	 */
	private void writeToMenu(String[] lines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputMenu));

		for (String line : lines) {
			writer.write(line + "\n");
		}

		System.out.printf("(INFO) Escrivint els %ss plats...\n", regex);

		writer.close();
	}

	/**
	 * Crida als mètodes per a llegir i escriure al menu.
	 *
	 * @see #readFromMenu()
	 * @see #writeToMenu(String[])
	 */
	@Override
	public void run() {
		String[] lines;
		try {
			lines = readFromMenu();
			writeToMenu(lines);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}