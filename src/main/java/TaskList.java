public class TaskList {

    private static int taskAmount;
    private static Tasks[] taskList;

    public TaskList(){  //Constructor
        this.taskList = new Tasks[101];
        this.taskAmount = 0;
    }

    public static void listTask(){
        System.out.println("____________________________________________________________");
        for(int j = 0; j<= taskAmount; j++) {
            if (taskList[j] != null) {
                System.out.print(j + 1 + ".[");
                if (taskList[j].isDone()) {
                    System.out.print("x");
                } else {
                    System.out.print(" ");
                }
                System.out.println("] " + taskList[j].getTaskName());
            }
        }
        System.out.println("____________________________________________________________");
    }
    public static void markList(String[] input, boolean markThis) {
        int sizeofString = input.length;
        for (int j = 0; j < sizeofString; j++) {
            int taskIndex = Integer.parseInt(input[j]);
            if (markThis) {
                taskList[taskIndex - 1].markDone();
            } else {
                taskList[taskIndex - 1].markNotDone();
            }
        }
        listTask();
        if (markThis) {
            System.out.println("what an AMAZING job!!" + "❀.(*´◡`*)❀" + "       MARKED!!");
        }
        else {
            System.out.println("OOOPS   " + "(｡•́︿•̀｡)" + "        how did that get marked...");
        }
    }

    public static void addTask(String input){
        Tasks newTask = new Tasks(input);
        taskList[taskAmount] = newTask;
        taskAmount++;

        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
    }
}
