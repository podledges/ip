public class Events extends Tasks {
    private String eventStart;
    private String eventEnd;
    public Events(String[] eventString) {
        super(eventString[0], Category.EVENT);
        this.eventStart = eventString[1];
        this.eventEnd = eventString[2];
    }
    public String toString(){
        return (super.toString() + String.format("(from: %s : %s)", this.eventStart, this.eventEnd));
    }

}
