import client.Client;
import server.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Scanner;

public class QuinDiaVaSer {

	private static final int PORT = 2222;

	private static LocalDate getDateFromUser() {
		Scanner scanner = new Scanner(System.in);
		int day, month, year;
		System.out.print("Introdueix el dia -> ");
		day = scanner.nextInt();
		System.out.print("Introdueix el mes -> ");
		month = scanner.nextInt();
		System.out.print("Introdueix el any -> ");
		year = scanner.nextInt();

		return LocalDate.of(year, month, day);
	}

	public static void main(String[] args) {
		Server server;
		try {
			server = new Server(PORT);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		Client client;
		try {
			InetAddress address = InetAddress.getByName("localhost");
			client = new Client(address, PORT);
		} catch (SocketException | UnknownHostException e) {
			throw new RuntimeException(e);
		}

		LocalDate date = getDateFromUser();

		try {
			client.sendMessage(date.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		server.start();
	}
}