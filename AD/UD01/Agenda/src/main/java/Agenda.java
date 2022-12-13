import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.HashSet;

public class Agenda implements Serializable {
    private static final String PATH = "agenda.xml";
    private HashSet<Contact> contacts;

    public Agenda() {
        this.contacts = new HashSet<>();
        deserialize();
    }

    public void showAgenda() {
        String successMessage = this.toString();
        String failureMessage = "INFO: The agenda is empty.";
        System.out.println(!contacts.isEmpty() ? successMessage : failureMessage);
    }

    public void add(Contact contact) {
        String successMessage = String.format("INFO: Adding %s...", contact);
        String failureMessage = String.format("INFO: %s already exists.", contact);
        System.out.println(contacts.add(contact) ? successMessage : failureMessage);
    }

    public void remove(Contact contact) {
        String successMessage = String.format("INFO: Removing contact %s...", contact.getPhone());
        String failureMessage = String.format("INFO: Contact %s doesn't exists.", contact.getPhone());
        System.out.println(contacts.remove(contact) ? successMessage : failureMessage);
    }

    public void deserialize() {
        if (!new File(PATH).exists()) return;

        XStream xstream = new XStream();
        xstream.allowTypes(new Class[]{Contact.class, Agenda.class});

        Agenda deserializedAgenda = (Agenda) xstream.fromXML(new File(PATH));
        this.contacts = deserializedAgenda.contacts;

        System.out.printf("INFO: Loading data from %s...\n", PATH);
    }

    public void serialize() {
        XStream xstream = new XStream();
        xstream.allowTypes(new Class[]{Contact.class, Agenda.class});

        try {
            System.out.printf("INFO: Saving data in %s...\n", PATH);
            xstream.toXML(this, new FileOutputStream(PATH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        for (Contact contact : contacts) {
            info.append("\t").append(contact).append(System.lineSeparator());
        }

        return "Agenda{ \n" + info + "}";
    }
}
