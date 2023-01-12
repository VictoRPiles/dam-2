import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author Victor Piles
 */
public class Atac implements Comu {
    private final Hacker hacker;
    private Socket socket;

    public Atac(Hacker hacker) {
        this.hacker = hacker;
    }

    public boolean atacar() {
        /* El hacker es connecta al socket de Ramonix */
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            // Ignorar
        }
        /* Es tria el color del missatge */
        switch (hacker.getNom()) {
            case "P4q1T0":
            case "PaU3T":
                System.out.println(ANSI_YELLOW + hacker.getNom() + " atacant..." + ANSI_RESET);
                break;
            case "Neo":
                System.out.println(ANSI_RED + hacker.getNom() + " atacant..." + ANSI_RESET);
                break;
            case "Ab4$t0$":
                System.out.println(ANSI_GREEN + hacker.getNom() + " atacant..." + ANSI_RESET);
                break;
        }

        /* El hacker ataca al socket de Ramonix */
        DataOutputStream out;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.flush();
            out.writeUTF(hacker.getNom());
        } catch (SocketException e) {
            /* Si el socket està tancat és perquè el servidor ja ha acabat, per tant, acaba l'atac */
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* El hacker rep el feedback de Ramonix */
        DataInputStream in;
        boolean destruida;
        try {
            in = new DataInputStream(socket.getInputStream());
            destruida = in.readBoolean();
        } catch (SocketException e) {
            /* Si el socket està tancat és perquè el servidor ja ha acabat, per tant, acaba l'atac */
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Es tanquen els recursos de la connexió */
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Si Ramonix comunica que no té energia */
        if (destruida) {
            /* Missatge final, generat amb figlet (http://www.figlet.org/) */
            System.out.println("" +
                    " ____      _    __  __  ___  _   _ _____  __\n" +
                    "|  _ \\    / \\  |  \\/  |/ _ \\| \\ | |_ _\\ \\/ /\n" +
                    "| |_) |  / _ \\ | |\\/| | | | |  \\| || | \\  / \n" +
                    "|  _ <  / ___ \\| |  | | |_| | |\\  || | /  \\ \n" +
                    "|_| \\_\\/_/   \\_\\_|  |_|\\___/|_| \\_|___/_/\\_\\" +
                    "");
            System.out.println("" +
                    " _____  _    _   _  ____  ___    ____   _____        ___   _ \n" +
                    "|_   _|/ \\  | \\ | |/ ___|/ _ \\  |  _ \\ / _ \\ \\      / / \\ | |\n" +
                    "  | | / _ \\ |  \\| | |  _| | | | | | | | | | \\ \\ /\\ / /|  \\| |\n" +
                    "  | |/ ___ \\| |\\  | |_| | |_| | | |_| | |_| |\\ V  V / | |\\  |\n" +
                    "  |_/_/   \\_\\_| \\_|\\____|\\___/  |____/ \\___/  \\_/\\_/  |_| \\_|" +
                    "");
        }

        /* Tornem l'estat de Ramonix al hacker */
        return destruida;
    }
}
