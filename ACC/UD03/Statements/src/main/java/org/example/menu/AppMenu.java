package org.example.menu;

/**
 * Implementation of the {@link Menu} class for this application.
 *
 * @author Victor Piles
 */
public class AppMenu extends Menu {
    public AppMenu() {
        this.addOption(1, "Show all teachers");
        this.addOption(2, "Show all departments");
        this.addOption(3, "Add new teacher");
        this.addOption(4, "Add new department");
        this.addOption(5, "Add salary column to teachers");
        this.addOption(6, "Evaluate custom query");
        this.addOption(7, "Quit");
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        for (int i = 1; i <= getOptions().size(); i++) {
            message.append(i).append(" - ").append(getOptions().get(i)).append("\n");
        }

        return message.toString();
    }
}