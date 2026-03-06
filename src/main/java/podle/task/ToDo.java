package podle.task;

/**
 * Represents a standard to-do task that lacks a specific time or date attached to it.
 */
public class ToDo extends Task {

    /**
     * Initializes a ToDo task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the to-do task into a format suitable for file storage.
     *
     * @return A formatted string representing the task's state.
     */
    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("T | %d | %s%n" , status, this.taskName);
    }

    /**
     * Returns the string representation of the to-do task for display purposes.
     *
     * @return The formatted string showing the task's type, status, and description.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + this.taskName;
        }
        else {
            return "[T][ ] " + this.taskName;
        }
    }
}


