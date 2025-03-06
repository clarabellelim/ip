package ghost.ui;

import ghost.task.Deadline;
import ghost.task.Event;
import ghost.task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions and displays messages.
 */
public class Ui {
    private final Scanner scanner;
    private static final String LINE = "____________________________________________________________";

    /**
     * Constructs an Ui object that reads user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line separator.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message when the program starts.
     */
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

    /**
     * Displays the exit message when the program ends.
     */
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

    /**
     * Displays a message when a task is deleted.
     *
     * @param removedTask The task that was removed.
     * @param taskSize The number of remaining tasks.
     */
    public void showDeleteMessage(Task removedTask, int taskSize) {
        printLine();
        System.out.println(" BOO! I've removed this haunting item:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + taskSize + " thing(s) to haunt on your haunting list.");
        printLine();
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that was marked.
     */
    public void showMarkMessage(Task task) {
        printLine();
        System.out.println(" BOO! I've marked this task as haunted:");
        System.out.println("   " + task);
        printLine();
    }

    /**
     * Displays a message when a task is unmarked as completed.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkMessage(Task task) {
        printLine();
        System.out.println(" BOO! I've unmarked this task for haunting:");
        System.out.println("   " + task);
        printLine();
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task The task that was added.
     * @param taskSize The updated number of tasks.
     */
    public void showAddMessage(Task task, int taskSize) {
        printLine();
        System.out.println(" New haunting item added. MUAHAHAHAHAHA:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + taskSize + " thing(s) to haunt on your haunting list.");
        printLine();
    }

    /**
     * Displays tasks that are scheduled for a given date.
     *
     * @param date The date to filter tasks by.
     * @param tasks The list of tasks to check.
     */
    public void showTasksByDate(LocalDate date, ArrayList<Task> tasks) {
        printLine();
        System.out.println(" BOO! Here are the tasks to haunt on " + date + ":");
        
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) {
                    System.out.println("   " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().toLocalDate().isAfter(date)
                        && !event.getTo().toLocalDate().isBefore(date)) {
                    System.out.println("   " + task);
                }
            }
        }
        
        printLine();
    }

    /**
     * Displays the tasks that match the given search keyword.
     *
     * @param keyword The keyword used for searching tasks.
     * @param matchingTasks The list of tasks that match the search keyword.
     */
    public void showFindMessage(String keyword, ArrayList<Task> matchingTasks) {
        printLine();
        System.out.println(" BOO! Here are the matching tasks in your haunting list for keyword: \"" + keyword + "\"");
        int index = 1;
        for (Task task : matchingTasks) {
            System.out.println(index++ + ". " + task);
        }
        printLine();
    }

    /**
     * Reads and returns the next user command.
     *
     * @return The user's command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Displays a message when loading tasks from storage fails.
     */
    public void showLoadingError() {
        System.out.println("BOO! Failed to load haunting tasks.");
    }

}
