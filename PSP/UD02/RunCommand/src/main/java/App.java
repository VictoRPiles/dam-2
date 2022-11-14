import runner.CommandRunner;

import java.io.IOException;
import java.util.InputMismatchException;

public class App {
	public static void main(String[] args) {
		try {
			Process process = CommandRunner.run(args);
			CommandRunner.showProcessOutput(process);
		}
		catch (IOException | InputMismatchException e) {
			System.out.println("(ERROR) Error while running the command: " + e.getMessage());
		}
	}
}