package org.example.client;

import org.example.objects.SerializableObject;
import org.example.util.Color;

import java.io.*;
import java.net.Socket;

/**
 * @author victor
 */
public class Client implements Runnable {
	private final String HOST;
	private final Integer PORT;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	private final String name;

	public Client(String host, Integer port, String name) {
		this.HOST = host;
		this.PORT = port;
		this.name = name;
	}

	/**
	 * Connecta al client amb el {@link Socket socket}.
	 *
	 * @throws IOException No es pot establir la connexió.
	 */
	private void bindSocket() throws IOException {
		Color.printGreenMessage(name + ": [+] Establint la connexió amb el socket...");
		socket = new Socket(HOST, PORT);
		/* Establir els canals I/O */
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(socket.getOutputStream(), true);
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		objectInputStream = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Desconnecta al client del {@link Socket socket}.
	 */
	private void unbindSocket() {
		Color.printGreenMessage(name + ": [+] Tancant la connexió amb el socket...");

		if (socket == null) {
			return;
		}

		try {
			socket.close();
			bufferedReader.close();
			printWriter.close();
			objectOutputStream.close();
			objectInputStream.close();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error al tancar la connexió amb el socket.");
			throw new RuntimeException();
		}
	}

	@Override
	public void run() {
		/* ########## Establir connexió ########## */
		try {
			bindSocket();
		} catch (IOException e) {
			unbindSocket();
			throw new RuntimeException();
		}

		/* ########## Missatges de salutació ########## */
		try {
			printWriter.println("Hola, soc el client '" + name + "'.");
			printWriter.flush();
		} catch (NullPointerException e) {
			Color.printRedMessage(name + ": [-] Error al enviar missatge al servidor.");
			throw new RuntimeException();
		}
		try {
			Color.printYellowMessage(name + ": [@] Servidor contesta: " + bufferedReader.readLine());
		} catch (NullPointerException | IOException e) {
			Color.printRedMessage(name + ": [-] Error al rebre la resposta del servidor.");
			throw new RuntimeException();
		}

		/* ########## Objecte serialitzat ########## */
		try {
			SerializableObject object = new SerializableObject("Nom de prova", 1234);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
		} catch (IOException | NullPointerException e) {
			Color.printRedMessage(name + ": [-] Error al enviar el objecte al servidor.");
			throw new RuntimeException();
		}
		try {
			Color.printYellowMessage(name + ": [@] Servidor contesta: " + objectInputStream.readObject());
		} catch (ClassNotFoundException | NullPointerException | IOException e) {
			Color.printRedMessage(name + ": [-] Error al rebre el objecte del servidor.");
			throw new RuntimeException();
		}

		/* ########## Tancar els canals I/O ########## */
		try {
			bufferedReader.close();
			printWriter.close();
			objectOutputStream.close();
			objectInputStream.close();
			unbindSocket();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error tancar els canals I/O.");
			throw new RuntimeException();
		}
	}
}
