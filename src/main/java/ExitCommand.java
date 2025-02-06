public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
