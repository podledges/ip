package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InterruptedException {
        ui.printUnknownCommand();
    }
}