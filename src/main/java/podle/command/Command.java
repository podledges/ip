package podle.command;

import podle.task.TaskList;
import podle.ui.Ui;
import podle.storage.Storage;

public abstract class Command {
    public boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return isExit;
    }
}