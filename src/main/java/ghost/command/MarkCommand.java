package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

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
    public MarkCommand(int taskIndex) throws GhostException {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking a task as completed and updating storage.
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

        tasks.markTask(adjustedIndex);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkMessage(tasks.get(adjustedIndex)); // Fix: Use get() instead of getTask()

        return false;
    }
}
