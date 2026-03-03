package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.Ui;

public abstract class Task {

    protected  String taskName;
    protected boolean isDone;

    public Task(String s){
        if(s.isEmpty()){
            throw new InvalidInputException(String.format(  "Your task cant NOT have a name %n" +
                                                            "How else will you get attched to it?" +
                                                            " Try adding it again! (WITH A NAME THIS TIME)"));
        }
        this.taskName = s;
        this.isDone = false;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public void markDone(){
        isDone = true;
        Ui.printMarkedMessage();
    }

    public void markNotDone(){
        isDone = false;
        Ui.printUnmarkedMessage();
    }


    public abstract String toFileFormat();



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
