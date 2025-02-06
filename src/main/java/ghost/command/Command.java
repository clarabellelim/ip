import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

package ghost.command;
public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException;
    
    public boolean isExit() {
        return false; 
    }
}
