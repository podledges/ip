package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask();
    }

}
