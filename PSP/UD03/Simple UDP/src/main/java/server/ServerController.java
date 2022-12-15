package server;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * @author Victor Piles
 */
public abstract class ServerController {

	public static void main(String[] args) {
		Server server = new Server(1024);
		try {
			// Start the server
			server.start();

			while (true) {
				// Receive packet
				DatagramPacket packet = new DatagramPacket(server.getBuffer(), server.getBuffer().length);

				// Read the response
				String response = server.readPacket(packet);
				System.out.printf("[Server] New packet received!!! %n");
				System.out.println("[Client] " + response);

				if (response.equals("/quit")) {
					String message = "[Server] Closing server...";
					server.sendPacket(message, packet.getAddress(), packet.getPort());
					return;
				}

				String message = "[Server] Message received!!!";
				server.sendPacket(message, packet.getAddress(), packet.getPort());
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