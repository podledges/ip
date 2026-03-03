package podle.command;

import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.Deadlines;
import podle.task.TaskList;
import podle.task.ToDo;
import podle.ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command{

    private final String[] deadlineString;
    private final Boolean shouldPrint;

    public DeadlineCommand(String[] deadlineString, Boolean shouldPrint) {
        this.deadlineString = deadlineString;
        this.shouldPrint = shouldPrint;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Deadlines deadlines = new Deadlines(deadlineString);
            tasks.addTask(deadlines, shouldPrint);
            storage.appendToFile(deadlines);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            ui.printError(e.getMessage());
        } catch (Exception e) {
            ui.printError("unexpectedly..." + e.getMessage());
        }
    }
}
