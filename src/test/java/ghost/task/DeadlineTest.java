package ghost.task;

import ghost.exception.GhostException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void constructor_validDate_parsesCorrectly() throws GhostException {
        Deadline deadline = new Deadline("Submit Report", "2025/02/15");
        assertEquals("[D][ ] Submit Report", deadline.toString().split("\\(by: ")[0].trim());
    }
}
