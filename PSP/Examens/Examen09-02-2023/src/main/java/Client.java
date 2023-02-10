import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client que envia missatges a un servidor i rep els nombres de verificació.
 *
 * @author victor
 */
public class Client implements Runnable {
	private final String HOST;
	private final Integer PORT;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

	private final String name;

	public static void main(String[] args) {
		Client client = new Client("localhost", 1234, "Client");
		new Thread(client).start();
	}

	public Client(String host, Integer port, String name) {
		this.HOST = host;
		this.PORT = port;
		this.name = name;
	}

	/**
	 * Connecta al client amb el {@link Socket socket}.
	 *
	 * @throws IOException No es pot establir la connexió.
	 */
	private void bindSocket() throws IOException {
		Color.printGreenMessage(name + ": [+] Establint la connexió amb el socket...");
		socket = new Socket(HOST, PORT);
		/* Establir els canals I/O */
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(socket.getOutputStream(), true);
	}

	/**
	 * Desconnecta al client del {@link Socket socket}.
	 */
	private void unbindSocket() {
		Color.printGreenMessage(name + ": [+] Tancant la connexió amb el socket...");

		if (socket == null) {
			return;
		}

		try {
			socket.close();
			bufferedReader.close();
			printWriter.close();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error al tancar la connexió amb el socket.");
			throw new RuntimeException();
		}
	}

	@Override
	public void run() {
		/* ########## Establir connexió ########## */
		try {
			bindSocket();
		} catch (IOException e) {
			unbindSocket();
			throw new RuntimeException();
		}

		/* ########## Intercanvi de missatges ########## */
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Nombre de línies -> ");
			int nombreDeLinies = scanner.nextInt();

			/* Quantitat de línies enviades pel client */
			printWriter.println(nombreDeLinies);
			printWriter.flush();

			Color.printYellowMessage("Client: [@] Client envia: " + nombreDeLinies + " línies.");

			/* Per cada línia, enviar una cadena */
			for (int i = 1; i <= nombreDeLinies; i++) {
				System.out.print("Missatge " + i + " -> ");
				String missatge = scanner.next();
				printWriter.println(missatge);
				printWriter.flush();

				Color.printYellowMessage("Client: [@] Client envia: " + missatge);
			}

			/* Rebre un valor per cada cadena */
			for (int i = 1; i <= nombreDeLinies; i++) {
				Color.printYellowMessage(name + ": [@] Servidor contesta: " + bufferedReader.readLine() + " (cadena " + i + ")");
			}
		} catch (NullPointerException | IOException e) {
			Color.printRedMessage(name + ": [-] Error de comunicació amb el servidor.");
			throw new RuntimeException();
		}

		/* ########## Tancar els canals I/O ########## */
		try {
			bufferedReader.close();
			printWriter.close();
			unbindSocket();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error tancar els canals I/O.");
			throw new RuntimeException();
		}
	}
}
