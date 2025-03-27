package ghost.ui;

import ghost.task.Task;
import javafx.scene.control.Label;
import java.util.ArrayList;


/**
 * Handles interactions with the user interface of the Ghost chatbot.
 * This includes updating the response messages, displaying task-related
 * information, and managing error messages.
 */
public class Ui {
    private String lastResponse;

    /**
     * Updates the response label with the given message.
     *
     * @param message The message to display on the GUI.
     */
    public void updateResponse(String message) {
        this.lastResponse = message;
        System.out.println(message);
    }

    /**
     * Displays the welcome message in the GUI.
     * This method sets the welcome message on the given label.
     *
     * @param responseLabel The label that displays the welcome message.
     */
    public void showWelcomeMessage(Label responseLabel) {
        String logo = """
                ('-. .-.               .-')    .-') _    
               ( OO )  /              ( OO ). (  OO) )   
        ,----.    ,--. ,--. .-'),-----. (_)---\\_)/     '._  
       '  .-./-') |  | |  |( OO'  .-.  '/    _ | |'--...__) 
       |  |_( OO )|   .|  |/   |  | |  |\\  : . '--.  .--' 
       |  | .--, \\|       |\\_) |  |\\|  | '..''.)   |  |    
      (|  | '. (_/|  .-.  |  \\ |  | |  |.-._)   \\   |  |    
       |  '--'  | |  | |  |   '  '-'  '\\       /   |  |    
        ------'  --' --'     -----'  -----'    --'    
        """;
        responseLabel.setText("BOO! Hello I am\n" + logo + "\nHow may I haunt you?");
    }

    /**
     * Displays the goodbye message in the GUI.
     * This message is shown when the user exits the program.
     *
     * @param responseLabel The label that displays the exit message.
     */
    public void showExitMessage(Label responseLabel) {
        String byebye = """
                .-. .-')                 ('-.  ,---. 
               \\  ( OO )              _(  OO) |   | 
                ;-----.\\  ,--.   ,--.(,------.|   | 
                 | .-.  |   \\  .'  /  |  .---'|   | 
                 | '-' /_).-')     /   |  |    |   | 
                 | .-. .(OO  \\   /   (|  '--. |  .' 
                 | |  \\  ||   /  /\\_   |  .--' --'  
                 | '--'  /-./  /.__)  |  ---..--.  
                 ------'   --'       ------''--'  
        """;
        System.out.println("AHHHHH! The ghost is vanishing...\n");
        System.out.println(byebye + "\nI will always be haunting you...");
        responseLabel.setText("BOO! The ghost is vanishing... Goodbye!");
    }

    /**
     * Displays the loading error message when failing to load tasks.
     *
     * @param responseLabel The label to display the error message.
     */
    public void showLoadingError(Label responseLabel) {
        String errorMessage = "BOO! Failed to load haunting tasks.";
        responseLabel.setText(errorMessage);
        System.out.println(errorMessage);
    }

    /**
     * Gets the last response message stored.
     *
     * @return The last response message.
     */
    public String getLastResponse() {
        return lastResponse;
    }

    /**
     * Displays an error message in the GUI.
     *
     * @param message The error message to display.
     * @param responseLabel The label to display the error message.
     */
    public void showError(String message, Label responseLabel) {
        responseLabel.setText("Error: " + message);
        System.out.println("Error: " + message);
    }

    /**
     * Displays the delete message in the GUI.
     *
     * @param task The task that was deleted.
     * @param taskCount The number of remaining tasks.
     * @param responseLabel The label to display the delete message.
     */
    public void showDeleteMessage(Task task, int taskCount, Label responseLabel) {
        String message = "Deleted task: " + task.getDescription() + ". You now have " + taskCount + " tasks left.";
        responseLabel.setText(message);
        System.out.println(message);
    }

    /**
     * Displays the unmark message in the GUI.
     *
     * @param task The task that was unmarked.
     * @param responseLabel The label to display the unmark message.
     */
    public void showUnmarkMessage(Task task, Label responseLabel) {
        String message = "Task unmarked: " + task.getDescription();
        responseLabel.setText(message);
        System.out.println(message);
    }

    /**
     * Displays the add message in the GUI.
     *
     * @param task The task that was added.
     * @param taskCount The number of tasks after the new addition.
     * @param responseLabel The label to display the add message.
     */
    public void showAddMessage(Task task, int taskCount, Label responseLabel) {
        String message = "Added task: " + task.getDescription() + ". You now have " + taskCount + " tasks.";
        responseLabel.setText(message);
        System.out.println(message);
    }

    /**
     * Displays the mark message in the GUI.
     *
     * @param task The task that was marked.
     * @param responseLabel The label to display the mark message.
     */
    public void showMarkMessage(Task task, Label responseLabel) {
        String message = "Task marked as done: " + task.getDescription();
        responseLabel.setText(message);
        System.out.println(message);
    }

    /**
     * Displays the tasks that match the specified keyword.
     *
     * @param keyword The keyword used to search for tasks.
     * @param tasks   The list of matching tasks.
     * @param responseLabel The label to display the result on the UI.
     */
    public void showFindMessage(String keyword, ArrayList<Task> tasks, Label responseLabel) {
        StringBuilder response = new StringBuilder();
        response.append("BOO! Here are the haunted tasks matching the keyword '")
                .append(keyword).append("':\n");

        for (Task task : tasks) {
            response.append("  ").append(task).append("\n");
        }

        responseLabel.setText(response.toString());
    }

}