package podle.parser;

import podle.command.*;
import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.Deadlines;
import podle.task.Events;
import podle.task.TaskList;
import podle.ui.Ui;

import java.io.IOException;


public class Parser {

    public Command parse(String input) {
        String[] inputParts = input.trim().split(" ", 2);    // split after the first space char
        String command = inputParts[0].toUpperCase();
        String arguments = (inputParts.length > 1) ? inputParts[1] : "";   // checking if the second part is not empty,
        AllCommands cmd = determineCommand(command);
        boolean shouldPrint = true;

        switch (cmd) {   // move to PARSER class
        case BYE:
            //ui.printBye();
            //isExit = true;
            break;

        case BYEBYE:
            //ui.printByeBye();
            //isExit = true;
            break;

        case DELETE:
            try {
                //TaskList.deleteTask(arg);

            } catch (InvalidInputException e) {
                //ui.printError(e.getMessage());
            }
            break;

        case MARK, UNMARK:  //Argument must contain a number or multiple numbers,
            try {
                String tempString = arguments.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
                String[] numberString = tempString.trim().split("[,\\s]+"); // split into
                int sizeofString = numberString.length;
                for (int j = 0; j < sizeofString; j++) {
                    int taskIndex = Integer.parseInt(numberString[j]);
                    if (cmd.equals(AllCommands.UNMARK)) {
                        TaskList.markList(taskIndex, false);
                    } else {
                        TaskList.markList(taskIndex, true);
                    }
                }
            } catch (InvalidInputException e) {
            //    ui.printError(e.getMessage());
            }
            break;

        case LIST:
            TaskList.listTask();
            break;

        case ADD, TODO: // argument must contain some text or anything
            return prepareAdd(arguments, shouldPrint);

        case DEADLINE:
            try {
                String[] deadlineString = arguments.trim().split("/", 2);
                if (deadlineString.length < 2) {
                   // ui.printError("I need a NAME AND TIME (use /by <DEADLINE>).");
                    break;
                }
                Deadlines deadlines = new Deadlines(deadlineString);
            //    TaskList.addTask(deadlines, shouldPrint);
                Storage.appendToFile(deadlines);
            } catch (ArrayIndexOutOfBoundsException | IOException e) {
            //    ui.printError(e.getMessage());
            } catch (Exception e) {
            //    ui.printError("unexpectedly..." + e.getMessage());
            }
            break;

        case EVENT:
            try {
                String[] eventString = arguments.trim().split("/", 3);
                if (eventString.length < 3) {
            //       ui.printError("I need a NAME and TWO TIMINGS (grrrr use /<START TIME> /<ENDTIME>");
                    break;
                }
                Events event = new Events(eventString);
            //    TaskList.addTask(event, shouldPrint);
                Storage.appendToFile(event);
            } catch (ArrayIndexOutOfBoundsException | IOException e) {
           //     ui.printError(e.getMessage());
            } catch (Exception e) {
           //     ui.printError("unexpectedly..." + e.getMessage());
            }
            break;

        case UNKNOWN:
        default:
          //  ui.printUnknownCommand();
            break;
        }
    }


    public  AllCommands determineCommand(String command){
        for (AllCommands c : AllCommands.values()) {
            if (command.equalsIgnoreCase(c.name())) {
                return c;
            }
        }
        return AllCommands.UNKNOWN;
    }

    private Command prepareAdd(String arguments, boolean shouldPrint){
            return new AddCommand(arguments, shouldPrint);
        }

    private Command prepareDeadline(String arguments, boolean shouldPrint){
        String[] deadlineString = arguments.trim().split("/", 2);
        if (deadlineString.length < 2) {
            return new IncorrectCommand("I need a NAME AND TIME (use /by <DEADLINE>).");
        }
        else {
            return new DeadlineCommand(deadlineString, shouldPrint);
        }
    }
    private Command prepareEvent(String arguments, boolean shouldPrint){
        String[] eventString = arguments.trim().split("/", 3);
        if (eventString.length < 3) {
            return new IncorrectCommand("I need a NAME and TWO TIMINGS (grrrr use /<START TIME> /<ENDTIME>");
        }

        else {
            return new DeadlineCommand(deadlineString, shouldPrint);
        }
    }


