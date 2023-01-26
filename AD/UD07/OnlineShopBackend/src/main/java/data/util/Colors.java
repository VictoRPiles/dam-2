package data.util;

/**
 * Colors from <a href="https://www.w3schools.blog/ansi-colors-java">W3schools</a>
 *
 * @author victor
 */
public interface Colors {
    /* Reset */
    String RESET = "\033[0m";       /* Text Reset */

    /* Regular Colors */
    String BLACK = "\033[0;30m";    /* BLACK */
    String RED = "\033[0;31m";      /* RED */
    String GREEN = "\033[0;32m";    /* GREEN */
    String YELLOW = "\033[0;33m";   /* YELLOW */
    String BLUE = "\033[0;34m";     /* BLUE */
    String PURPLE = "\033[0;35m";   /* PURPLE */
    String CYAN = "\033[0;36m";     /* CYAN */
    String WHITE = "\033[0;37m";    /* WHITE */

    static void printQueryMessage(String message) {
        System.out.println(GREEN + "[+] " + message + RESET);
    }

    static void printInfoMessage(String message) {
        System.out.println(YELLOW + "[+] " + message + RESET);
    }

    static void printSelectMessage(String message) {
        System.out.println(CYAN + "[+] " + message + RESET);
    }
}
