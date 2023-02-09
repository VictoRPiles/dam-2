package org.example.client;

/**
 * @author victor
 */
public class ClientController {
	public static void main(String[] args) {
		Client client = new Client("localhost", 1234, "Client");
		new Thread(client).start();
	}
}
