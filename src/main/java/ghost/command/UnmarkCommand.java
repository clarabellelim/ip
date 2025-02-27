package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;
    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to unmark.
     * @throws GhostException If the task index is invalid.
     */
    public UnmarkCommand(int taskIndex) throws GhostException {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, setting the task to not done.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return False, indicating the program should not exit.
     * @throws GhostException If the task index is out of bounds.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        // Adjusting the index to 0-based (assuming user provides 1-based index)
        int adjustedIndex = taskIndex - 1;

        if (adjustedIndex < 0 || adjustedIndex >= tasks.size()) {
            throw new GhostException("AHHHHHHH: Task number is out of haunting range.");
        }

        tasks.unmarkTask(adjustedIndex);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkMessage(tasks.get(adjustedIndex)); // Assuming there's a method to show a message when unmarking
        return false;
    }
}
