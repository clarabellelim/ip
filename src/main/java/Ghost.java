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

        String line = "____________________________________________________________";

        // list of tasks
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("BOO! Hello I am\n" + logo + "\nHow may I haunt you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            // exit statement
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(" Bye! I will always be haunting you...");
                System.out.println(line);
                break;
            }

            // if user inputs "list", print list of tasks
            if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println(" BOO! Here's your list of things to HAUNT:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("  " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
                continue;
            }

            //mark task as done
            if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) -1 ;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].markAsDone();
                    System.out.println(line);
                    System.out.println(" BOO! I've marked this task as haunted:");
                    System.out.println("  " + tasks[taskNumber]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println(" AHHHHHH: Task number is out of haunting range.");
                    System.out.println(line);
                }
                continue;
            }

            //unmark
            if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) -1 ;
                if (taskNumber >= 0 && taskNumber < taskCount) {
                    tasks[taskNumber].unmark();
                    System.out.println(line);
                    System.out.println(" BOO! I've unmarked this task for haunting:");
                    System.out.println("  " + tasks[taskNumber]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println(" AHHHHHH: Task number is out of haunting range.");
                    System.out.println(line);
                }
                continue;
            }

            //add new tasks 
            if (taskCount < tasks.length) {
                tasks[taskCount] = new Task (input);
                taskCount++;
                System.out.println(line);
                System.out.println(" New haunting item added: " + input);
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println(" AHHHHHH: The haunting list is full.");
                System.out.println(line);
            }

        }

        scanner.close();

    }
}
