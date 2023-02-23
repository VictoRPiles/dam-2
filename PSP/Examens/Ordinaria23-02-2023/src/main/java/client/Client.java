package client;


import objects.Car;
import util.Color;
import util.Serializer;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

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
		Color.printGreenMessage(name + ": [+] Establint la connexió amb el servidor...");
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
		Color.printGreenMessage(name + ": [+] Tancant la connexió amb el servidor...");

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

		Car car;
		try {
			car = new Car("Toyota Corolla", "Azul");
			objectOutputStream.writeObject(car);
			objectOutputStream.flush();

			Color.printYellowMessage(name + ": [@] Client envia: " + car);
		} catch (IOException | NullPointerException e) {
			Color.printRedMessage(name + ": [-] Error al enviar el objecte al servidor.");
			throw new RuntimeException();
		}

		try {
			printWriter.println(Arrays.toString(Serializer.serialize(car, "MD5")));
			printWriter.flush();
		} catch (NullPointerException e) {
			Color.printRedMessage(name + ": [-] Error al enviar missatge al servidor.");
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

		/* NOTA: L'objecte Car implementa la funció del Hash en el mètode equals */
		if (car.equals(newCar)){
			Color.printGreenMessage(name + ": [+] Hash MD5 verificat. Les dades rebudes són correctes.");
		}else {
			Color.printRedMessage(name + ": [-] Hash MD5 incorrecte.");
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

	public static void main(String[] args) {
		Client client = new Client("localhost", 1234, "Client");
		new Thread(client).start();
	}
}
