package ghost.task;

import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void constructor_validDate_parsesCorrectly() throws GhostException {
        Event event = new Event("Meeting", "2025/02/15 10:00", "2025/02/15 12:00");
        assertEquals("[E][ ] Meeting", event.toString().split("\\(from: ")[0].trim());
    }
}

