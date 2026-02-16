package podle.task;

public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
    int status = isDone ? 1 : 0;
    return String.format("T | %d | %s" , status, this.taskName);
    }

    public static ToDo fromFileFormat(String[] parts) {     // parts[0] is 'D', parts[1] is status, parts[2] is description, parts[3] is 'by'
        return new ToDo(parts[2]);
    }
    @Override
    public String toString() {
        if(isDone){
            return String.format("[T][X] " + this.taskName);
        }
        else {
            return String.format("[T][ ] " + this.taskName);
        }
    }
}


