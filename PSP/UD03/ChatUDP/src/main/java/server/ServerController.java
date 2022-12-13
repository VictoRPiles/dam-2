package server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Victor Piles
 */
@SuppressWarnings("InfiniteLoopStatement")
public class ServerController {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("One argument required: <port>");
			return;
		}

		Server server;
		int port = Integer.parseInt(args[0]);
		try {
			server = new Server(new DatagramSocket(port));
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		byte[] message = new byte[1000];
		while (true) {
			DatagramPacket packet = new DatagramPacket(message, message.length);
			try {
				server.getSocket().receive(packet);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("[CLIENT] - " + new String(packet.getData(), StandardCharsets.UTF_8));
		}
	}
}