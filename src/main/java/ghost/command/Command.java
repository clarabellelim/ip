package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.exception.GhostException;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException;
    
    public boolean isExit() {
        return false; 
    }
}
