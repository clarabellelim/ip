public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();

        return false;
    }
}
