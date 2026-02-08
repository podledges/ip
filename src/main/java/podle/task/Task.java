package podle.task;
import podle.exception.InvalidInputException;

public class Task {

    private String taskName;
    private boolean isDone;
    private Category category;

    public Task(String s, Category category){
        if(s.isEmpty()){
            throw new InvalidInputException(String.format(  "Your task cant NOT have a name %n" +
                                                            "How else will you get attched to it?" +
                                                            " Try adding it again! (WITH A NAME THIS TIME)"));
        }
        this.taskName = s;
        this.isDone = false;
        this.category = category;
        podlesWill(category.name());
    }

    public boolean isDone(){
        return this.isDone;
    }

    public String toString() {
        if(isDone){
        return String.format("[%s][X] " + this.taskName, category.name().charAt(0));
        }
        else {
        return String.format("[%s][ ] " + this.taskName, category.name().charAt(0));
        }
    }
    public static void podlesWill(String input){        // simply prints Podles will:
        System.out.println("Podles will add " + input);
    }

    public void markDone(){
        isDone = true;
    }

    public void markNotDone(){
        isDone = false;
    }
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
