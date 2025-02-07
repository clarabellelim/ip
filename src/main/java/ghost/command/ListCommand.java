package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();

        return false;
    }
}
