import java.util.Scanner;

public class AgendaController {
    private final Agenda agenda;

    public AgendaController(Agenda agenda) {
        this.agenda = agenda;
    }

    public byte showMenu() {
        final byte N_OPTIONS = 4;
        byte option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show all contacts");
        System.out.println("2. Add new contact");
        System.out.println("3. Remove");
        System.out.println("4. Save and exit");
        System.out.print("> ");

        option = scanner.nextByte();
        /* if is not a valid option, recursively loop this method until valid */
        if (option <= 0 || option > N_OPTIONS) return showMenu();

        return option;
    }

    private Contact prepareNewContactForm() {
        String name, surname, phone;
        Scanner scanner = new Scanner(System.in);

        System.out.println("New contact form:");
        System.out.print("Contact Name: "); name = scanner.nextLine();
        System.out.print("Contact Surname: "); surname = scanner.nextLine();
        System.out.print("Contact Phone: "); phone = scanner.nextLine();

        return new Contact(name, surname, phone);
    }

    private Contact removeContactForm() {
        String phone;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Remove contact form:");
        System.out.print("Contact Phone: "); phone = scanner.nextLine();

        return new Contact("name", "surname", phone);
    }

    public boolean callAction(byte action) {
        switch (action) {
            case 1:
                agenda.showAgenda();
                return false;
            case 2:
                agenda.add(prepareNewContactForm());
                return false;
            case 3:
                agenda.remove(removeContactForm());
                return false;
            case 4:
                agenda.serialize();
                return true;
        }
        return false;
    }
}
