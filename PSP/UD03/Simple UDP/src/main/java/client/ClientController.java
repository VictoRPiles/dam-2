package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		if (args == null) {
			System.out.println("No args provided");
			return;
		}

		Client client = new Client(args[0], Integer.parseInt(args[1]));

		System.out.print("Enter your message: ");
		String message = scanner.nextLine();
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