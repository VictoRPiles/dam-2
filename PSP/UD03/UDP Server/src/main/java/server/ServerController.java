package server;

import java.io.IOException;
import java.net.DatagramPacket;

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
				// Receive packet
				DatagramPacket packet = new DatagramPacket(server.getBuffer(), server.getBuffer().length);

				// Read the response
				String response = server.readPacket(packet);
				System.out.printf("[Server] New packet received!!! %n");
				System.out.println(response);

				// Sent a packet
				String message = "[Server] You are connected!!!";
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