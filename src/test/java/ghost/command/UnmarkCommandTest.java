package ghost.command;

import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.storage.Storage;
import ghost.task.Todo;
import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class UnmarkCommandTest {
    @Test
    public void execute_unmarksTaskAsNotDone() throws GhostException {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        Todo task = new Todo("Unmark Me");
        task.markAsDone();
        tasks.addTask(task);

        UnmarkCommand cmd = new UnmarkCommand(1);
        cmd.execute(tasks, ui, storage);

        assertFalse(tasks.get(0).isDone());
    }
}
