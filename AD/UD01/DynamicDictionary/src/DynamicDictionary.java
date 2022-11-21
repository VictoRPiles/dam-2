import java.io.*;
import java.util.Collections;
import java.util.HashSet;

public class DynamicDictionary {
    private HashSet<String> words = new HashSet<>();
    private final File source;
    private final File destinationFolder = new File("Dictionary");

    public DynamicDictionary(File resourceFile) {
        this.source = resourceFile;
    }

    public DynamicDictionary(String path) {
        this.source = new File(path);
    }

    public void readWordsFromSourceFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;
            /* read line by line until EOF */
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank())
                    /* split line in words and add to the HashSet */
                    Collections.addAll(words, line.split("\\s+"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearAllFilesNamedAsAbecedaryLetters() {
        /* this array contains the abecedary (https://www.wikilengua.org/index.php/Alfabeto) */
        char[] abecedary = {
                'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'ñ',
                'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x',
                'y', 'z'
        };
        /* delete all files in the destination folder named as an abecedary letter plus .txt */
        for (char letter : abecedary) {
            File file = new File(destinationFolder + "/" + letter + ".txt");
            if (file.exists()) file.delete();
        }
    }

    public void createDestinationFolder() {
        if (!destinationFolder.exists()) {
            boolean success = new File(destinationFolder.getPath()).mkdir();

            if (!success) throw new RuntimeException("Can't create folder " + destinationFolder);

            System.out.printf("Creating folder %s ...\n", destinationFolder);
        }
    }

    public void writeWords() {
        char firstLetter;
        for (String word : words) {
            firstLetter = word.toLowerCase().charAt(0);
            /* destination file will be in the destination folder */
            File destinationFile = new File(destinationFolder + "/" + (firstLetter + ".txt"));
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destinationFile, true))) {
                System.out.println("Writing word " + word + " to " + destinationFile + "...");
                bufferedWriter.write(word);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public File getSource() {
        return source;
    }

    public File getDestinationFolder() {
        return destinationFolder;
    }

    @Override
    public String toString() {
        return "DynamicDictionary{\n" +
                "\twords=" + words + "\n" +
                '}';
    }
}