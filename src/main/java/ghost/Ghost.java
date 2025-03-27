package ghost;

import ghost.command.Command;
import ghost.exception.GhostException;
import ghost.parser.Parser;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.task.Task;
import ghost.ui.Ui;

import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 * The {@code Ghost} class represents the core chatbot logic.
 * It initializes necessary components such as storage, task list, and UI,
 * and processes user commands.
 */
public class Ghost {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a {@code Ghost} chatbot with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     * @param responseLabel The label to display responses on the UI.
     */
    public Ghost(String filePath, Label responseLabel) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tasksList = storage.loadTasks();
            tasks = new TaskList(tasksList, storage, ui);
        } catch (GhostException e) {
            ui.showLoadingError(responseLabel);
            tasks = new TaskList(new ArrayList<>(), storage, ui);
        }
        parser = new Parser(tasks, ui, storage, responseLabel);
    }

    /**
     * Processes a user command and returns a response.
     *
     * @param input The user input command.
     * @param responseLabel The label to display responses on the UI.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input, Label responseLabel) {
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage, responseLabel) ? "Goodbye! 👋" : ui.getLastResponse();
        } catch (GhostException e) {
            return e.getMessage();
        }
    }

    /**
     * Getter for the {@code Ui} object.
     *
     * @return The {@code Ui} object.
     */
    public Ui getUi() {
        return ui;
    }
}
