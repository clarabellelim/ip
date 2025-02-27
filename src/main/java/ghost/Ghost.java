package ghost;

import ghost.command.Command;
import ghost.exception.GhostException;
import ghost.parser.Parser;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.task.Task;
import ghost.ui.Ui;

import java.util.ArrayList;

/**
 * The {@code Ghost} class represents the main chatbot program.
 * It initialises necessary components such as storage, task list, and UI,
 * and runs the main event loop to process user commands.
 */
public class Ghost {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Ghost} chatbot with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Ghost(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tasksList = storage.loadTasks();
            tasks = new TaskList(tasksList, storage, ui);
        } catch (GhostException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>(), storage, ui);
        }
    }

    /**
     * Runs the chatbot event loop, handling user input and executing commands
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        Parser parser = new Parser(tasks, ui, storage);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // Divider line
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (GhostException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * The main entry point for the chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Ghost("data/tasks.txt").run();
    }
}
