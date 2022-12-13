/**
 * @author Victor Piles
 */
public class RobotB extends Robot {

	private final int mallas;

	public RobotB(Cinta cinta, int mallas, int tiempo) {
		super(cinta, tiempo);
		this.mallas = mallas;
	}

	@Override
	public void run() {
		synchronized (cinta) {
			//noinspection InfiniteLoopStatement
			while (true) {
				/* Saca 'x' mallas de 5 kilos */
				for (int malla = 0; malla < mallas; malla++) {
					System.out.print("(Malla " + (malla + 1) + ") ");
					/* Si hay 5 kilos o más en la cinta */
					if (cinta.getKilos() >= 5) {
						cinta.descargar(5);
					} else {
						/* Si quedan menos de 5 kilos */
						System.out.println("RobotB: No puedo sacar 5 kilos. Solo quedan " + cinta.getKilos() + ".");
						/* Esta malla queda vacía, se llenará en la próxima ronda */
						malla--;
						/* Avisa al Robot A */
						cinta.notify();
						try {
							/* Espera al Robot A */
							cinta.wait();
							System.out.println("RobotB: Puedo seguir sacando...");
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
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