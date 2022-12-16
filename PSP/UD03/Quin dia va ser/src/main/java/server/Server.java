package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class Server extends Thread {

	private DatagramSocket socket;
	private boolean running;
	private int port;
	private byte[] buffer;

	public Server(int port) throws SocketException {
		socket = new DatagramSocket(port);
		this.port = port;
		buffer = new byte[256];
	}

	public void sendMessage(String message, InetAddress address, int port) throws IOException {
		buffer = message.getBytes();

		socket.send(new DatagramPacket(buffer, buffer.length, address, port));
	}

	public String receiveMessage(DatagramPacket packet) throws IOException {
		socket.receive(packet);
		return new String(packet.getData(), 0, packet.getLength());
	}

	@Override
	public void run() {
		running = true;

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		String message;
		try {
			message = receiveMessage(packet);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(message);

		// TODO: 12/15/22 Tornar el dia de la data
	}
}