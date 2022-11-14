/**
 * @author Victor Piles
 */
public class Client {
	private String nom;
	private int edat;
	private boolean espanyol;

	public Client(String nom, int edat, boolean espanyol) {
		this.nom = nom;
		this.edat = edat;
		this.espanyol = espanyol;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEdat() {
		return edat;
	}

	public void setEdat(int edat) {
		this.edat = edat;
	}

	public boolean isEspanyol() {
		return espanyol;
	}

	public void setEspanyol(boolean espanyol) {
		this.espanyol = espanyol;
	}

	@Override
	public String toString() {
		return "Client{" +
				"nom='" + nom + '\'' +
				", edat=" + edat +
				", espanyol=" + espanyol +
				'}';
	}
}