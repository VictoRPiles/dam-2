import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the file -> ");
        final String PATH = scanner.nextLine();

        if (new File(PATH).exists()) {
            Dictionary dictionary = new Dictionary(new File(PATH));

            dictionary.createDestinationFolder();
            dictionary.clearAllFilesNamedAsAbecedaryLetters();
            try {
                dictionary.writeWords(dictionary.readWords());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File not found.");
        }
    }
}