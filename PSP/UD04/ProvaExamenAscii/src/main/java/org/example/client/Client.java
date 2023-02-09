package org.example.client;

import org.example.util.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author victor
 */
public class Client implements Runnable {
	private final String HOST;
	private final Integer PORT;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

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

		/* ########## Missatges ########## */
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Nombre de línies -> ");
			int nombreDeLinies = scanner.nextInt();

			printWriter.println(nombreDeLinies);
			printWriter.flush();

			for (int i = 0; i < nombreDeLinies; i++) {
				System.out.print("Missatge -> ");
				String missatge = scanner.next();
				printWriter.println(missatge);
				printWriter.flush();
			}

			for (int i = 0; i < nombreDeLinies; i++) {
				Color.printYellowMessage(name + ": [@] Servidor contesta: " + bufferedReader.readLine());
			}
		} catch (NullPointerException | IOException e) {
			Color.printRedMessage(name + ": [-] Error de comunicació amb el servidor.");
			throw new RuntimeException();
		}

		/* ########## Tancar els canals I/O ########## */
		try {
			bufferedReader.close();
			printWriter.close();
			unbindSocket();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error tancar els canals I/O.");
			throw new RuntimeException();
		}
	}
}
