package server;

import java.net.DatagramSocket;

/**
 * @author Victor Piles
 */
public class Server {

	private final DatagramSocket socket;

	public Server(DatagramSocket socket) {
		this.socket = socket;
	}

	public DatagramSocket getSocket() {
		return socket;
	}
}