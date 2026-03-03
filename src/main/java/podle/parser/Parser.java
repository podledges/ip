package podle.parser;

import podle.command.*;
import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.Deadlines;
import podle.task.Events;
import podle.task.TaskList;
import podle.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parser {

    public static Command parse(String input) {
        String[] inputParts = input.trim().split("\\s+", 2);    // split after the first space char
        String command = inputParts[0].toUpperCase();
        String arguments = (inputParts.length > 1) ? inputParts[1] : "";   // checking if the second part is not empty,
        AllCommands cmd = determineCommand(command);
        boolean shouldPrint = true;

        switch (cmd) {   // move to PARSER class
        case BYE:
            return new ExitCommand();

        case BYEBYE:
            return new QuickExitCommand();

        case DELETE:
            return new DeleteCommand(arguments);

        case MARK, UNMARK:  //Argument must contain a number or multiple numbers,
            return prepareMarkIndexes(arguments,cmd);

        case LIST:
            return new ListCommand();

        case FIND:
            return prepareFind(arguments);

        case ADD, TODO: // argument must contain some text or anything
            return prepareAdd(arguments, shouldPrint);

        case DEADLINE:
            return prepareDeadline(arguments, shouldPrint);

        case EVENT:
            return prepareEvent(arguments, shouldPrint);

        case UNKNOWN:
        default:
            return new IncorrectCommand("that was not a valid command podles has learnt");
        }
    }


    public static AllCommands determineCommand(String command){
        for (AllCommands c : AllCommands.values()) {
            if (command.equalsIgnoreCase(c.name())) {
                return c;
            }
        }
        return AllCommands.UNKNOWN;
    }

    private static Command prepareAdd(String arguments, boolean shouldPrint){
            return new AddCommand(arguments, shouldPrint);
        }

    private static Command prepareDeadline(String arguments, boolean shouldPrint){
        String[] deadlineString = arguments.trim().split("/", 2);
        if (deadlineString.length < 2) {
            return new IncorrectCommand("I need a NAME AND TIME (use /by <DEADLINE>).");
        }
        else {
            return new DeadlineCommand(deadlineString, shouldPrint);
        }
    }
    private static Command prepareEvent(String arguments, boolean shouldPrint){
        String[] eventString = arguments.trim().split("/", 3);
        if (eventString.length < 3) {
            return new IncorrectCommand(String.format("I need a NAME and TWO TIMINGS madge! " +
                            "%n (Hint: Event <EVENT_NAME> /<START_TIME>  /<END_TIME>"));
        }
            return new EventCommand(eventString, shouldPrint);
        }

    private static Command prepareFind(String arguments){
        if (arguments == null || arguments.trim().isEmpty()) {
            return new IncorrectCommand(String.format("We cant find nothing as nothing is nothing %n") +
                            String.format("pls type something for the search term i.e find <search_term>"));
        }
            return new FindCommand(arguments);
    }
    private static Command prepareMarkIndexes(String arguments, AllCommands cmd){

        boolean shouldMark = false;
        boolean shouldPrint = true;

        if (cmd.equals(AllCommands.UNMARK)) {
            shouldMark = false;
        }
        else if (cmd.equals(AllCommands.MARK)) {
            shouldMark = true;
        }

        String numberString = arguments.replaceAll("[a-zA-Z].*", "");
        String[] numberStringArray = numberString.trim().split("[,\\s]+"); // split into
        int sizeofString = numberStringArray.length;
        List<Integer> numberArray = new ArrayList<>();

        for(int j = 0; j < sizeofString; j++) {
            numberArray.add(Integer.parseInt(numberStringArray[j]));
        }
        if (numberArray.isEmpty()) {
            return new IncorrectCommand("podles is empty, like your input, give podles a valid task index");
        }
        return new MarkCommand(numberArray, shouldMark, shouldPrint);
    }

    }



