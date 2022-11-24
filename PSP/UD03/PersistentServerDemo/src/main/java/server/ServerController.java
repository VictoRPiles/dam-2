package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Victor Piles
 */
@SuppressWarnings("InfiniteLoopStatement")
public abstract class ServerController {

	public static void main(String[] args) {
		int count = 0;
		Server server = new Server(1024);
		try {
			System.out.println("============== SERVER ===============");
			System.out.println("Press Control + C to stop the process");
			server.init();

			while (true) {
				Socket client = server.getSocket().accept();
				count++;
				System.out.println("[Server] (INFO) Client " + count + ": " + client);
				server.setOutput(new DataOutputStream(client.getOutputStream()));
				server.getOutput().writeUTF("[Server] Hello you are the client number " + count);
			}
		} catch (IOException e) {
			System.out.println("[Server] (ERROR) " + e.getMessage());
		}
	}
}