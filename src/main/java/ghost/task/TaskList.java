package ghost.task;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.scene.control.Label;

/**
 * Manages a list of tasks, providing methods to add, delete, retrieve, and display tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a TaskList with an initial list of tasks, storage, and UI components.
     *
     * @param tasks   The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui      The UI handler for displaying messages.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param responseLabel The Label to update with task information.
     */
    public void listTasks(Label responseLabel) {
        StringBuilder response = new StringBuilder();
        if (tasks.isEmpty()) {
            response.append(" BOO! There's nothing to haunt! Add a scary task first.");
        } else {
            response.append(" BOO! Here's your list of things to HAUNT:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        responseLabel.setText(response.toString());
    }

    /**
     * Finds and displays tasks that occur on a given date.
     *
     * @param date          The date to filter tasks.
     * @param responseLabel The Label to update with task information.
     */
    public void findTasksByDate(LocalDate date, Label responseLabel) {
        StringBuilder response = new StringBuilder();
        // Format the date as dd/MM/yyyy for output
        response.append("BOO! Here are the haunted tasks on ")
                .append(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(":");

        boolean isFound = false;

        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                response.append("\n  ").append(task);
                isFound = true;
            } else if (task instanceof Event &&
                    ((Event) task).getFrom().toLocalDate().equals(date)) {
                response.append("\n  ").append(task);
                isFound = true;
            }
        }

        if (!isFound) {
            response.append("\n No haunted tasks found on this date.");
        }

        responseLabel.setText(response.toString());
    }

    /**
     * Finds and returns tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Retrieves a task by index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws GhostException If the index is out of bounds.
     */
    public Task get(int index) throws GhostException {
        if (index < 0 || index >= tasks.size()) {
            throw new GhostException("Invalid haunted task index.");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the list and saves it.
     *
     * @param task The task to add.
     * @param responseLabel The Label to update with task information.
     */
    public void addTask(Task task, Label responseLabel) {
        tasks.add(task);
        storage.saveTasks(tasks);

        String response = " New haunting item added. MUAHAHAHAHAHA: \n" +
                "  " + task + "\n" +
                " Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.";

        responseLabel.setText(response);
    }

    /**
     * Deletes a task from the list and saves the updated list.
     *
     * @param taskIndex The index of the task to delete.
     * @param responseLabel The Label to update with task information.
     * @return The deleted task.
     * @throws GhostException If the index is out of bounds.
     */
    public Task deleteTask(int taskIndex, Label responseLabel) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task removedTask = tasks.remove(taskIndex);
        storage.saveTasks(tasks);

        String response = " BOO! I've removed this haunting item:\n" +
                "   " + removedTask + "\n" +
                " Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.";

        responseLabel.setText(response);

        return removedTask;
    }

    /**
     * Marks a task as done and saves the updated list.
     *
     * @param taskIndex The index of the task to mark.
     * @param responseLabel The Label to update with task information.
     * @return The marked task.
     * @throws GhostException If the index is out of bounds.
     */
    public Task markTask(int taskIndex, Label responseLabel) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.saveTasks(tasks);

        String response = " BOO! I've marked this task as haunted:\n" +
                "   " + task;

        responseLabel.setText(response);

        return task;
    }

    /**
     * Unmarks a task (sets it as not done) and saves the updated list.
     *
     * @param taskIndex The index of the task to unmark.
     * @param responseLabel The Label to update with task information.
     * @throws GhostException If the index is out of bounds.
     */
    public void unmarkTask(int taskIndex, Label responseLabel) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.saveTasks(tasks);

        String response = " BOO! I've unmarked this task for haunting:\n" +
                "   " + task;

        responseLabel.setText(response);
    }
}
