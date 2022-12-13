package runner;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

/**
 * Class with utils for process and multithreading.
 *
 * @author Victor Piles
 */
public abstract class CommandRunner {
	/**
	 * The current {@link Runtime}.
	 */
	private static final Runtime runtime = Runtime.getRuntime();

	/**
	 * Runs the provided command.
	 *
	 * @param command The command.
	 *
	 * @return The {@link Process} returned from running the command.
	 *
	 * @throws IOException If an input/output error happens.
	 * @see Runtime#exec(String[])
	 */
	public static Process run(String @NotNull ... command) throws IOException {
		if (command.length == 0) throw new InputMismatchException("No command provided");

		return runtime.exec(command);
	}

	/**
	 * Prints the output of a {@link Process}.
	 *
	 * @param process The process.
	 *
	 * @throws IOException If an input/output error happens.
	 */
	public static void showProcessOutput(@NotNull Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}
}