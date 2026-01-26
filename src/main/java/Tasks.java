public class Tasks {

    private String taskName;
    private boolean isDone;

    public Tasks(String s){
        this.taskName = s;
        this.isDone = false;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public String getTaskName(){
        return taskName;
    }

    public void markDone(){
        isDone = true;
    }

    public void markNotDone(){
        isDone = false;
    }

}
