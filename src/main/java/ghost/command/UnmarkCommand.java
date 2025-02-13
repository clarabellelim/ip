package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) throws GhostException {
        this.taskIndex = taskIndex;
    }

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
