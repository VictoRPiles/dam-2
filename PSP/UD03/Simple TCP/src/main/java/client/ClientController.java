package client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		if (args[0] == null) {
			System.out.println("No args provided");
			return;
		}
		Client client = new Client(args[0], 1024);
		try {
			// Connect to the server
			client.startConnection();

			// Read a message from the server
			String response = client.readMessage();
			System.out.println(response);

			// Send a message to the server
			System.out.print("Enter your message: ");
			String message = scanner.nextLine();
			client.sendMessage(message);

			// Read a message from the server
			response = client.readMessage();
			System.out.println(response);
		} catch (IOException e) {
			System.out.printf("[Client] (ERROR) %s%n", e.getMessage());
		} finally {
			try {
				// Close the connection
				client.closeConnection();
			} catch (IOException ignored) {
			}
		}
	}
}