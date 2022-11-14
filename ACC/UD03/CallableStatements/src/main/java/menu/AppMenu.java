package menu;

/**
 * Implementation of the {@link Menu} class for this application.
 *
 * @author Victor Piles
 */
public class AppMenu extends Menu
{
	public AppMenu()
	{
		this.addOption(1, "Set new department");
		this.addOption(2, "Set new teacher");
		this.addOption(3, "Set salary to 'x'");
		this.addOption(4, "Rise salary a %");
		this.addOption(5, "Rise salary a % to a department like 'x'");
		this.addOption(6, "Get newest teacher surname");
		this.addOption(7, "Count teachers of department 'x'");
		this.addOption(8, "Quit");
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