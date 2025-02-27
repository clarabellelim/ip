package ghost.task;

import ghost.exception.GhostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specific due date.
 */
public class Deadline extends Task {
    private final LocalDate by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructs a {@code Deadline} task with a description and a due date.
     *
     * @param description The task description.
     * @param by The due date in {@code yyyy/MM/dd} format.
     * @throws GhostException If the date format is invalid.
     */
    public Deadline(String description, String by) throws GhostException {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GhostException("AHHHHHH: Please use the following death date format: yyyy/MM/dd!");
        }
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date as a {@code LocalDate}.
     */
    public LocalDate getDate() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the file representation of the deadline task.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        return "Deadline " + description + " /by " + by.format(INPUT_FORMAT); // Ensure LocalDate is formatted properly
    }
}
