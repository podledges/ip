package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.task.ToDo;
import podle.ui.Ui;

/**
 * Represents a command to add a standard to-do task. Upon execution, creates the to-do task and saves it to the list and storage.
 */
public class AddCommand extends Command {

    private final String taskString;
    private final boolean shouldPrint;

    public AddCommand(String taskString, Boolean shouldPrint) {
        this.taskString = taskString;
        this.shouldPrint = shouldPrint;
    }

    /**
     * Executes the command by creating the to-do task and appending it to the local storage.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            ToDo todo = new ToDo(taskString);
            tasks.addTask(todo, shouldPrint);
            storage.appendToFile(todo);
        } catch (InvalidInputException e) {
            ui.printError(e.getMessage());
        }
    }
}