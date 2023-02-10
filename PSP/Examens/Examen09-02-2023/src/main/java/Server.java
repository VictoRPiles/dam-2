import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor multifil que rep missatges i envia els nombres de verificació.
 *
 * @author victor
 */
public class Server implements Runnable {
	private final Integer PORT;
	private ServerSocket serverSocket;

	public static void main(String[] args) {
		Server server = new Server(1234);
		new Thread(server).start();
	}

	public Server(int port) {
		this.PORT = port;
	}

	/**
	 * Connecta al servidor amb el {@link ServerSocket server socket}.
	 *
	 * @throws IOException No es pot establir la connexió.
	 */
	private void bindServerSocket() throws IOException {
		Color.printGreenMessage("Servidor: [+] Establint la connexió amb el server socket...");
		serverSocket = new ServerSocket(PORT);
		Color.printGreenMessage("Servidor: [+] Servidor al port " + PORT + ".");
	}


	/**
	 * Desconnecta al servidor del {@link ServerSocket server socket}.
	 */
	private void unbindServerSocket() {
		Color.printGreenMessage("Servidor: [+] Tancant la connexió amb el server socket...");
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al tancar la connexió amb el server socket.");
				throw new RuntimeException();
			}
		}
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		/* ########## Establir connexió ########## */
		try {
			bindServerSocket();
		} catch (IOException e) {
			Color.printRedMessage("Servidor: [-] Error al establir la connexió amb el server socket.");
			unbindServerSocket();
			throw new RuntimeException();
		}

		/* ########## Acceptar clients ########## */
		while (true) {
			Color.printGreenMessage("Servidor: [+] Esperant clients...");
			try {
				Socket socketClient = serverSocket.accept();
				Color.printGreenMessage("Servidor: [+] Nou client connectat: " + socketClient);
				new Thread(new ClientHandler(socketClient)).start();
			} catch (IOException | NullPointerException e) {
				Color.printRedMessage("Servidor: [-] Error al establir la connexió amb el client.");
				unbindServerSocket();
				throw new RuntimeException();
			}
		}
	}

	private static class ClientHandler implements Runnable {
		private final Socket socketClient;
		@SuppressWarnings("FieldCanBeLocal")
		private BufferedReader bufferedReader;
		@SuppressWarnings("FieldCanBeLocal")
		private PrintWriter printWriter;

		public ClientHandler(Socket socketClient) {
			this.socketClient = socketClient;
			Color.printGreenMessage("Servidor: [+] Creant un fil per al client...");
		}

		@Override
		public void run() {
			/* ########## Establir els canals I/O ########## */
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				printWriter = new PrintWriter(socketClient.getOutputStream(), true);
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al establir els canals I/O.");
				throw new RuntimeException();
			}

			/* ########## Intercanvi de missatges ########## */
			try {
				String missatge = bufferedReader.readLine();

				/* El servidor espera rebre una línia amb un sol nombre */
				int nombreDeLinies = Integer.parseInt(missatge);

				Color.printYellowMessage("Servidor: [@] Client " + socketClient + " envia: " + nombreDeLinies + " línies.");

				/* El nombre de línies te que ser positiu */
				if (nombreDeLinies <= 0) {
					Color.printRedMessage("Servidor: [-] Nombre de línies incorrecte.");
					throw new IllegalArgumentException("Nombre de línies incorrecte");
				}

				/* Rebre 'n' paraules */
				List<String> paraules = new ArrayList<>();
				for (int i = 0; i < nombreDeLinies; i++) {
					String paraula = bufferedReader.readLine();
					paraules.add(paraula);
				}

				/* Calcular la suma dels valors */
				List<Integer> valors = new ArrayList<>();
				for (String paraula : paraules) {
					int valor = 0;
					for (char lletra : paraula.toCharArray()) {
						valor += lletra;
					}
					valors.add(valor);
				}

				/* Servidor contesta al client */
				for (int valor : valors) {
					printWriter.println(valor);
					printWriter.flush();
				}
				Color.printYellowMessage("Servidor: [@] Servidor contesta al client " + socketClient + ": " + valors.size() + " línies.");
			} catch (IOException | NumberFormatException e) {
				Color.printRedMessage("Servidor: [-] Error de comunicació amb el client.");
				throw new RuntimeException(e);
			}

			/* ########## Tancar els canals I/O ########## */
			try {
				bufferedReader.close();
				printWriter.close();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error tancar els canals I/O.");
				throw new RuntimeException();
			}

			/* ########## Tancar la connexió amb el client ########## */
			try {
				socketClient.close();
				Color.printGreenMessage("Servidor: [+] Tancant la connexió amb el client " + socketClient + "...");
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al tancar la connexió amb el socket del client.");
				throw new RuntimeException();
			}
		}
	}
}
