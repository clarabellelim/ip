package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by deleting a task from the task list and updating storage.
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
            throw new GhostException("AHHHHHHH: Task number out of range!");
        }

        Task taskToDelete = tasks.get(taskIndex);
        tasks.deleteTask(taskIndex, responseLabel);  // Delete the task
        storage.saveTasks(tasks.getTasks());

        // Use Platform.runLater to update the GUI
        Platform.runLater(() -> {
            ui.showDeleteMessage(taskToDelete, tasks.size(), responseLabel);
        });

        return false;
    }
}
