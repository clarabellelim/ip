package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.*;
import ghost.ui.Ui;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Represents a command that adds a new task to the task list.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs an {@code AddCommand} with the given input.
     *
     * @param input The user input representing the task to be added.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by adding a task to the task list and saving it to storage.
     *
     * @param tasks         The task list.
     * @param ui            The user interface.
     * @param storage       The storage for saving tasks.
     * @param responseLabel The label to display the response on the UI.
     * @return {@code false} since this command does not terminate the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage, Label responseLabel) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new GhostException("AHHHHHHH: Haunting description cannot be empty.");
            }

            Task task = createTask(input); // Determine and create the correct task type
            tasks.addTask(task, responseLabel);
            storage.saveTasks(tasks.getTasks());

            // Update the UI on the JavaFX thread
            Platform.runLater(() -> ui.showAddMessage(task, tasks.size(), responseLabel));

        } catch (GhostException e) {
            Platform.runLater(() -> ui.showError(e.getMessage(), responseLabel));
        }

        return false;
    }

    /**
     * Parses the user input and creates an appropriate Task object.
     *
     * @param input The user input.
     * @return A Task object.
     * @throws GhostException If the input format is invalid.
     */
    private Task createTask(String input) throws GhostException {
        if (input.startsWith("todo ")) {
            return new Todo(input.substring(5).trim());
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            if (parts.length < 2) {
                throw new GhostException("Invalid deadline format! Use: deadline <task> /by <date>");
            }
            return new Deadline(parts[0].trim(), parts[1].trim()); // Ensure Deadline constructor matches
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from ", 2);
            if (parts.length < 2 || !parts[1].contains(" /to ")) {
                throw new GhostException("Invalid event format! Use: event <task> /from <start> /to <end>");
            }
            String[] timeParts = parts[1].split(" /to ", 2);
            return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()); // Ensure Event constructor matches
        } else {
            throw new GhostException("Unrecognized task type! Start with 'todo', 'deadline', or 'event'.");
        }
    }
}
