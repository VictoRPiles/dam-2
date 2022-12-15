package client;

import java.io.IOException;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 1024);
		try {
			// Connect to the server
			client.startConnection();

			// Read a message from the server
			String response = client.readMessage();
			System.out.println(response);

			// Send a message to the server
			String message = "[Client] Hello, Server!!!";
			client.sendMessage(message);
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