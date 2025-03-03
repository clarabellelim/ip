package ghost.parser;

import ghost.command.AddCommand;
import ghost.command.Command;
import ghost.command.DeleteCommand;
import ghost.command.ExitCommand;
import ghost.command.FindByDateCommand;
import ghost.command.ListCommand;
import ghost.command.MarkCommand;
import ghost.command.UnmarkCommand;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

/**
 * Handles parsing of user input and execution of commands.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a Parser with the necessary components.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Processes user input and executes the corresponding command.
     *
     * @param input The user input string.
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean handleCommand(String input) {
        try {
            Command command = parse(input);
            return command.execute(tasks, ui, storage);
        } catch (GhostException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input The user input string.
     * @return The parsed command.
     * @throws GhostException If the command is invalid.
     */
    public Command parse(String input) throws GhostException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "delete":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify a haunted task number to delete!");
                }
                return createDeleteCommand(parts[1]);
            case "mark":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify a haunted task number to mark!");
                }
                return createMarkCommand(parts[1]);
            case "unmark":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify a haunted task number to unmark!");
                }
                return createUnmarkCommand(parts[1]);
            case "finddate":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify the haunting date in yyyy-MM-dd format.");
                }
                return new FindByDateCommand(parts[1].trim());
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(input);
            default:
                throw new GhostException("AHHHHHH: The description is too scary, I can't understand it!");
        }
    }

    private Command createDeleteCommand(String taskString) throws GhostException {
        try {
            int taskIndex = Integer.parseInt(taskString.trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
        }
    }

    private Command createMarkCommand(String taskString) throws GhostException {
        try {
            int taskIndex = Integer.parseInt(taskString.trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
        }
    }

    private Command createUnmarkCommand(String taskString) throws GhostException {
        try {
            int taskIndex = Integer.parseInt(taskString.trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new GhostException("AHHHHHH: Haunted task number must be a valid integer!");
        }
    }
}