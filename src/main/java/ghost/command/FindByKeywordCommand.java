package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command that finds tasks by a given keyword in their description.
 */
public class FindByKeywordCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindByKeywordCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @throws GhostException If the keyword is null or empty.
     */
    public FindByKeywordCommand(String keyword) throws GhostException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new GhostException(" AHHHHHH: The search keyword cannot be empty!");
        }
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the find command, searching for tasks containing the keyword in their descriptions.
     * It displays the matching tasks if any are found, or an error message if no tasks match.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage system for handling task persistence (not used in this method).
     * @return false as the command does not end the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        var matchingTasks = findTasksByKeyword(keyword, tasks.getTasks());

        if (matchingTasks.isEmpty()) {
            ui.showError(" No tasks found matching the keyword: " + keyword);
        } else {
            ui.showFindMessage(keyword, matchingTasks);
        }

        return false;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @param tasks   The list of tasks to search through.
     * @return A list of tasks that contain the keyword in their description.
     */
    private ArrayList<Task> findTasksByKeyword(String keyword, ArrayList<Task> tasks) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
