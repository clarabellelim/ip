package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.exception.GhostException;

/**
 * Represents an abstract command that can be executed in the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command, performing an action on the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @return {@code true} if the command exits the program, otherwise {@code false}.
     * @throws GhostException If an error occurs during execution.
     */
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException;

    /**
     * Returns whether this command should cause the chatbot to exit.
     *
     * @return {@code false} by default. Subclasses can override this.
     */
    public boolean isExit() {
        return false;
    }
}
