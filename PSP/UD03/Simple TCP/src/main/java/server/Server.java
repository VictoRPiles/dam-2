package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Victor Piles
 */
public class Server {

	private ServerSocket socket;
	private final int port;
	@SuppressWarnings("FieldCanBeLocal")
	private DataInputStream in;
	@SuppressWarnings("FieldCanBeLocal")
	private DataOutputStream out;

	public Server(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		socket = new ServerSocket(port);

		System.out.println(this);
	}

	public void close() throws IOException {
		socket.close();
	}

	public void sendMessage(Socket client, String message) throws IOException {
		out = (new DataOutputStream(client.getOutputStream()));
		out.writeUTF(message);
	}

	public String readMessage(Socket client) throws IOException {
		in = (new DataInputStream(client.getInputStream()));
		return in.readUTF();
	}

	public ServerSocket getSocket() {
		return socket;
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