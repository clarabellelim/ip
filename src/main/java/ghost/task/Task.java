package ghost.task;

import ghost.exception.GhostException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // Default to not done
    }

    public abstract String toFileString();

    public static Task fromString(String taskString) throws GhostException {
        System.out.println("Parsing task: " + taskString); // Debugging

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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // [X] for done, [ ] for not done
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}


    
