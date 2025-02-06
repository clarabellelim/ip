package ghost.ui;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;


public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    
    private static final String LINE = "____________________________________________________________";

    public void printLine() {
        System.out.println(LINE);
    }

    public void showWelcomeMessage() {
        String logo = "             ('-. .-.               .-')    .-') _    \n"
                + "            ( OO )  /              ( OO ). (  OO) )   \n"
                + "  ,----.    ,--. ,--. .-'),-----. (_)---\\_)/     '._  \n"
                + " '  .-./-') |  | |  |( OO'  .-.  '/    _ | |'--...__) \n"
                + " |  |_( OO )|   .|  |/   |  | |  |\\  :` `. '--.  .--' \n"
                + " |  | .--, \\|       |\\_) |  |\\|  | '..`''.)   |  |    \n"
                + "(|  | '. (_/|  .-.  |  \\ |  | |  |.-._)   \\   |  |    \n"
                + " |  '--'  | |  | |  |   `'  '-'  '\\       /   |  |    \n"
                + "  `------'  `--' `--'     `-----'  `-----'    `--'    \n";
        System.out.println("BOO! Hello I am\n" + logo + "\nHow may I haunt you?");
        printLine();
    }

    public void showExitMessage() {
        String byebye = ".-. .-')                 ('-.  ,---. \n"
                + "\\  ( OO )              _(  OO) |   | \n"
                + " ;-----.\\  ,--.   ,--.(,------.|   | \n"
                + " | .-.  |   \\  `.'  /  |  .---'|   | \n"
                + " | '-' /_).-')     /   |  |    |   | \n"
                + " | .-. `.(OO  \\   /   (|  '--. |  .' \n"
                + " | |  \\  ||   /  /\\_   |  .--' `--'  \n"
                + " | '--'  /`-./  /.__)  |  `---..--.  \n"
                + " `------'   `--'       `------''--'  \n";
        printLine();
        System.out.println(" AHHHHH! The ghost is vanishing...\n");
        System.out.println(byebye + "\nI will always be haunting you...");
        printLine();
    }

    public void showDeleteMessage(Task removedTask, int taskSize) {
        printLine();
        System.out.println(" BOO! I've removed this haunting item:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + taskSize + " thing(s) to haunt on your haunting list.");
        printLine();
    }

    public void showMarkMessage(Task task) {
        printLine();
        System.out.println(" BOO! I've marked this task as haunted:");
        System.out.println("   " + task);
        printLine();
    }

    public void showUnmarkMessage(Task task) {
        printLine();
        System.out.println(" BOO! I've unmarked this task for haunting:");
        System.out.println("   " + task);
        printLine();
    }

    public void showAddMessage(Task task, int taskSize) {
        printLine();
        System.out.println(" New haunting item added. MUAHAHAHAHAHA:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + taskSize + " thing(s) to haunt on your haunting list.");
        printLine();
    }

    public void showTasksByDate(LocalDate date, ArrayList<Task> tasks) {
        printLine();
        System.out.println(" BOO! Here are the tasks to haunt on " + date + ":");
        
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) { // Deadline happens on the exact date
                    System.out.println("   " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().toLocalDate().isAfter(date) && !event.getTo().toLocalDate().isBefore(date)) { 
                    // Event occurs if 'date' is between from and to
                    System.out.println("   " + task);
                }
            }
        }
        
        printLine();
    }
        

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void showLoadingError() {
        System.out.println("BOO! Failed to load haunting tasks.");
    }

}
