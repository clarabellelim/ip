import Task;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

package ghost.command;
public class AddCommand extends Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        Task task = Task.fromString(input);
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
    
        ui.printLine();
        System.out.println("New haunting item added: " + task);
        ui.printLine();
    
        return false; 
    }
}
