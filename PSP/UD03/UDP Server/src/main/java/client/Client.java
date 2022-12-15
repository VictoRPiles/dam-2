package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Victor Piles
 */
@SuppressWarnings("unused")
public class Client {

	private DatagramSocket socket;
	private final String host;
	private final int port;
	private byte[] buffer = new byte[256];

	public Client(String host, int port) {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		this.host = host;
		this.port = port;

		System.out.printf("[Client] Forwarding to %s:%d...%n", host, port);
	}

	public void sendPacket(String message, InetAddress address, int port) throws IOException {
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, port);
		socket.send(packet);
	}

	public String readPacket(DatagramPacket packet) throws IOException {
		socket.receive(packet);
		return new String(packet.getData(), 0, packet.getLength());
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	@Override
	public String toString() {
		return String.format("[Client] Forwarding to %s:%d...", host, port);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Client client = (Client) o;

		return host.equals(client.host);
	}

	@Override
	public int hashCode() {
		return host.hashCode();
	}
}