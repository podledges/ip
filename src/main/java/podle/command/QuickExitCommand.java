package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class QuickExitCommand extends Command {

    public QuickExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeBye();
        this.isExit = true;
    }
}