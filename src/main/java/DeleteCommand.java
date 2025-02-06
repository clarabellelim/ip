public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws GhostException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GhostException(" AHHHHHH: Task number is out of haunting range.");
        }
        Task deleteTask = tasks.deleteTask(taskIndex);
        storage.saveTasks(tasks.getTasks()); 
        ui.showDeleteMessage(deleteTask, tasks.size());
    
        return false;
    }
    
}
