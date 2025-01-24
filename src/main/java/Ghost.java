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

        System.out.println("BOO! Hello I am\n" + logo + "\nHow may I haunt you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(" Bye! I will always be haunting you...");
                System.out.println(line);
                break;
            } else { 
                System.out.println(line);
                System.out.println(" " + input);
                System.out.println(line);
            }
        }

        scanner.close();

    }
}
