package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.task.ToDo;
import podle.ui.Ui;

public class AddCommand extends Command {

    private final String taskString;
    private final Boolean shouldPrint;

    public AddCommand(String taskString, Boolean shouldPrint) {
        this.taskString = taskString;
        this.shouldPrint = shouldPrint;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            ToDo todo = new ToDo(taskString);
            TaskList.addTask(todo, shouldPrint);
            Storage.appendToFile(todo);
        } catch (InvalidInputException e) {
            Ui.printError(e.getMessage());
        }
    }
}
