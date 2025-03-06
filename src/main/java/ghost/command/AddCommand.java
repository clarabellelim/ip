package ghost.command;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;
import java.util.ArrayList;

/**
 * Represents a command that adds a new task to the task list.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs an {@code AddCommand} with the given input.
     *
     * @param input The user input representing the task to be added.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by adding a task to the task list and saving it to storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @return {@code false} since this command does not terminate the program.
     * @throws GhostException If the task cannot be created or added.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        if (input == null || input.trim().isEmpty()) {
            throw new GhostException("AHHHHHHH: Haunting description cannot be empty.");
        }

        Task task = Task.fromUserInput(input);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showAddMessage(task, tasks.size());

        return false;
    }
}
