package client;

import java.net.DatagramSocket;

/**
 * @author Victor Piles
 */
public class Client {

	private final DatagramSocket socket;

	public Client(DatagramSocket socket) {
		this.socket = socket;
	}

	public DatagramSocket getSocket() {
		return socket;
	}
}