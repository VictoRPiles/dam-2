package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Victor Piles
 */
@SuppressWarnings("unused")
public class Client {

	private Socket socket;
	private final String host;
	private final int port;
	private DataInputStream input;
	private DataOutputStream output;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void init() throws IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());

		System.out.println(this);
	}

	public void close() throws IOException {
		socket.close();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getHost() {
		return host;
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
		return String.format("[Client] (%s) Forwarding to port %d...", host, port);
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