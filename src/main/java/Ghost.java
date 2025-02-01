import java.util.ArrayList;
import java.util.Scanner;

enum TaskType {
    TODO, DEADLINE, EVENT
}

public class Ghost {
    private static final String FILE_PATH = "./data/ghost.txt";

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
 
        ArrayList<Task> tasks = Storage.loadTasks(FILE_PATH);

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

                // todo / deadling / event
                if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    addTask(input, tasks);
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

  
    private static void addTask(String input, ArrayList<Task> tasks) throws GhostException {
        String line = "____________________________________________________________";
        String description;

        if (input.startsWith("todo ")) {
            description = input.substring(5).trim();
            
            if (description.isEmpty()) {
            throw new GhostException(" AHHHHHH: There's nothing to haunt! Please include a scary description.");
        }
        tasks.add(new Todo(description));

    } else if (input.startsWith("deadline ")) {
        String[] parts = input.substring(9).split(" /by ", 2);

        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new GhostException(" AHHHHHH: Please use the following haunting format: 'deadline <scary description> /by <deadline>'.");
        }
        tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));

    } else if (input.startsWith("event ")) {
        String[] parts = input.substring(6).split(" /from ", 2);
        
        if (parts.length < 2) {
            throw new GhostException(" AHHHHHH: Please use the following haunting format: 'event <scary description> /from <start> /to <end>'.");
        }

        String[] times = parts[1].split(" /to ", 2);

        if (times.length < 2 || parts[0].trim().isEmpty() || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new GhostException(" AHHHHHH: Please use the following haunting format: 'event <scary description> /from <start> /to <end>'.");
        }
        tasks.add(new Event(parts[0].trim(), times[0].trim(), times[1].trim()));
       
    }

    Storage.saveTasks(tasks, FILE_PATH);
    System.out.println(line);
    System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
    System.out.println("  " + tasks.get(tasks.size() - 1));
    System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
    System.out.println(line);

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
            Storage.saveTasks(tasks, FILE_PATH);
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
            Storage.saveTasks(tasks, FILE_PATH);
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
            Storage.saveTasks(tasks, FILE_PATH);
            System.out.println(line);
            System.out.println(" BOO! I've unmarked this task for haunting:");
            System.out.println("   " + tasks.get(taskNumber));
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
    }
}
