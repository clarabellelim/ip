package ghost.storage;

import ghost.task.Task;
import ghost.task.Todo;
import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void saveAndLoadTasks_correctlyHandlesData() throws GhostException {
        Storage storage = new Storage("test_tasks.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task"));

        storage.saveTasks(tasks);
        ArrayList<Task> loadedTasks = storage.loadTasks();

        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] Test Task", loadedTasks.get(0).toString());
    }
}

