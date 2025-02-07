package ghost;

import ghost.command.Command;
import ghost.exception.GhostException;
import ghost.parser.Parser;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.task.Task;
import ghost.ui.Ui;

import java.util.ArrayList;

public class Ghost {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ghost(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> tasksList = storage.loadTasks();
            tasks = new TaskList(tasksList, storage, ui);
        } catch (GhostException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>(), storage, ui);
        }
    }

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

    public static void main(String[] args) {
        new Ghost("data/tasks.txt").run();
    }
}
