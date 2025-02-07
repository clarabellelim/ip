package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
