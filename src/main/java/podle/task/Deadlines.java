package podle.task;

public class Deadlines extends Task{

    private String by;

    public Deadlines(String[] deadlineString) {
        super(deadlineString[0]);
        this.by = extractDay(deadlineString[1]);
    }

    @Override
    public String toString() {
        if(isDone){
            return String.format("[D][X] " + this.taskName + String.format("(do by: %s)", this.by));
        }
        else {
            return String.format("[D][ ] " + this.taskName + String.format("(do by: %s)", this.by));
        }
    }

    public static Deadlines fromFileFormat(String[] parts) {    // parts[0] is 'D', parts[1] is status, parts[2] is description, parts[3] is 'by'
        String[] data = {parts[2].trim(), parts[3].trim()};
        return new Deadlines(data);
    }

    @Override
    public String toFileFormat() {
        int status = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s",status, this.taskName, this.by);
    }
}


