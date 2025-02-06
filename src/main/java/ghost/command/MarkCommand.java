package ghost.command;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) throws GhostException { // Change String to int
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        tasks.markTask(taskIndex);
        storage.saveTasks(tasks.getTasks());
        return false;
    }
}

