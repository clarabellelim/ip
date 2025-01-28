import java.util.ArrayList;
import java.util.Scanner;

public class Ghost {
    public static void main(String[] args) {
        String logo = "             ('-. .-.               .-')    .-') _    \n"
                + "            ( OO )  /              ( OO ). (  OO) )   \n"
                + "  ,----.    ,--. ,--. .-'),-----. (_)---\\_)/     '._  \n"
                + " '  .-./-') |  | |  |( OO'  .-.  '/    _ | |'--...__) \n"
                + " |  |_( OO )|   .|  |/   |  | |  |\\  :` `. '--.  .--' \n"
                + " |  | .--, \\|       |\\_) |  |\\|  | '..`''.)   |  |    \n"
                + "(|  | '. (_/|  .-.  |  \\ |  | |  |.-._)   \\   |  |    \n"
                + " |  '--'  | |  | |  |   `'  '-'  '\\       /   |  |    \n"
                + "  `------'  `--' `--'     `-----'  `-----'    `--'    \n";

        String byebye = ".-. .-')                 ('-.  ,---. \n"
        + "\\  ( OO )              _(  OO) |   | \n"
        + " ;-----.\\  ,--.   ,--.(,------.|   | \n"
        + " | .-.  |   \\  `.'  /  |  .---'|   | \n"
        + " | '-' /_).-')     /   |  |    |   | \n"
        + " | .-. `.(OO  \\   /   (|  '--. |  .' \n"
        + " | |  \\  ||   /  /\\_   |  .--' `--'  \n"
        + " | '--'  /`-./  /.__)  |  `---..--.  \n"
        + " `------'   `--'       `------''--'  \n";

        String line = "____________________________________________________________";

        // Array list of tasks
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("BOO! Hello I am\n" + logo + "\nHow may I haunt you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try{ 
                String input = scanner.nextLine();

                //exit statement
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println(byebye + "\nI will always be haunting you...");
                    System.out.println(line);
                    break;
                }

                // if user inputs "list", print list of tasks
                if (input.equalsIgnoreCase("list")) {
                    System.out.println(line);
                    System.out.println(" BOO! Here's your list of things to HAUNT:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("\n MUAHAHAHAHAHA");
                    System.out.println(line);
                    continue;
                }

                //delete task
                if (input.startsWith("delete ")) {
                    deleteTask(input, tasks);
                    continue;
                }


                //mark task as done
                if (input.startsWith("mark ")) {
                    mark(input, tasks);
                    continue;
                }

                //unmark
                if (input.startsWith("unmark ")) {
                    unmark(input, tasks);
                    continue;
                }

                //todo
                if (input.startsWith("todo ")) {
                    String description = input.substring(5);

                    if (description.isEmpty()) {
                        throw new GhostException(" AHHHHHH: There's nothing to haunt! Please include a scary description.");
                    }

                    tasks.add(new Todo(description));
                    System.out.println(line);
                    System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
                    System.out.println(line);
                    continue;
                }

                //deadline
                if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ", 2);

                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new GhostException(" AHHHHHH: Please use the following haunting format: 'deadline <scary description> /by <deadline>'.");
                    }

                    String description = parts[0];
                    String by = parts[1];
                    tasks.add(new Deadline(description, by));
                    System.out.println(line);
                    System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
                    System.out.println("  " + tasks.get(tasks.size() -1));
                    System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
                    System.out.println(line);
                    continue;
                }

                if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from ", 2);
                    
                    if (parts.length < 2) {
                        throw new GhostException(" AHHHHHH: Please use the following haunting format: 'event <scary description> /from <start> /to <end>'.");
                    }

                    String description = parts[0];
                    String[] times = parts[1].split(" /to ", 2);

                    if (times.length < 2 || description.isEmpty() || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
                        throw new GhostException(" AHHHHHH: Please use the following haunting format: 'event <scary description> /from <start> /to <end>'.");
                    }

                    String from = times[0];
                    String to = times[1];
                    tasks.add(new Event(description, from, to));
                    System.out.println(line);
                    System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
                    System.out.println(line);
                    continue;
                }

                    throw new GhostException(" AHHHHHH: The description is too scary, I can't understand it! Please try again!");
                } catch (GhostException e) {
                    System.out.println(line);
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }

            }

        scanner.close();

    }


    //delete
    private static void deleteTask(String input, ArrayList<Task> tasks) throws GhostException {
        String line = "____________________________________________________________";

        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
            }
            Task removedTask = tasks.remove(taskNumber);
            System.out.println(line);
            System.out.println(" BOO! I've removed this haunting item:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
    }


    //mark
    private static void mark(String input, ArrayList<Task> tasks) throws GhostException {
        String line = "____________________________________________________________";

        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
            }
            tasks.get(taskNumber).markAsDone();
            System.out.println(line);
            System.out.println(" BOO! I've marked this task as haunted:");
            System.out.println("   " + tasks.get(taskNumber));
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
    }

    //unmark
    private static void unmark(String input, ArrayList<Task> tasks) throws GhostException {
        String line = "____________________________________________________________";

        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
            }
            tasks.get(taskNumber).unmark();
            System.out.println(line);
            System.out.println(" BOO! I've unmarked this task for haunting:");
            System.out.println("   " + tasks.get(taskNumber));
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
    }
}
