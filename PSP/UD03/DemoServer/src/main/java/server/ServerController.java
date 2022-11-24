package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Victor Piles
 */
public abstract class ServerController {

	public static void main(String[] args) {
		Server server = new Server(1024);
		try {
			server.init();

			Socket client = server.getSocket().accept();
			server.setOutput(new DataOutputStream(client.getOutputStream()));
			server.getOutput().writeUTF("Hello World");

			server.close();
		} catch (IOException e) {
			System.out.println("[Server] (ERROR) " + e.getMessage());
		}
	}
}