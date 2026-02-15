package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.*;

import java.util.ArrayList;

import static podle.ui.Ui.*;

public class TaskList {

    private static int taskAmount;
    private static ArrayList<Task> taskList;

    public TaskList(){  //Constructor
        this.taskList = new ArrayList<>();
        this.taskAmount = 0;
    }

    public static void echoTask(int index){
        System.out.println(String.format("%d." + taskList.get(index).toString(),index+1));
    }
    public static void listTask(){
        Ui.podlesWill("list");
        Ui.printLine();
        for(int j = 0; j < taskAmount; j++) {
            if (taskList.get(j) != null) {
                echoTask(j);
            }
        }
        Ui.printLine();
    }
    public static void addTask(Task newTask){
        taskList.add(newTask) ;
        taskAmount++;
        Ui.printLine();
        System.out.println(String.format(   "Podles has added: %n   " + taskList.get(taskAmount - 1).toString() +
                "%n" + "now you have %d tasks in the list.", taskAmount));
        Ui.printLine();
    }

    public static void deleteTask(String input) {
            int taskIndex = Integer.parseInt(input);
            if ((taskIndex > taskAmount + 1) || taskIndex <= 0) {
                throw new InvalidInputException(String.format("Podles sincerely regres to inform you, that " + taskIndex +
                        " seems to be out of range, try an index that is  " + "1 <= <input> <= " + taskAmount + 1 + ", "));
                }
            Ui.printLine();
            System.out.println("Podles has removed the task below from the list!!!!");
            echoTask(taskIndex-1);
            taskList.remove(taskIndex - 1);
            taskAmount--;
            System.out.println("You have " + taskAmount + " remaining tasks in the list!!" );
            Ui.printLine();
    }


    public static void markList(String[] input, boolean shouldMark) {
        int sizeofString = input.length;
            for(int j = 0; j < sizeofString; j++) {
                int taskIndex = Integer.parseInt(input[j]);
                if ((taskIndex > taskAmount + 1) || taskIndex <= 0) {
                    throw new InvalidInputException(String.format("Podles sincerely regres to inform you, that " + taskIndex +
                         " seems to be out of range, try an index that is  " + "1 <= <input> <= " + taskAmount + 1 + ", "));
                }
                if (shouldMark) {
                    taskList.get(taskIndex - 1).markDone();
                } else {
                    taskList.get(taskIndex - 1).markNotDone();
                }
            }
        listTask();
        if (shouldMark) {
            Ui.printMarked();
        }
        else {
            Ui.printUnmarked();
        }
    }


}
