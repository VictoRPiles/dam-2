package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {

	private final DatagramSocket socket;
	private final InetAddress address;
	private final int port;
	private byte[] buffer;

	public Client(InetAddress address, int port) throws SocketException {
		this.socket = new DatagramSocket();
		this.address = address;
		this.port = port;
		buffer = new byte[256];
	}

	public void sendMessage(String message) throws IOException {
		buffer = message.getBytes();

		socket.send(new DatagramPacket(buffer, buffer.length, address, port));
	}

	public String receiveMessage(DatagramPacket packet) throws IOException {
		socket.receive(packet);
		return new String(packet.getData(), 0, packet.getLength());
	}

	public void close() {
		socket.close();
	}
}