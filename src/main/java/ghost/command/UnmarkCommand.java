package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, setting the task to not done.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @param responseLabel The label to display the response on the UI.
     * @return False, indicating the program should not exit.
     * @throws GhostException If the task index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage, Label responseLabel) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException("AHHHHHHH: Task number is out of haunting range.");
        }

        tasks.unmarkTask(taskIndex, responseLabel);
        storage.saveTasks(tasks.getTasks());

        Platform.runLater(() -> {
            try {
                ui.showUnmarkMessage(tasks.get(taskIndex), responseLabel);
            } catch (GhostException e) {
                ui.showError(e.getMessage(), responseLabel);
            }
        });

        return false;
    }
}
