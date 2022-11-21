package menu;

/**
 * Implementation of the {@link Menu} class for this application.
 *
 * @author Victor Piles
 */
public class DatabaseMenu extends Menu
{
	public DatabaseMenu()
	{
		this.addOption(1, "SQLite");
		this.addOption(2, "Derby");
		this.addOption(3, "HSQLDB");
	}

	@Override
	public String toString()
	{
		StringBuilder message = new StringBuilder();

		for (int i = 1; i <= getOptions().size(); i++) {
			message.append(i).append(" - ").append(getOptions().get(i)).append("\n");
		}

		return message.toString();
	}
}