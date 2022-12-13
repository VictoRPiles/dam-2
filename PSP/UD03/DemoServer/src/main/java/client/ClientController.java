package client;

import java.io.IOException;

/**
 * @author Victor Piles
 */
public abstract class ClientController {

	public static void main(String[] args) {
		Client client = new Client("localhost", 1024);
		try {
			client.init();

			System.out.println(client.getInput().readUTF());

			client.close();
		} catch (IOException e) {
			System.out.println("[Client] (ERROR) " + e.getMessage());
		}
	}
}