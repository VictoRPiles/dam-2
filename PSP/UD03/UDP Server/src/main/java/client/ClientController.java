package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		Client client = new Client("localhost", 1024);

		String message = "[Client] Hello, Server!!!";
		try {
			client.sendPacket(message, InetAddress.getByName(client.getHost()), client.getPort());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String response;
		DatagramPacket packet = new DatagramPacket(client.getBuffer(), client.getBuffer().length);
		try {
			response = client.readPacket(packet);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(response);
	}
}