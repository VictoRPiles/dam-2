package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Victor Piles
 */
@SuppressWarnings("unused")
public class Server {

	private ServerSocket socket;
	private final int port;
	private DataInputStream input;
	private DataOutputStream output;

	public Server(int port) {
		this.port = port;
	}

	public void init() throws IOException {
		socket = new ServerSocket(port);
		System.out.println("[Server] Listening to " + port + " port...");
	}

	public void close() throws IOException {
		socket.close();
	}

	public ServerSocket getSocket() {
		return socket;
	}

	public void setSocket(ServerSocket socket) {
		this.socket = socket;
	}

	public int getPort() {
		return port;
	}

	public DataInputStream getInput() {
		return input;
	}

	public void setInput(DataInputStream input) {
		this.input = input;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public void setOutput(DataOutputStream output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "[Server] Listening to " + port + " port...";
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