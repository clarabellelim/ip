package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;

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
     * @return {@code false} since this command does not terminate the program.
     * @throws GhostException If the task index is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        int adjustedIndex = taskIndex - 1;

        if (adjustedIndex < 0 || adjustedIndex >= tasks.size()) {
            throw new GhostException("AHHHHHHH: Task number is out of haunting range.");
        }

        Task deleteTask = tasks.deleteTask(adjustedIndex);
        storage.saveTasks(tasks.getTasks());
        ui.showDeleteMessage(deleteTask, tasks.size());

        return false;
    }
}
