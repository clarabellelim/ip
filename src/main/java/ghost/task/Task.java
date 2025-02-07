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
        String[] parts = taskString.split(" \\| ");
        
        switch (parts[0]) {
            case "T":
                return new Todo(parts[2]); // Todo task
            case "D":
                return new Deadline(parts[2], parts[3]); // Todo task
            case "E":
                return new Event(parts[2], parts[3], parts[4]); // Event task, expect description, from date/time, and to date/time
            default:
                throw new GhostException("AHHHHHHHHH: Unknown type of haunting task: " + parts[0]);
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

    
