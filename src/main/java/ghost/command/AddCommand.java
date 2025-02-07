package ghost.command;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;
import java.util.ArrayList;


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
