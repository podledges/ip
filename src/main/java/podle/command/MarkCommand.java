package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class MarkCommand extends Command {

    private int userInputIndex;
    private boolean shouldMark;

    public MarkCommand(int userInputIndex) {
        this.userInputIndex = userInputIndex;
        this.shouldMark = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            TaskList.markList(userInputIndex, shouldMark);
        } catch (InvalidInputException e) {
            ui.printError(e.getMessage());
        }
    }
}


