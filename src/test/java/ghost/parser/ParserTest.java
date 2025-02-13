package ghost.parser;

import ghost.command.AddCommand;
import ghost.command.Command;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void parse_validTodoCommand_returnsAddCommand() throws GhostException {
        TaskList tasks = new TaskList(new ArrayList<>(), new Storage("data.txt"), new Ui());
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        Parser parser = new Parser(tasks, ui, storage);
        Command cmd = parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }
}
