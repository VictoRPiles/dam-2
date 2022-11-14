package menu;

/**
 * Implementation of the {@link Menu} class for this application.
 *
 * @author Victor Piles
 */
public class OptionsMenu extends Menu
{
	public OptionsMenu()
	{
		this.addOption(1, "Mostrar listado de alumnos");
		this.addOption(2, "Mostrar listado de municipios");
		this.addOption(3, "Introducir nuevo alumno (Prepared)");
		this.addOption(4, "Introducir nuevo municipio (Procedure)");
		this.addOption(5, "Salir");
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