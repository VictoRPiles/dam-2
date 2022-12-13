/**
 * @author Victor Piles
 */
public class RobotA extends Robot {

	private final int kilos;

	public RobotA(Cinta cinta, int kilos, int tiempo) {
		super(cinta, tiempo);
		this.kilos = kilos;
	}

	@Override
	public void run() {
		synchronized (cinta) {
			//noinspection InfiniteLoopStatement
			while (true) {
				/* Carga 'x' kilos */
				cinta.cargar(kilos);

				/* Avisa al Robot B */
				cinta.notify();
				try {
					/* Espera al Robot B */
					cinta.wait();
					System.out.println("RobotA: Puedo seguir cargando...");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				try {
					/* Se espera 'x' segundos */
					//noinspection BusyWait
					sleep(tiempo * 1000L);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}