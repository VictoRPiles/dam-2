/**
 * @author Victor Piles
 */
public class Car extends Thread {
	private int kilometers;

	public Car(String name) {
		super(name);
		System.out.println("(META) Cotxe " + name + " preparat.");
	}

	@Override
	public void run() {
		do {
			int random = (int) ((Math.random() * 100) + 1);
			kilometers += random;
			System.out.println("(CARRERA) " + this.getName() + " ha recorregut " + kilometers + "kms!");
			try {

				//noinspection BusyWait
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		} while (kilometers < 500);
		System.out.println("(FÍ) " + this.getName() + " ha acabat.");
	}
}