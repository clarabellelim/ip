package ghost.task;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
     * Prints the list of tasks to the user.
     */
    public void listTasks() {
        ui.printLine();
        if (tasks.isEmpty()) {
            System.out.println(" BOO! There's nothing to haunt! Add a task first.");
        } else {
            System.out.println(" BOO! Here's your list of things to HAUNT:\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + tasks.get(i));
            }
        }
        ui.printLine();
    }

    /**
     * Finds and displays tasks that occur on a given date.
     *
     * @param date The date to filter tasks.
     */
    public void findTasksByDate(LocalDate date) {
        ui.printLine();
        System.out.println("BOO! Here are the haunted tasks on " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");

        boolean isFound = false;

        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                System.out.println("  " + task);
                isFound = true;
            } else if (task instanceof Event &&
                    ((Event) task).getFrom().toLocalDate().equals(date)) {
                System.out.println("  " + task);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println(" No haunted tasks found on this date.");
        }

        ui.printLine();
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
            throw new GhostException("Invalid task index.");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the list and saves it.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.printLine();
        System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
        ui.printLine();
    }

    /**
     * Deletes a task from the list and saves the updated list.
     *
     * @param taskIndex The index of the task to delete.
     * @return The deleted task.
     * @throws GhostException If the index is out of bounds.
     */
    public Task deleteTask(int taskIndex) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task removedTask = tasks.remove(taskIndex);
        storage.saveTasks(tasks);
    
        ui.printLine();
        System.out.println(" BOO! I've removed this haunting item:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
        ui.printLine();
    
        return removedTask;
    }

    /**
     * Marks a task as done and saves the updated list.
     *
     * @param taskIndex The index of the task to mark.
     * @return The marked task.
     * @throws GhostException If the index is out of bounds.
     */
    public Task markTask(int taskIndex) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.saveTasks(tasks);
    
        ui.printLine();
        System.out.println(" BOO! I've marked this task as haunted:");
        System.out.println("   " + task);
        ui.printLine();
    
        return task;
    }

    /**
     * Unmarks a task (sets it as not done) and saves the updated list.
     *
     * @param taskIndex The index of the task to unmark.
     * @throws GhostException If the index is out of bounds.
     */
    public void unmarkTask(int taskIndex) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.saveTasks(tasks);
    
        ui.showUnmarkMessage(task);
    }
    
}
