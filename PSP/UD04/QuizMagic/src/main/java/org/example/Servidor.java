package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("Inici servidor en 6000");

        Socket socket;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // FIXME: 12/1/2023
        new ThreadServidor(socket).start();
    }

    private static class ThreadServidor extends Thread {
        private final Socket socket;

        private ThreadServidor(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Cree fil servidor!");
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String nom = in.readLine();
                System.out.println("Nom del client: " + nom);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
