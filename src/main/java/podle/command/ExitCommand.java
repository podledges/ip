package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InterruptedException {
        ui.printBye();
        this.isExit = true;
    }
}
