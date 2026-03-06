package podle.command;
import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;
import java.util.List;

/**
 * Represents a command to modify the completion status of one or more tasks.
 * Can be used to either mark tasks as done or unmark them.
 */
public class MarkCommand extends Command {

    private List<Integer> userInputIndexes;
    private boolean shouldMark;
    private boolean shouldPrint;

    /**
     * Constructs a MarkCommand with the specified task indices and desired status.
     *
     * @param userInputIndexes A list of 1-based indices corresponding to the tasks to update.
     * @param shouldMark True to mark tasks as done, false to unmark them.
     * @param shouldPrint True to print confirmation messages to the user.
     */
    public MarkCommand(List<Integer> userInputIndexes, boolean shouldMark, boolean shouldPrint) {
        this.userInputIndexes = userInputIndexes;
        this.shouldMark = shouldMark;
        this.shouldPrint = shouldPrint;
    }

    /**
     * Executes the command by updating the completion status of the target tasks and saving the changes.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs while writing to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {

        for (int index : userInputIndexes) {
            try {
                tasks.markList(index, shouldMark, shouldPrint);
                storage.markStorageList(index, shouldMark);
            } catch (InvalidInputException e) {
                ui.printError(e.getMessage());
            }
        }
    }
}


