public class Main {
    public static void main(String[] args) {
        System.out.println("===== AGENDA =====");

        Agenda agenda = new Agenda();
        AgendaController agendaController = new AgendaController(agenda);

        boolean exit;
        do {
            byte action = agendaController.showMenu();
            exit = agendaController.callAction(action);
        } while (!exit);
    }
}