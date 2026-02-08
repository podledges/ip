public class Deadlines extends Task{

    private String by;

    public Deadlines(String[] deadlineString) {
        super(deadlineString[0], Category.DEADLINE);
        this.by = extractDay(deadlineString[1]);
    }

    public String toString(){
            return super.toString() + String.format("(do by: %s)", this.by) ;
    }
}
