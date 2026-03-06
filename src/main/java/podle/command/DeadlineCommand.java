package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.Deadline;
import podle.task.TaskList;
import podle.ui.Ui;
import java.io.IOException;

/**
 * Represents a command to add a deadline task. Upon execution, creates the deadline and saves it to the list and storage.
 */
public class DeadlineCommand extends Command {

    private final String[] deadlineString;
    private final boolean shouldPrint;

    public DeadlineCommand(String[] deadlineString, Boolean shouldPrint) {
        this.deadlineString = deadlineString;
        this.shouldPrint = shouldPrint;
    }

    /**
     * Executes the command by creating the deadline task and appending it to the local storage.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Deadline deadlines = new Deadline(deadlineString);
            tasks.addTask(deadlines, shouldPrint);
            storage.appendToFile(deadlines);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            ui.printError(e.getMessage());
        } catch (Exception e) {
            ui.printError("unexpectedly..." + e.getMessage());
        }
    }
}