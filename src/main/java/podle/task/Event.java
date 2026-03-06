package podle.task;

public class Event extends Task {
    private final String eventStart;
    private final String eventEnd;
    public Event(String[] eventString) {
        super(eventString[0]);
        this.eventStart = eventString[1];
        this.eventEnd = eventString[2];
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + this.taskName + String.format("(From: %s To: %s)", this.eventStart, this.eventEnd);
        }
        else {
            return "[E][ ] " + this.taskName + String.format("(From: %s To: %s)", this.eventStart, this.eventEnd);
        }
    }

    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s-%s%n",status, this.taskName, this.eventStart, this.eventEnd);
    }

    public static Event fromFileFormat(String[] parts) {
        String[] times = parts[3].split("[-/]");
        String[] data = {parts[2].trim(), times[0].trim(), times[1].trim()};
        return new Event(data);
    }

}
