@SuppressWarnings({"SpellCheckingInspection", "unused"})
public interface Comu {

    //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    // Ús: System.out.println(ANSI_RED + ANSI_CYAN_BACKGROUND + ANSI_STRIKEOUT_WHITE + "Cadena de colorins" + ANSI_RESET);
    //FONDOS
    String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    String ANSI_RED_BACKGROUND = "\u001B[41m";
    String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //STRIKEOUT
    String ANSI_STRIKEOUT_BLACK = "\u001B[30;9m";
    String ANSI_STRIKEOUT_RED = "\u001B[31;9m";
    String ANSI_STRIKEOUT_GREEN = "\u001B[32;9m";
    String ANSI_STRIKEOUT_YELLOW = "\u001B[33;9m";
    String ANSI_STRIKEOUT_BLUE = "\u001B[34;9m";
    String ANSI_STRIKEOUT_PURPLE = "\u001B[35;9m";
    String ANSI_STRIKEOUT_CYAN = "\u001B[36;9m";
    String ANSI_STRIKEOUT_WHITE = "\u001B[37;9m";

    //LLETRES
    String ANSI_RESET = "\u001B[0m";
    String ANSI_BLACK = "\u001B[30m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI_BLUE = "\u001B[34m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_WHITE = "\u001B[37m";

    // DADES DE LES CONNEXIONS
    String HOST = "localhost";
    int PORT = 5000;

    // LLISTAT DE HACKERS
    String[] HACKERS = {"P4q1T0", "Neo", "PaU3T", "Ab4$t0$"};
    int[] hackerCadencia = {1, 2, 1, 1};
}
