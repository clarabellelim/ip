package ghost;

import ghost.command.Command;
import ghost.exception.GhostException;
import ghost.parser.Parser;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;

import java.util.ArrayList;

/**
 * The main class for the Ghost application.
 * This class initializes the core components and runs the main event loop.
 */
public class Main {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;  // Parser instance

    /**
     * Initializes the Ghost application, setting up UI, storage, and task management.
     *
     * @param filePath The file path for storing task data.
     */
    public Main(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> loadedTasks = storage.loadTasks();
            tasks = new TaskList(loadedTasks, storage, ui);
        } catch (GhostException e) {
            ui.showError("Failed to load haunting tasks. Starting with an empty list...");
            tasks = new TaskList(new ArrayList<>(), storage, ui);
        }

        parser = new Parser(tasks, ui, storage);
    }

    /**
     * Runs the Ghost application.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = parser.parse(input);  // Use the instance of Parser
                isExit = command.execute(tasks, ui, storage);
            } catch (GhostException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Ghost("data/ghost.txt").run();
    }
}
