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
	private DataInputStream in;
	private DataOutputStream out;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void startConnection() throws IOException {
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());

		System.out.println(this);
	}

	public void closeConnection() throws IOException {
		socket.close();
	}

	public void sendMessage(String message) throws IOException {
		out.writeUTF(message);
	}

	public String readMessage() throws IOException {
		return in.readUTF();
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

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
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