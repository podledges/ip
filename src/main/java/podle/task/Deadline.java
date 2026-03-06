package podle.task;

/**
 * Represents a deadline task that needs to be completed by a specific time or date.
 */
public class Deadline extends Task{

    private final String by;

    /**
     * Initializes a Deadline task based on an array of string parameters.
     *
     * @param deadlineString An array containing the description and deadline time respectively.
     */
    public Deadline(String[] deadlineString) {
        super(deadlineString[0]);
        this.by = extractDay(deadlineString[1]);
    }

    /**
     * Returns the string representation of the deadline for display purposes.
     *
     * @return The formatted string detailing the task, its completion status, and deadline.
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[D][X] " + this.taskName + String.format("(do by: %s)", this.by));
        }
        else {
            return String.format("[D][ ] " + this.taskName + String.format("(do by: %s)", this.by));
        }
    }

    /**
     * Reconstructs a Deadline object from a saved file record.
     *
     * @param parts An array of string tokens read from the storage file.
     * @return The reconstructed Deadline task.
     */
    public static Deadline fromFileFormat(String[] parts) {    // parts[0] is 'D', parts[1] is status, parts[2] is description, parts[3] is 'by'
        String[] data = {parts[2].trim(), parts[3].trim()};
        return new Deadline(data);
    }

    /**
     * Converts the deadline task into a format suitable for file storage.
     *
     * @return A formatted string representing the deadline's state.
     */
    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s%n",status, this.taskName, this.by);
    }
}


