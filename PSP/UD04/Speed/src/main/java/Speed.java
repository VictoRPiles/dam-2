public class Speed extends Thread {
	private final Autobus autobus;

	public Speed(String name, Autobus autobus) {
		super(name);
		this.autobus = autobus;
	}

	@Override
	public void run() {
		float random = (float) (Math.random() * 10);

		synchronized (autobus) {
			while (true) {
				/* ========== ACCELERAR ========== */
				if (getName().equals("accelerar")) {
					System.out.println("Anem a accelerar");
					do {
						autobus.accelerar(random);
						System.out.println("Velocitat: " + Math.floor(autobus.getVelocitat()) + " km/h");

						try {
							sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					} while (autobus.getVelocitat() < Autobus.VELOCITAT_MAXIMA);
				}
				/* ========== FRENAR ========== */
				else if (getName().equals("frenar")) {
					System.out.println("Anem a frenar");
					do {
						autobus.frenar(random);
						System.out.println("Velocitat: " + Math.floor(autobus.getVelocitat()) + "km/h");

						try {
							sleep(1000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					} while (autobus.getVelocitat() > Autobus.VELOCITAT_MINIMA);
				}
				/* ========== CANVI DE FIL ========== */
				autobus.notify();
				try {
					autobus.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}