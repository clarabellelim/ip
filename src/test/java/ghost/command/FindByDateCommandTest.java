package ghost.command;

import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FindByDateCommandTest {
    @Test
    public void execute_findsTasksOnDate() {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        assertDoesNotThrow(() -> {
            FindByDateCommand cmd = new FindByDateCommand("2025-02-15");
            cmd.execute(tasks, ui, storage);
        });
    }
}
