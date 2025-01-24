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
        String[] tasks = new String[100];
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

            //add new tasks 
            if (taskCount < tasks.length) {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(line);
                System.out.println(" New haunting item added: " + input);
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println(" Error: The haunting list is full.");
                System.out.println(line);
            }
        }

        scanner.close();

    }
}
