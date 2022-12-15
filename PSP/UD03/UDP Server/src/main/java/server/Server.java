package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Victor Piles
 */
@SuppressWarnings("unused")
public class Server {

	private DatagramSocket socket;
	private final int port;
	private byte[] buffer = new byte[256];

	public Server(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		socket = new DatagramSocket(port);

		System.out.println(this);
	}

	public void close() throws IOException {
		socket.close();
	}

	public void sendPacket(String message, InetAddress address, int port) throws IOException {
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, port);
		socket.send(packet);
	}

	public String readPacket(DatagramPacket packet) throws IOException {
		socket.receive(packet);
		return new String(packet.getData(), 0, packet.getLength());
	}

	public InetAddress getAddressFromPacket(DatagramPacket packet) {
		return packet.getAddress();
	}

	public int getPortFromPacket(DatagramPacket packet) {
		return packet.getPort();
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
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
		return String.format("[Server] Listening to port %d...", port);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Server server = (Server) o;

		return port == server.port;
	}

	@Override
	public int hashCode() {
		return port;
	}
}