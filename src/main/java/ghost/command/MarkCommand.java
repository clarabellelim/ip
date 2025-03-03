package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) throws GhostException {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        int adjustedIndex = taskIndex - 1;

        if (adjustedIndex < 0 || adjustedIndex >= tasks.size()) {
            throw new GhostException("AHHHHHHH: Task number is out of haunting range.");
        }

        tasks.markTask(adjustedIndex);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkMessage(tasks.get(adjustedIndex));

        return false;
    }
}
