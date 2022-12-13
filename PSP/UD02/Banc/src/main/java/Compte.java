import java.util.List;

/**
 * @author Victor Piles
 */
public class Compte {
	private static Long ID_Comptes = 0L;
	private final Long id;
	private List<Client> beneficiaris;
	private float saldo;
	private boolean deutor;

	public Compte(Long id) {
		this.id = id;
	}

	public Compte(List<Client> beneficiaris, float saldo, boolean deutor) {
		this.id = ID_Comptes++;
		this.beneficiaris = beneficiaris;
		this.saldo = saldo;
		this.deutor = deutor;
	}

	public static Long getID_Comptes() {
		return ID_Comptes;
	}

	public static void setID_Comptes(Long ID_Comptes) {
		Compte.ID_Comptes = ID_Comptes;
	}

	public Long getId() {
		return id;
	}

	public List<Client> getBeneficiaris() {
		return beneficiaris;
	}

	public void setBeneficiaris(List<Client> beneficiaris) {
		this.beneficiaris = beneficiaris;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isDeutor() {
		return deutor;
	}

	public void setDeutor(boolean deutor) {
		this.deutor = deutor;
	}

	/**
	 * Es comparen els {@link Compte comptes} per l'{@link Compte#id}.
	 *
	 * @param o Objecte a comparar.
	 *
	 * @return Si els dos {@link Compte comptes} tenen el mateix {@link Compte#id}.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Compte compte = (Compte) o;

		return id.equals(compte.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Compte{" +
				"id=" + id +
				", beneficiaris=" + beneficiaris +
				", saldo=" + saldo +
				", deutor=" + deutor +
				'}';
	}
}