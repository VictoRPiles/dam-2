package org.example.client;

import org.example.objects.Car;
import org.example.util.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author victor
 */
public class Client implements Runnable {
	private final String HOST;
	private final Integer PORT;
	private Socket socket;
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

		/* ########## Objecte serialitzat ########## */
		Car car;
		try {
			car = new Car("Toyota Corolla", "Azul");
			objectOutputStream.writeObject(car);
			objectOutputStream.flush();
		} catch (IOException | NullPointerException e) {
			Color.printRedMessage(name + ": [-] Error al enviar el objecte al servidor.");
			throw new RuntimeException();
		}

		Car newCar;
		try {
			newCar = (Car) objectInputStream.readObject();
			Color.printYellowMessage(name + ": [@] Servidor contesta: " + newCar);
		} catch (ClassNotFoundException | NullPointerException | IOException e) {
			Color.printRedMessage(name + ": [-] Error al rebre el objecte del servidor.");
			throw new RuntimeException();
		}

		/* El hash es troba al mètode equals */
		if (car.equals(newCar)) {
			Color.printGreenMessage(name + ": [+] Hash MD5 correcte.");
		} else {
			Color.printRedMessage(name + ": [-] Hash MD5 incorrecte.");
		}

		/* ########## Tancar els canals I/O ########## */
		try {
			objectOutputStream.close();
			objectInputStream.close();
			unbindSocket();
		} catch (IOException e) {
			Color.printRedMessage(name + ": [-] Error tancar els canals I/O.");
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		Client client = new Client("localhost", 1234, "Client");
		new Thread(client).start();
	}
}
