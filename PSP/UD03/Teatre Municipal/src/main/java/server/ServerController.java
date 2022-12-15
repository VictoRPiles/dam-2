package server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author Victor Piles
 */
@SuppressWarnings("InfiniteLoopStatement")
public abstract class ServerController {

	private static final HashMap<String, Integer> butaquesDisponibles = new HashMap<>();
	private static final HashMap<String, Integer> butaquesPreu = new HashMap<>();

	public static void main(String[] args) {
		Server server = new Server(1024);

		// Definim les butaques disponibles
		butaquesDisponibles.put("GAL", 8);
		butaquesDisponibles.put("CEN", 54);
		butaquesDisponibles.put("LAT1", 4);
		butaquesDisponibles.put("LAT2", 4);
		butaquesDisponibles.put("VIP1", 3);
		butaquesDisponibles.put("VIP2", 3);

		// Definim el preu de les butaques
		butaquesPreu.put("GAL", 150);
		butaquesPreu.put("CEN", 80);
		butaquesPreu.put("LAT1", 100);
		butaquesPreu.put("LAT2", 100);
		butaquesPreu.put("VIP1", 250);
		butaquesPreu.put("VIP2", 250);

		try {
			server.start();

			while (true) {
				Socket client = server.getSocket().accept();

				// El servidor analitzarà la cadena ...
				String response = server.readMessage(client);
				System.out.println("Client: " + response);

				// ... i mostrarà per pantalla el missatge del client i contestarà amb la cadena “Hola, soc el servidor”.
				String message = "Hola, soc el servidor!!!";
				server.sendMessage(client, message);

				while (true) {
					response = server.readMessage(client);
					System.out.println("Client: " + response);

					// Quan l'usuari vulga finalitzar l'execució del programa, escriurà per teclat “Fi”
					if (response.equalsIgnoreCase("Fi")) {
						// El servidor contestarà amb el mateix missatge
						server.sendMessage(client, response);
						break;
					}
					else if (response.equalsIgnoreCase("Veure butaques")) {
						server.sendMessage(client, (butaquesDisponibles + " " + butaquesPreu));
					}
					else {
						reservarButaca(server, client, response);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				System.out.println("Tancant connexió...");
				server.close();
			} catch (IOException ignored) {
			}
		}
	}

	/**
	 * Si el servidor té entrades d'aqueix tipus, imprimirà per la seua pantalla el tipus de butaca seleccionat i
	 * enviarà al client la cadena formada per “Reserva (tipusButaca) (*númerodeButaquesLliures).”
	 */
	private static void reservarButaca(Server server, Socket client, String response) throws IOException {
		String message;

		/*
		 El servidor haurà de procedir a interpretar què vol el client i actuar en conseqüència. Si el client envia un
		 missatge que no correspon amb cap opció comentada anteriorment, enviarà el client un missatge d'“Error”.
		*/
		if (!(butaquesDisponibles.containsKey(response))) {
			message = "Error: No existeix aquesta butaca";
			server.sendMessage(client, message);
			return;
		}

		Integer disponibles = butaquesDisponibles.get(response);

		if (disponibles > 0) {
			message = String.format("Reserva (%s) (%d)", response, disponibles);
			butaquesDisponibles.put(response, --disponibles);
		}
		else {
			message = "Butaques esgotades";
		}

		server.sendMessage(client, message);
	}
}