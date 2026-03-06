package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.Ui;

/**
 * Represents an abstract foundation for different types of tasks.
 * Stores common properties like the task's name and its completion status.
 */
public abstract class Task {

    protected  String taskName;
    protected boolean isDone;

    /**
     * Initializes a basic Task with a name.
     *
     * @param s The name or description of the task.
     * @throws InvalidInputException If the provided name is empty.
     */
    public Task(String s){
        if (s.isEmpty()) {
            throw new InvalidInputException(String.format(  "Your task must have a name (ノ●A●)ノ%n" +
                                                            "Try adding again! (WITH ADD <NAME> THIS TIME)"));
        }
        this.taskName = s;
        this.isDone = false;
    }

    /**
     * Checks if the task's name contains a specific search term.
     *
     * @param searchTerm The keyword to search for (case-insensitive).
     * @return True if the term is found within the task name, false otherwise.
     */
    public boolean hasSearchTerm(String searchTerm){
        return (taskName.toLowerCase().contains(searchTerm.toLowerCase()));
    }

    /**
     * Marks the task as completed.
     *
     * @param shouldPrint True to print a confirmation message to the UI, false otherwise.
     */
    public void markDone(boolean shouldPrint){
        isDone = true;
        if (shouldPrint) {
            printMarkedMessage();
        }
    }

    /**
     * Marks the task as incomplete.
     *
     * @param shouldPrint True to print a confirmation message to the UI, false otherwise.
     */
    public void markNotDone(boolean shouldPrint){
        isDone = false;
        if (shouldPrint) {
            Ui.printUnmarkedMessage();
        }
    }


    public abstract String toFileFormat();


    /**
     * Extracts and standardizes a day of the week from the given input string, if present.
     *
     * @param input The raw date or day string.
     * @return A capitalized day string if matched, otherwise returns the original trimmed input.
     */
    public String extractDay(String input) {
        String upperInput = input.toUpperCase();

        for (Day day : Day.values()) {
            if (upperInput.contains(day.name())) {
                String dayName = day.name();
                return dayName.charAt(0) + dayName.substring(1).toLowerCase();
            }
        }
        return input.trim();
    }


}
