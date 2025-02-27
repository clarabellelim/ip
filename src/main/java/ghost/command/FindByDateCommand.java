package ghost.command;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that finds tasks by a specified date.
 */
public class FindByDateCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a {@code FindByDateCommand} with the specified date string.
     *
     * @param dateStr The date in {@code yyyy-MM-dd} format.
     * @throws GhostException If the date format is invalid.
     */
    public FindByDateCommand(String dateStr) throws GhostException {
        try {
            this.date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new GhostException(" AHHHHHH: Invalid date format! Use yyyy-MM-dd.");
        }
    }

    /**
     * Executes the command by displaying tasks that match the specified date.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage (not used in this command).
     * @return {@code false} since this command does not terminate the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksByDate(date, tasks.getTasks()); // Calls the new UI method
        return false;
    }
}
