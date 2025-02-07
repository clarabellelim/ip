package ghost.parser;

import ghost.command.*;
import ghost.exception.GhostException;

public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public boolean handleCommand(String input) {
        try {
            Command command = parse(input);
            boolean isExit = command.execute(tasks, ui, storage);
            return isExit;
        } catch (GhostException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }

    public Command parse(String input) throws GhostException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
    
        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "delete" -> {
                if (parts.length < 2) throw new GhostException("AHHHHHH: Please specify a haunted task number to delete!");
                try {
                    int taskIndex = Integer.parseInt(parts[1].trim()) - 1;
                    yield new DeleteCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
                }
            }
            case "mark" -> {
                if (parts.length < 2) throw new GhostException("AHHHHHH: Please specify a haunted task number to mark!");
                try {
                    int taskIndex = Integer.parseInt(parts[1].trim()) - 1;
                    yield new MarkCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
                }
            }
            case "unmark" -> {
                if (parts.length < 2) throw new GhostException("AHHHHHH: Please specify a haunted task number to unmark!");
                try {
                    int taskIndex = Integer.parseInt(parts[1].trim()) - 1;
                    yield new UnmarkCommand(taskIndex);
                } catch (NumberFormatException e) {
                    throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
                }
            }
            case "finddate" -> {
                if (parts.length < 2) throw new GhostException("AHHHHHH: Please specify the haunting date in yyyy-MM-dd format.");
                yield new FindByDateCommand(parts[1].trim());
            }
            case "todo", "deadline", "event" -> new AddCommand(input);
            default -> throw new GhostException(" AHHHHHH: The description is too scary, I can't understand it!");
        };
    }
    
}
