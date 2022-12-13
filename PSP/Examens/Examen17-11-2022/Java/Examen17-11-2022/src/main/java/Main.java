/**
 * @author Victor Piles
 */
public class Main {

	public static void main(String[] args) {
		Cinta cinta = new Cinta();

		RobotA robotA = new RobotA(cinta, 15, 5); //Pone 15 kilos cada 5 segundos
		RobotB robotB = new RobotB(cinta, 2, 3);  //Saca 2 mallas cada 3 segundos
		//RobotA robotA = new RobotA(cinta, 20, 5);
		//RobotB robotB = new RobotB(cinta, 3, 3);
		//RobotA robotA = new RobotA(cinta, 18, 6);
		//RobotB robotB = new RobotB(cinta, 2, 3);

		robotA.start();
		robotB.start();
	}
}