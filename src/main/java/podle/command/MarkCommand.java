package podle.command;
import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;
import java.util.List;


public class MarkCommand extends Command {

    private List<Integer> userInputIndexes;
    private boolean shouldMark;
    private boolean shouldPrint;

    public MarkCommand(List<Integer> userInputIndexes, boolean shouldMark, boolean shouldPrint) {
        this.userInputIndexes = userInputIndexes;
        this.shouldMark = shouldMark;
        this.shouldPrint = shouldPrint;
    }

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


