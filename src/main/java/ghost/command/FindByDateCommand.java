package ghost.command;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

public class FindByDateCommand extends Command {
    private final LocalDate date;

    public FindByDateCommand(String dateStr) throws GhostException {
        try {
            this.date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new GhostException(" AHHHHHH: Invalid date format! Use yyyy-MM-dd.");
        }
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksByDate(date, tasks.getTasks()); // Calls the new UI method
        return false;
    }
}
