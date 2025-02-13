package ghost.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    @Test
    public void showWelcomeMessage_notNull() {
        Ui ui = new Ui();
        assertDoesNotThrow(ui::showWelcomeMessage);
    }

    @Test
    public void showExitMessage_notNull() {
        Ui ui = new Ui();
        assertDoesNotThrow(ui::showExitMessage);
    }
}
