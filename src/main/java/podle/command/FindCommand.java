package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

/**
 * Represents a command to search for tasks. Upon execution, displays tasks that contain the specified keyword.
 */
public class FindCommand extends Command {
    private String searchedString;

    public FindCommand(String searchedString) {
        this.searchedString = searchedString;
    }

    /**
     * Executes the command by searching the task list and displaying any matching results.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.searchList(searchedString);
    }
}