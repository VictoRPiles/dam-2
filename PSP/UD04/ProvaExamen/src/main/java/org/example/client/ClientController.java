package org.example.client;

/**
 * @author victor
 */
public class ClientController {
	public static void main(String[] args) {
		for (int i = 0; i < 1_000_000; i++) {
			Client client = new Client("localhost", 1234, "Client " + i);
			new Thread(client).start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
