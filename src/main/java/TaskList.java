public class TaskList {

    private static int taskAmount;
    private static Tasks[] taskList;

    public TaskList(){  //Constructor
        this.taskList = new Tasks[101];
        this.taskAmount = 0;
    }
    public static void podlesWill(String input){        // simply prints Podles will:
        System.out.println("Podles will " + input + ":");
    }

    public static void listTask(){
        podlesWill("list");
        System.out.println("____________________________________________________________");
        for(int j = 0; j<= taskAmount; j++) {
            if (taskList[j] != null) {
                System.out.println(String.format("%d." + taskList[j].toString(),j+1));
            }
        }
        System.out.println("____________________________________________________________");
    }
    public static void markList(String[] input, boolean markThis) {
        int sizeofString = input.length;
            for(int j = 0; j < sizeofString; j++) {
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

    public static void addTask(Tasks newTask){
        taskList[taskAmount] = newTask;
        taskAmount++;

        System.out.println("____________________________________________________________");
        System.out.println(String.format(   "Podles has added: %n   " + taskList[taskAmount-1].toString() +
                                            "%n" + "now you have %d tasks in the list.", taskAmount));
        System.out.println("____________________________________________________________");
    }
}
