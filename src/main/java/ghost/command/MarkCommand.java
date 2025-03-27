package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Represents a command that marks a task as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking a task as completed and updating storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @param responseLabel The label to display the response on the UI.
     * @return {@code false} since this command does not terminate the program.
     * @throws GhostException If the task index is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage, Label responseLabel) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException("AHHHHHHH: Task number is out of haunting range.");
        }

        tasks.markTask(taskIndex, responseLabel);
        storage.saveTasks(tasks.getTasks());

        Platform.runLater(() -> {
            try {
                ui.showMarkMessage(tasks.get(taskIndex), responseLabel);
            } catch (GhostException e) {
                ui.showError(e.getMessage(), responseLabel);
            }
        });

        return false;
    }
}
