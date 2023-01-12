package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author victor
 */
public class Servidor extends Thread {
    private final ServerSocket serverSocket;

    public Servidor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor(new ServerSocket(6000));
        servidor.start();
    }

    @Override
    public void run() {
        System.out.println("Servidor a l'espera...");

        Socket socket;
        try {
            socket = serverSocket.accept();
            System.out.println("Connectat!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream in;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Rebut!");
            Vehicle vehicle = (Vehicle) in.readObject();
            System.out.println("Objecte rebut: " + vehicle);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.close();
            in.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
