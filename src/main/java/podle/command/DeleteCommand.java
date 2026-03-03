package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class DeleteCommand extends Command {

    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int inputNumber = Integer.parseInt(arguments);
        try {
            tasks.deleteTask(inputNumber);
            Storage.deleteLine(inputNumber);
        } catch (InvalidInputException e) {
            ui.printError(e.getMessage());
        }
    }
}
