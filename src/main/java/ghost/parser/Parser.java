package ghost.parser;

import ghost.command.*;
import ghost.exception.GhostException;
import ghost.storage.Storage;
import ghost.task.TaskList;
import ghost.ui.Ui;

import javafx.scene.control.Label;

/**
 * Handles parsing of user input and execution of commands.
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private final Label responseLabel;

    /**
     * Constructs a Parser with the necessary components.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @param responseLabel The label to display responses on the UI.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage, Label responseLabel) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.responseLabel = responseLabel;
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
            return command.execute(tasks, ui, storage, responseLabel);
        } catch (GhostException e) {
            ui.showError(e.getMessage(), responseLabel);
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
                return new ExitCommand(responseLabel);
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
            case "find":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify a keyword to search for.");
                }
                return new FindByKeywordCommand(parts[1].trim());
            case "finddate":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify the haunting date in yyyy-MM-dd format.");
                }
                return new FindByDateCommand(parts[1].trim());
            case "todo":
                if (parts.length < 2) {
                    throw new GhostException("AHHHHHH: Please specify a task description!");
                }
                return new AddCommand("todo " + parts[1].trim()); // Ensure correct format for AddCommand
            case "deadline":
                if (parts.length < 2 || !parts[1].contains("/by")) {
                    throw new GhostException("AHHHHHH: Please specify the deadline in the format /by yyyy/MM/dd!");
                }
                return new AddCommand("deadline " + parts[1].trim()); // Ensure correct format for AddCommand
            case "event":
                if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new GhostException("AHHHHHH: Please specify the event in the format /from yyyy/MM/dd HH:mm /to yyyy/MM/dd HH:mm!");
                }
                return new AddCommand("event " + parts[1].trim()); // Ensure correct format for AddCommand
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