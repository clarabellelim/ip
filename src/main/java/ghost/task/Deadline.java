package ghost.task;

import ghost.exception.GhostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate by;

    public Deadline(String description, String by) throws GhostException {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new GhostException("AHHHHHH: Please use the following death date format: yyyy/MM/dd!");
        }
    }

    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "Deadline " + description + " /by " + by.format(INPUT_FORMAT); // Ensure LocalDate is formatted properly
    }
}
