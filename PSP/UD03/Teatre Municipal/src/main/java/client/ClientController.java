package client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("No has introduït la IP del servidor.");
			return;
		}
		// El client (socket TCP) rep com a paràmetre del programa el nom de la màquina on es troba el servidor.
		Client client = new Client(args[0], 1024);
		try {
			Scanner scanner = new Scanner(System.in);

			// El client es connecta al servidor ...
			client.startConnection();

			// ... i li enviarà un missatge de salutació formal mitjançant la cadena “Hola”.
			String message = "Hola";
			client.sendMessage(message);

			/*
			 El client imprimirà sempre els missatges que rep del servidor anteposant la cadena "Servidor:".
			 Igual en el client que anteposarà “Client: ”.
			*/
			String response = client.readMessage();
			System.out.println("Servidor: " + response);

			/*
			 El client l'imprimirà per pantalla podent repetir el mateix procés (enviar un missatge al servidor i
			 esperar a la contestació) tantes vegades com desitge.
			*/
			do {
				// A partir d'ara, l'usuari introduirà un tipus de butaca i el client l'enviarà al servidor.
				System.out.print("Introdueix un tipus de butaca: ");
				message = scanner.nextLine();
				client.sendMessage(message);

				response = client.readMessage();
				System.out.println("Servidor: " + response);
			} while (!response.equalsIgnoreCase("Fi"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				client.closeConnection();
			} catch (IOException ignored) {
			}
		}
	}
}