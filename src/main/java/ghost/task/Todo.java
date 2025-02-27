package ghost.task;

/**
 * Represents a to-do task that does not have a specific date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with a given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the to-do task formatted for file storage.
     *
     * @return A string suitable for saving in a file.
     */
    @Override
    public String toFileString() {
        return "Todo " + description;
    }
}
