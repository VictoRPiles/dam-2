import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Piles
 */
public class SalaReunions {
    private final List<Hacker> hackers;
    private boolean neoPresent;

    public SalaReunions() {
        hackers = new ArrayList<>();
        neoPresent = false;
    }

    /* S'afegeix el hacker i es comprova si és Neo */
    public void afegirHacker(Hacker hacker) {
        hackers.add(hacker);

        if (hacker.getNom().equalsIgnoreCase("neo")) {
            neoPresent = true;
        }
    }

    public boolean isNeoPresent() {
        return neoPresent;
    }

    public List<Hacker> getHackers() {
        return hackers;
    }
}
