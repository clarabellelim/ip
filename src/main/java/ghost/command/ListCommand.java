package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Represents a command that lists all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by listing all tasks in the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage (not used in this command).
     * @param responseLabel The label to display the response on the UI.
     * @return {@code false} since this command does not terminate the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage, Label responseLabel) {
        Platform.runLater(() -> tasks.listTasks(responseLabel));  // Make sure to update the UI on the JavaFX thread

        return false;
    }
}
