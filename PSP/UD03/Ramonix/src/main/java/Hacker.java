/**
 * @author Victor Piles
 */
public class Hacker extends Thread implements Comu {
    private final String nom;
    private final SalaReunions salaReunions;

    private final float cadencia;

    public Hacker(String nom, SalaReunions salaReunions, float cadencia) {
        this.nom = nom;
        this.salaReunions = salaReunions;
        this.cadencia = cadencia;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        /* El hacker entra a la sala de reunió i s'espera */
        try {
            entrarSalaReunions();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean destruit;
        /* Fins que Ramonix no ens comunique que ha mort ... atacar */
        do {
            Atac atac = new Atac(this);
            /* El hacker ataca a Ramonix */
            destruit = atac.atacar();
            /* El hacker s'espera abans d'atacar de nou, ha de descansar */
            try {
                sleep((long) (this.cadencia * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (!destruit);
    }

    /**
     * Els hackers s'esperen en la sala de reunió fins que aplegue Neo.
     */
    public void entrarSalaReunions() throws InterruptedException {
        synchronized (salaReunions) {
            /* El hacker entra a la sala */
            salaReunions.afegirHacker(this);

            /* Si és Neo, saluda */
            if (this.nom.equalsIgnoreCase("neo")) {
                System.out.println("***** Neo: Bon dia. Anem a destruir RAMONIX!!! *****");
            }

            /* Si Neo està a la sala de reunions */
            if (salaReunions.isNeoPresent()) {
                /* La resta saluden */
                if (!nom.equalsIgnoreCase("neo")) {
                    System.out.println(nom + ": Bon dia Neo...");
                }
                /* Si ja estan tots a la sala */
                if (salaReunions.getHackers().size() == 4) {
                    salaReunions.notifyAll();
                    /* Comença l'atac */
                    System.out.println("JA ESTEM TOTS. " + nom + " COMENÇA L'ATAC!!!");
                } else {
                    salaReunions.wait();
                    /* Comença l'atac */
                    System.out.println("JA ESTEM TOTS. " + nom + " COMENÇA L'ATAC!!!");
                }
            } else {
                salaReunions.wait();
                /* La resta saluden */
                if (!nom.equalsIgnoreCase("neo")) {
                    System.out.println(nom + ": Bon dia Neo...");
                }
                /* Comença l'atac */
                System.out.println("JA ESTEM TOTS. " + nom + " COMENÇA L'ATAC!!!");
            }
        }
    }
}
