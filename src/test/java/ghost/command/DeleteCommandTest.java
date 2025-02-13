package ghost.command;

import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.storage.Storage;
import ghost.task.Todo;
import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class DeleteCommandTest {
    @Test
    public void execute_deletesTask_correctly() throws GhostException {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        tasks.addTask(new Todo("Delete Me"));

        DeleteCommand cmd = new DeleteCommand(1);
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
    }
}
