package ghost.command;

import ghost.task.TaskList;
import ghost.ui.Ui;
import ghost.storage.Storage;
import ghost.task.Todo;
import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AddCommandTest {
    @Test
    public void execute_addsTodo_correctly() throws GhostException {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        AddCommand cmd = new AddCommand("Todo Test Task");

        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Test Task", tasks.get(0).toString());
    }
}
