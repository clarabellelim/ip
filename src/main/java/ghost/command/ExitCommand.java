package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

/**
 * Represents a command that exits the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by displaying an exit message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @return {@code true} to indicate program termination.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        return true;
    }

    /**
     * Indicates that this command should exit the chatbot.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
