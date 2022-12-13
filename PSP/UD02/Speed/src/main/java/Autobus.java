public class Autobus {
	public static final int VELOCITAT_MINIMA = 50;
	public static final int VELOCITAT_MAXIMA = 80;

	private String matricula;
	private float velocitat = 50f;

	public Autobus(String matricula) {
		this.matricula = matricula;
	}

	public void accelerar(float quantitat) {
		velocitat += quantitat;
	}

	public void frenar(float quantitat) {
		velocitat -= quantitat;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public float getVelocitat() {
		return velocitat;
	}

	public void setVelocitat(float velocitat) {
		this.velocitat = velocitat;
	}

	@Override
	public String toString() {
		return "Autobus{" +
				"matricula='" + matricula + '\'' +
				'}';
	}
}