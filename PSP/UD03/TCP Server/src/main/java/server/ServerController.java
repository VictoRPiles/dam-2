package server;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Victor Piles
 */
@SuppressWarnings("InfiniteLoopStatement")
public abstract class ServerController {

	public static void main(String[] args) {
		Server server = new Server(1024);
		try {
			// Start the server
			server.start();

			while (true) {
				// Wait until a client is connected
				Socket client = server.getSocket().accept();
				System.out.printf("[Server] New client connected!!! %n");

				// Send a message to the client
				String message = "[Server] You are connected!!!";
				server.sendMessage(client, message);

				// Read a message from the client
				String response = server.readMessage(client);
				System.out.println(response);
			}
		} catch (IOException e) {
			System.out.printf("[Server] %s%n", e.getMessage());
		} finally {
			try {
				// Close the server
				server.close();
			} catch (IOException ignored) {
			}
		}
	}
}