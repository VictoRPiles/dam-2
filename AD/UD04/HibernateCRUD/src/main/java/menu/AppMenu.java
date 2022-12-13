package menu;

/**
 * Implementation of the {@link Menu} class for this application.
 *
 * @author Victor Piles
 */
public class AppMenu extends Menu {

	public AppMenu() {
		this.addOption(1, "Show a department by ID");
		this.addOption(2, "Show a teacher by ID");
		this.addOption(3, "Show the teachers in existing department");
		this.addOption(4, "Create new department");
		this.addOption(5, "Create new teacher with new department associated");
		this.addOption(6, "Create new teacher with existing department associated");
		this.addOption(7, "Delete a teacher");
		this.addOption(8, "Delete a department");
		this.addOption(9, "Set salary of whole department");
		this.addOption(10, "Rise salary for seniors of department");
		this.addOption(11, "Quit");
	}
}