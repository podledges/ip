package podle.command;

import podle.storage.Storage;
import podle.task.Events;
import podle.task.TaskList;
import podle.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    private String[] eventString;
    private boolean shouldPrint;

    public EventCommand(String[] eventString, Boolean shouldPrint) {
        this.eventString = eventString;
        this.shouldPrint = shouldPrint;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Events event = new Events(eventString);
            tasks.addTask(event, shouldPrint);
            storage.appendToFile(event);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            ui.printError(e.getMessage());
        } catch (Exception e) {
            ui.printError("unexpectedly..." + e.getMessage());
        }
    }

}
