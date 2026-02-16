package podle.task;

public class Events extends Task {
    private String eventStart;
    private String eventEnd;
    public Events(String[] eventString) {
        super(eventString[0]);
        this.eventStart = eventString[1];
        this.eventEnd = eventString[2];
    }

    @Override
    public String toString() {
        if(isDone){
            return String.format("[E][X] " + this.taskName + String.format("(from: %s : %s)", this.eventStart, this.eventEnd));
        }
        else {
            return String.format("[E][ ] " + this.taskName + String.format("(from: %s : %s)", this.eventStart, this.eventEnd));
        }
    }

    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("E | %d | %s | %s-%s",status, this.taskName, this.eventStart, this.eventEnd);
    }


    public static Events fromFileFormat(String[] parts) {     // parts[0] is 'D', parts[1] is status, parts[2] is description, parts[3] is 'by'
        String[] times = parts[3].split("[-/]");
        String[] data = {parts[2].trim(), times[0].trim(), times[1].trim()};
        return new Events(data);
    }

}
