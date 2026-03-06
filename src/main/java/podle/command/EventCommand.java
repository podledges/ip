package podle.command;

import podle.storage.Storage;
import podle.task.Event;
import podle.task.TaskList;
import podle.ui.Ui;
import java.io.IOException;

/**
 * Represents a command to add an event task. Upon execution, creates the event and saves it to the list and storage.
 */
public class EventCommand extends Command {
    private String[] eventString;
    private boolean shouldPrint;

    public EventCommand(String[] eventString, Boolean shouldPrint) {
        this.eventString = eventString;
        this.shouldPrint = shouldPrint;
    }

    /**
     * Executes the command by creating the event task and appending it to the local storage.
     *
     * @param tasks The active task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Event event = new Event(eventString);
            tasks.addTask(event, shouldPrint);
            storage.appendToFile(event);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            ui.printError(e.getMessage());
        } catch (Exception e) {
            ui.printError("unexpectedly..." + e.getMessage());
        }
    }
}