package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author victor
 */
public class Client extends Thread {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket("localhost", 6000));
        client.start();
    }

    @Override
    public void run() {
        System.out.println("Benvingut a Quiz Magic!");

        System.out.print("Introdueix el nom del jugador: ");
        Scanner scanner = new Scanner(System.in);
        String nom = scanner.nextLine();

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.flush();
            out.writeUTF(nom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
