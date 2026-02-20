package podle.command;

import podle.exception.InvalidInputException;
import podle.task.*;
import podle.ui.Ui;

import java.io.IOException;

public class ExecuteCommand {

    private Command cmd;
    private String arg;

    public ExecuteCommand(Command cmd){
        this.cmd = cmd;
    }

    public ExecuteCommand(Command cmd, String s){
        this.cmd = cmd;
        this.arg = s;
    }

    public void Execute(TaskList tasks, Ui ui) throws InterruptedException, IOException {
        switch(cmd) {   // move to PARSER class
        case BYE:
            ui.printBye();
            System.exit(0);
            break;

        case BYEBYE:
            ui.printByeBye();
            System.exit(0);
            break;

        case DELETE:
            try {
                TaskList.deleteTask(arg);

            } catch(InvalidInputException e) {
                ui.printError(e.getMessage());
            }
            break;

        case MARK, UNMARK:  //Argument must contain a number or multiple numbers,
            try{
                String tempString = arg.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
                String[] numberString = tempString.trim().split("[,\\s]+"); // split into
                int sizeofString = numberString.length;
                for(int j = 0; j < sizeofString; j++) {
                    int taskIndex = Integer.parseInt(numberString[j]);
                    if (cmd.equals(Command.UNMARK)) {
                        TaskList.markList(taskIndex, false);
                    }
                    else {
                    TaskList.markList(taskIndex, true);
                    }
                 }
            } catch(InvalidInputException e) {
                ui.printError(e.getMessage());
            }
            break;

        case LIST:
            TaskList.listTask();
            break;

        case ADD, TODO: // argument must contain some text or anything
            try {
                ToDo todo = new ToDo(arg);
                TaskList.addTask(todo);
                Storage.appendToFile(todo);
            } catch(InvalidInputException e){
                ui.printError(e.getMessage());
            }
            break;

        case DEADLINE:
            try {
                String[] deadlineString = arg.trim().split("/", 2);
                if (deadlineString.length < 2) {
                    ui.printError("I need a NAME AND TIME (use /by <DEADLINE>).");
                    break;
                }
                Deadlines deadlines = new Deadlines(deadlineString);
                TaskList.addTask(deadlines);
                Storage.appendToFile(deadlines);
            } catch (ArrayIndexOutOfBoundsException | IOException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("unexpectedly..." + e.getMessage());
            }
            break;

        case EVENT:
            try {
                String[] eventString = arg.trim().split("/", 3);
                if (eventString.length < 3) {
                    ui.printError("I need a NAME and TWO TIMINGS (grrrr use /<START TIME> /<ENDTIME>");
                    break;
                }
                Events event = new Events(eventString);
                TaskList.addTask(event);
                Storage.appendToFile(event);
            } catch (ArrayIndexOutOfBoundsException | IOException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("unexpectedly..." + e.getMessage());
            }
            break;

        case UNKNOWN:
        default:
            ui.printUnknownCommand();
            break;
        }
    }
}
