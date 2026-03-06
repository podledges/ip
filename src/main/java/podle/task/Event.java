package podle.task;

/**
 * Represents an event task that occurs between a specific start and end time.
 */
public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;

    /**
     * Initializes an Event task based on an array of string parameters.
     *
     * @param eventString An array containing the description, start time, and end time respectively.
     */
    public Event(String[] eventString) {
        super(eventString[0]);
        this.eventStart = eventString[1];
        this.eventEnd = eventString[2];
    }

    /**
     * Returns the string representation of the event for display purposes.
     *
     * @return The formatted string detailing the event, its completion status, and timing.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + this.taskName + String.format("(From: %s To: %s)", this.eventStart, this.eventEnd);
        }
        else {
            return "[E][ ] " + this.taskName + String.format("(From: %s To: %s)", this.eventStart, this.eventEnd);
        }
    }

    /**
     * Converts the event task into a format suitable for file storage.
     *
     * @return A formatted string representing the event's state.
     */
    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s-%s%n",status, this.taskName, this.eventStart, this.eventEnd);
    }

    /**
     * Reconstructs an Event object from a saved file record.
     *
     * @param parts An array of string tokens read from the storage file.
     * @return The reconstructed Event task.
     */
    public static Event fromFileFormat(String[] parts) {
        String[] times = parts[3].split("[-/]");
        String[] data = {parts[2].trim(), times[0].trim(), times[1].trim()};
        return new Event(data);
    }

}
