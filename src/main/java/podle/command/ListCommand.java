package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command to display all tasks currently tracked by the application.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, prompting the task list to print all its contents.
     *
     * @param tasks The active task list to retrieve tasks from.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask();
    }

}
