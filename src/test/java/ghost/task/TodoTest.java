package ghost.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void toString_correctFormat() {
        Todo todo = new Todo("Read Book");
        assertEquals("[T][ ] Read Book", todo.toString());
    }
}
