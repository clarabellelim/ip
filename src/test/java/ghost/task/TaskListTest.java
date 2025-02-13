package ghost.task;

import ghost.storage.Storage;
import ghost.ui.Ui;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addTask_increasesSize() {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        tasks.addTask(new Todo("Test Task"));

        assertEquals(1, tasks.size());
    }
}
