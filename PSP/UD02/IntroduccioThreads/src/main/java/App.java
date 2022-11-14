/**
 * @author Victor Piles
 */
public class App {
	public static void main(String[] args) {
		Fil[] fils = {
				new Fil("Fil 1"),
				new Fil("Fil 2"),
				new Fil("Fil 3")
		};

		for (Fil fil : fils) {
			fil.start();
		}
	}
}