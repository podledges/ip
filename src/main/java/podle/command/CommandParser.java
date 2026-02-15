package podle.command;

import podle.exception.InvalidInputException;
import podle.task.Deadlines;
import podle.task.Events;
import podle.task.TaskList;
import podle.task.ToDo;

import podle.ui.Ui.*;


public class CommandParser {

    public static ExecuteCommand Parse(String input) {
        String[] inputParts = input.trim().split(" ", 2);    // split after the first space char
        String command = inputParts[0].toUpperCase();
        String arguments = (inputParts.length > 1) ? inputParts[1] : "";   // checking if the second part is not empty,
        Command cmd = Command.UNKNOWN;
        for (Command c : Command.values()) {
            if (command.equalsIgnoreCase(c.name())) {
                cmd = c;
                break;
            } else cmd = Command.UNKNOWN;
        }
        return new ExecuteCommand(cmd, arguments);
    }
}
