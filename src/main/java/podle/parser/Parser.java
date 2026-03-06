package podle.parser;

import podle.command.*;
import podle.exception.InvalidInputException;
import podle.storage.Storage;
import podle.task.Deadline;
import podle.task.Event;
import podle.task.TaskList;
import podle.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interprets user input strings and translates them into executable Commands.
 * Acts as the middleman between raw text input and the application's logic.
 */
public class Parser {

    /**
     * Parses the raw input string from the user and returns the corresponding Command object.
     *
     * @param input The full text line entered by the user.
     * @return An executable Command based on the parsed input.
     */
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

    /**
     * Matches the user's command keyword against the defined enum of valid commands.
     *
     * @param command The first word of the user's input.
     * @return The corresponding AllCommands enum value, or UNKNOWN if no match is found.
     */
    public static AllCommands determineCommand(String command) {
        for (AllCommands c : AllCommands.values()) {
            if (command.equalsIgnoreCase(c.name())) {
                return c;
            }
        }
        return AllCommands.UNKNOWN;
    }

    /**
     * Prepares an AddCommand for standard ToDo tasks.
     *
     * @param arguments The description of the task to add.
     * @param shouldPrint True to print confirmation upon execution, false otherwise.
     * @return The constructed AddCommand.
     */
    private static Command prepareAdd(String arguments, boolean shouldPrint) {
        return new AddCommand(arguments, shouldPrint);
    }

    /**
     * Prepares a DeadlineCommand by splitting the arguments into a description and a time.
     *
     * @param arguments The string containing the task description and deadline, separated by "/".
     * @param shouldPrint True to print confirmation upon execution, false otherwise.
     * @return The constructed DeadlineCommand or an IncorrectCommand if formatting is invalid.
     */
    private static Command prepareDeadline(String arguments, boolean shouldPrint) {
        String[] deadlineString = arguments.trim().split("/", 2);
        if (deadlineString.length < 2) {
            return new IncorrectCommand("I need a NAME AND TIME (use /by <DEADLINE>).");
        }
        else {
            return new DeadlineCommand(deadlineString, shouldPrint);
        }
    }

    /**
     * Prepares an EventCommand by extracting the description, start time, and end time.
     *
     * @param arguments The string containing the task details, separated by "/".
     * @param shouldPrint True to print confirmation upon execution, false otherwise.
     * @return The constructed EventCommand or an IncorrectCommand if missing required parts.
     */
    private static Command prepareEvent(String arguments, boolean shouldPrint) {
        String[] eventString = arguments.trim().split("/", 3);
        if (eventString.length < 3) {
            return new IncorrectCommand(String.format("I need a NAME and TWO TIMINGS! >_< " +
                            "%n (Hint: Event <EVENT_NAME> /<START_TIME>  /<END_TIME>"));
        }
        return new EventCommand(eventString, shouldPrint);
    }

    /**
     * Prepares a FindCommand based on a search term.
     *
     * @param arguments The keyword to search for in the task list.
     * @return The constructed FindCommand or an IncorrectCommand if the search term is empty.
     */
    private static Command prepareFind(String arguments) {
        if (arguments == null || arguments.trim().isEmpty()) {
            return new IncorrectCommand(String.format("We found nothing for your nothing search term %n") +
                            String.format("pls type something to not get nothing i.e find <search_term>"));
        }
        return new FindCommand(arguments);
    }

    /**
     * Prepares a MarkCommand for marking or unmarking tasks, handling potentially multiple indices.
     *
     * @param arguments The raw string containing task numbers to mark/unmark.
     * @param cmd The parsed command (MARK or UNMARK) specifying the action to take.
     * @return The constructed MarkCommand or an IncorrectCommand if indices are missing/invalid.
     */
    private static Command prepareMarkIndexes(String arguments, AllCommands cmd){

        boolean shouldMark = false;
        boolean shouldPrint = true;

        if (cmd.equals(AllCommands.MARK)) {
            shouldMark = true;
        }

        String numberString = arguments.replaceAll("[a-zA-Z].*", "");
        String[] numberStringArray = numberString.trim().split("[,\\s]+"); // split into
        int sizeofString = numberStringArray.length;
        List<Integer> numberArray = new ArrayList<>();

        for (int j = 0; j < sizeofString; j++) {
            numberArray.add(Integer.parseInt(numberStringArray[j]));
        }

        if (numberArray.isEmpty()) {
            return new IncorrectCommand("podles is empty, like your input, give podles a valid task index");
        }

        return new MarkCommand(numberArray, shouldMark, shouldPrint);
    }

}



