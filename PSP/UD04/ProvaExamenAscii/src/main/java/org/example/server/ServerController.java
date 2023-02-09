package org.example.server;

/**
 * @author victor
 */
public class ServerController {
	public static void main(String[] args) {
		Server server = new Server(1234);
		new Thread(server).start();
	}
}
