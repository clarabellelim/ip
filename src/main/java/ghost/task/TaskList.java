package ghost.task;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;
    private final Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }    

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

    public void findTasksByDate(LocalDate date) {
        ui.printLine();
        System.out.println("BOO! Here are the haunted tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        boolean found = false;

        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                System.out.println("  " + task);
                found = true;
            } else if (task instanceof Event && ((Event) task).getFrom().toLocalDate().equals(date)) {
                System.out.println("  " + task);
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No haunted tasks found on this date.");
        }

        ui.printLine();
    }

    public Task get(int index) throws GhostException {
        if (index < 0 || index >= tasks.size()) {
            throw new GhostException("Invalid task index.");
        }
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.printLine();
        System.out.println(" New haunting item added. MUAHAHAHAHAHA: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " thing(s) to haunt on your haunting list.");
        ui.printLine();
    }

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
    
        return removedTask; // Return the removed task
    }
    
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
    
        return task; // Return the marked task
    }
    
    public void unmarkTask(int taskIndex) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone(); // Fixed typo
        storage.saveTasks(tasks);
    
        ui.showUnmarkMessage(task);
    }
    
}
