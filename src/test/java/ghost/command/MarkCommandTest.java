package ghost.command;

import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.storage.Storage;
import ghost.task.Todo;
import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MarkCommandTest {
    @Test
    public void execute_marksTaskAsDone() throws GhostException {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        tasks.addTask(new Todo("Mark Me"));

        MarkCommand cmd = new MarkCommand(1);
        cmd.execute(tasks, ui, storage);

        assertTrue(tasks.get(0).isDone());
    }
}
