package ghost.task;

import ghost.exception.GhostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructs an {@code Event} task with a description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start date/time in {@code yyyy/MM/dd HH:mm} format.
     * @param to The end date/time in {@code yyyy/MM/dd HH:mm} format.
     * @throws GhostException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws GhostException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GhostException("AHHHHHH: Please use the following death date format: yyyy/MM/dd HH:mm!");
        }
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start time as a {@code LocalDateTime}.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end time as a {@code LocalDateTime}.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the file representation of the event task.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        return "Event " + description + " /from " + from.format(INPUT_FORMAT) + " /to " + to.format(INPUT_FORMAT);
    }
}
