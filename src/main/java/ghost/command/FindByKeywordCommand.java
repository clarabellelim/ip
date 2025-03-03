package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.Task;
import ghost.task.TaskList;
import ghost.ui.Ui;

import java.util.ArrayList;

public class FindByKeywordCommand extends Command {
    private final String keyword;

    public FindByKeywordCommand(String keyword) throws GhostException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new GhostException(" AHHHHHH: The search keyword cannot be empty!");
        }
        this.keyword = keyword.toLowerCase();
    }

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
