package client;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * @author Victor Piles
 */
public class ClientController {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Three arguments required: <message> <host> <port>");
			return;
		}

		Client client;
		try {
			client = new Client(new DatagramSocket());
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		byte[] message = args[0].getBytes();
		InetAddress host;
		try {
			host = InetAddress.getByName(args[1]);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		int port = Integer.parseInt(args[2]);

		DatagramPacket packet = new DatagramPacket(message, args[0].length(), host, port);
		try {
			client.getSocket().send(packet);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}