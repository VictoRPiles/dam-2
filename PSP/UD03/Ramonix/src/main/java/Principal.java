import java.util.ArrayList;

public class Principal implements Comu {

    public static void main(String[] args) {
        ArrayList<Hacker> llistaHackers = new ArrayList<>();

        /*
        Creem la sala de reunions que és compartida per tots els hackers.
        S'ha de fer ací i passar-la com a paràmetre perquè és compartida per tots els fils.
        */
        SalaReunions salaReunions = new SalaReunions();

        /* Crear els 4 hackers -fils- (Neo, P4Qu1T0, PaU3T i Ab4$T0$) */
        for (String hacker : HACKERS) {
            System.out.println("Cree: " + hacker);
            Hacker h = new Hacker(hacker, salaReunions);
            llistaHackers.add(h);
        }

        /* Llançar els hackers */
        for (Hacker hacker : llistaHackers) {
            hacker.start();
        }
    }
}
