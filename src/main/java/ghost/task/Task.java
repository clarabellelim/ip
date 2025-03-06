package ghost.task;

import ghost.exception.GhostException;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Default to not done
    }

    /**
     * Converts this task to a string for file storage.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Converts user input into a {@code Task} object.
     *
     * @param input The user input string (e.g., "todo read book", "deadline return book /by 2025-03-10").
     * @return The corresponding {@code Task} object.
     * @throws GhostException If the format is invalid.
     */
    public static Task fromUserInput(String input) throws GhostException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new GhostException("AHHHHH: Task description cannot be empty!");
        }

        String commandWord = parts[0].toLowerCase();
        String details = parts[1].trim();

        switch (commandWord) {
            case "todo":
                return new Todo(details);

            case "deadline":
                String[] deadlineParts = details.split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new GhostException("AHHHHH: A deadline task needs a description and a /by date!");
                }
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());

            case "event":
                String[] eventParts = details.split(" /from | /to ", 3);
                if (eventParts.length < 3) {
                    throw new GhostException("AHHHHH: An event task needs a description, /from, and /to dates!");
                }
                return new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

            default:
                throw new GhostException("AHHHHH: Unknown type of haunted task!");
        }
    }

    /**
     * Converts a string representation of a task from storage into a {@code Task} object.
     *
     * @param taskString The stored task format (e.g., "[T][ ] Read book").
     * @return The corresponding {@code Task} object.
     * @throws GhostException If the format is invalid.
     */
    public static Task fromString(String taskString) throws GhostException {
        if (taskString.startsWith("[T]")) {
            return new Todo(taskString.substring(6)); // Skip "[T][ ] "
        } else if (taskString.startsWith("[D]")) {
            String[] parts = taskString.substring(6).split(" \\(by: ", 2);
            if (parts.length < 2) {
                throw new GhostException("Invalid Deadline format: " + taskString);
            }
            return new Deadline(parts[0], parts[1].replace(")", ""));
        } else if (taskString.startsWith("[E]")) {
            String[] parts = taskString.substring(6).split(" \\(from: ", 2);
            if (parts.length < 2) {
                throw new GhostException("Invalid Event format: " + taskString);
            }
            String[] timeParts = parts[1].split(" to: ", 2);
            if (timeParts.length < 2) {
                throw new GhostException("Invalid Event format: " + taskString);
            }
            return new Event(parts[0], timeParts[0], timeParts[1].replace(")", ""));
        } else {
            throw new GhostException("AHHHHHHHHH: Unknown type of haunting task: " + taskString);
        }
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return {@code "[X]"} if the task is done, {@code "[ ]"} otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // [X] for done, [ ] for not done
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
