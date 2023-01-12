import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Victor Piles
 */
public class Ramonix extends Thread implements Comu {
    private final ServerSocket serverSocket;
    private int energia;

    public Ramonix(int energia, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.energia = energia;
    }

    public static void main(String[] args) throws IOException {
        /* Creem a Ramonix amb una energia de 500 */
        Ramonix ramonix = new Ramonix(500, new ServerSocket(PORT));
        /* Llancem el fil de Ramonix */
        ramonix.start();
    }

    @Override
    public void run() {
        System.out.println(ANSI_GREEN + "Benvinguts a RAMONIX!" + ANSI_RESET);

        Socket socket;

        /* Llegim el nom del hacker */
        DataInputStream in;
        String nom;
        int atac = 0;
        while (energia > 0) {
            try {
                socket = serverSocket.accept();
                in = new DataInputStream(socket.getInputStream());
                nom = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /* Ajustem els paràmetres de l'atac al hacker corresponent */
            switch (nom) {
                case "P4q1T0":
                case "PaU3T":
                    System.out.println(ANSI_YELLOW + "Atac des de: " + nom + ANSI_RESET);
                    atac = -10;
                    break;
                case "Neo":
                    System.out.println(ANSI_RED + "Atac des de: " + nom + ANSI_RESET);
                    atac = -20;
                    break;
                case "Ab4$t0$":
                    System.out.println(ANSI_GREEN + "Atac des de: " + nom + ANSI_RESET);
                    atac = 10;
                    break;
            }
            /* Ramonix rep l'atac */
            energia += atac;
            System.out.println("**** Energia: " + energia);

            /* Ramonix comunica als hackers si han d'acabar */
            DataOutputStream out;
            try {
                out = new DataOutputStream(socket.getOutputStream());
                out.flush();
                if (energia <= 0) {
                    System.out.println("COMUNIQUE AL CLIENT QUE TANQUE");
                }
                out.writeBoolean(energia <= 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            /* Es tanquen els recursos de la connexió */
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
