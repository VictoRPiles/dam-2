package org.example;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
        System.out.println("Connectat!");

        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Vehicle vehicle = new Vehicle("1234 MAT", "KUGA", 2003, "Diesel", "12345678X");
        System.out.println("Objecte a enviar: " + vehicle);

        try {
            out.flush();
            out.writeObject(vehicle);
            System.out.println("Enviat!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
