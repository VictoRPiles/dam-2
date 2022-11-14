public class Main {
    public static void main(String[] args) {
        DynamicDictionary dictionary = new DynamicDictionary("resources/imagine.txt");

        System.out.printf("Reading words from %s...\n", dictionary.getSource());
        dictionary.readWordsFromSourceFile();

        System.out.printf("Words read: \n%s\n", dictionary);

        System.out.printf("Clearing previous files in %s\n", dictionary.getDestinationFolder());
        dictionary.clearAllFilesNamedAsAbecedaryLetters();

        dictionary.createDestinationFolder();
        dictionary.writeWords();
    }
}