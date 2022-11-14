import java.io.*;

public class Dictionary {
    private final File source;
    private File destinationFolder = new File("Dictionary");

    public Dictionary(File source) {
        this.source = source;
    }

    /** Allows to override the default value for {@link #destinationFolder}. */
    public Dictionary(File source, File destinationFolder) {
        this.source = source;
        this.destinationFolder = destinationFolder;
    }

    public void createDestinationFolder() {
        if (!destinationFolder.exists()) {
            boolean success = new File(destinationFolder.getPath()).mkdir();

            if (!success) throw new RuntimeException("Can't create folder " + destinationFolder);

            System.out.println("Creating folder " + destinationFolder + "...");
        }
    }

    public String[] readWords() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            StringBuilder everything = new StringBuilder();
            String line;
            /* read line by line until EOF */
            while ((line = bufferedReader.readLine()) != null) {
                everything.append(line).append(System.lineSeparator());
            }
            /* returns content of file split by spaces */
            return everything.toString().split("\\s+");
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

    public void writeWords(String[] words) throws IOException {
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
}
