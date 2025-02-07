package ghost.task;

import ghost.exception.GhostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String description, String from, String to) throws GhostException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GhostException("AHHHHHH: Please use the following death date format: yyyy/MM/dd HH:mm!");
        }
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
