package org.example.server;

import org.example.objects.SerializableObject;
import org.example.util.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author victor
 */
public class Server implements Runnable {
	private final Integer PORT;
	private ServerSocket serverSocket;


	public Server(int port) {
		this.PORT = port;
	}

	/**
	 * Connecta al servidor amb el {@link ServerSocket server socket}.
	 *
	 * @throws IOException No es pot establir la connexió.
	 */
	private void bindServerSocket() throws IOException {
		Color.printGreenMessage("Servidor: [+] Establint la connexió amb el server socket...");
		serverSocket = new ServerSocket(PORT);
	}


	/**
	 * Desconnecta al servidor del {@link ServerSocket server socket}.
	 */
	private void unbindServerSocket() {
		Color.printGreenMessage("Servidor: [+] Tancant la connexió amb el server socket...");
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al tancar la connexió amb el server socket.");
				throw new RuntimeException();
			}
		}
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		/* ########## Establir connexió ########## */
		try {
			bindServerSocket();
		} catch (IOException e) {
			Color.printRedMessage("Servidor: [-] Error al establir la connexió amb el server socket.");
			unbindServerSocket();
			throw new RuntimeException();
		}

		while (true) {
			Color.printGreenMessage("Servidor: [+] Esperant clients...");
			try {
				Socket socketClient = serverSocket.accept();
				Color.printGreenMessage("Servidor: [+] Nou client connectat: " + socketClient.getInetAddress().getHostAddress());
				new Thread(new ClientHandler(socketClient)).start();
			} catch (IOException | NullPointerException e) {
				Color.printRedMessage("Servidor: [-] Error al establir la connexió amb el client.");
				unbindServerSocket();
				throw new RuntimeException();
			}
		}
	}

	private static class ClientHandler implements Runnable {
		private final Socket socketClient;
		@SuppressWarnings("FieldCanBeLocal")
		private BufferedReader bufferedReader;
		private PrintWriter printWriter;
		private ObjectOutputStream objectOutputStream;
		private ObjectInputStream objectInputStream;

		public ClientHandler(Socket socketClient) {
			this.socketClient = socketClient;
		}

		@Override
		public void run() {
			/* ########## Establir els canals I/O ########## */
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				printWriter = new PrintWriter(socketClient.getOutputStream(), true);
				objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
				objectInputStream = new ObjectInputStream(socketClient.getInputStream());
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al establir els canals I/O.");
				throw new RuntimeException();
			}

			/* ########## Missatges de salutació ########## */
			try {
				Color.printYellowMessage("Servidor: [@] Client envia: " + bufferedReader.readLine());
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al rebre la resposta del client.");
				throw new RuntimeException(e);
			}
			printWriter.println("Hola, soc el servidor.");
			printWriter.flush();

			/* ########## Objecte serialitzat ########## */
			SerializableObject object;
			try {
				object = (SerializableObject) objectInputStream.readObject();
				Color.printYellowMessage("Servidor: [@] Client envia: " + object);
			} catch (IOException | ClassNotFoundException e) {
				Color.printRedMessage("Servidor: [-] Error al rebre el objecte del client.");
				throw new RuntimeException(e);
			}
			try {
				object.setName("Nou nom");
				object.setNumber(4321);
				objectOutputStream.writeObject(object);
				objectOutputStream.flush();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al enviar el objecte al client.");
				throw new RuntimeException(e);
			}

			/* ########## Tancar els canals I/O ########## */
			try {
				bufferedReader.close();
				printWriter.close();
				objectOutputStream.close();
				objectInputStream.close();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error tancar els canals I/O.");
				throw new RuntimeException();
			}

			/* ########## Tancar la connexió amb el client ########## */
			try {
				socketClient.close();
			} catch (IOException e) {
				Color.printRedMessage("Servidor: [-] Error al tancar la connexió amb el socket del client.");
				throw new RuntimeException();
			}
		}
	}
}
