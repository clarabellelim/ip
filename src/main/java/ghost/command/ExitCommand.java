package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import javafx.scene.control.Label;

/**
 * Represents a command that exits the chatbot.
 */
public class ExitCommand extends Command {
    private final Label responseLabel;

    /**
     * Constructs an {@code ExitCommand} with the given response label.
     *
     * @param responseLabel The label to display the exit message.
     */
    public ExitCommand(Label responseLabel) {
        this.responseLabel = responseLabel;
    }

    /**
     * Executes the command by displaying an exit message.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @param responseLabel The label to display the response on the UI.
     * @return {@code true} to indicate program termination.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage, Label responseLabel) {
        ui.showExitMessage(responseLabel);
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
