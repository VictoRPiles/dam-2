package client;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * @author Victor Piles
 */
@SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
public abstract class ClientController {

	public static void main(String[] args) {
		Client client = new Client("localhost", 1024);
		try {
			System.out.println("============== CLIENT ===============");
			System.out.println("Press Control + C to stop the process");

			while (true) {
				client.init();

				System.out.println(client.getInput().readUTF());
				sleep(1000);

				client.close();
			}
		} catch (IOException e) {
			System.out.println("[Client] (ERROR) " + e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}