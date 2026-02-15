package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.*;

import java.io.IOException;

import static podle.task.Storage.updateMark;
import static podle.task.Storage.updateUnMark;
import static podle.ui.Ui.*;

public class TaskList {

    private static int taskAmount;
    private static Task[] taskList;

    public TaskList(){  //Constructor
        this.taskList = new Task[101];
        this.taskAmount = 0;
    }

    public static void listTask(){
        Ui.podlesWill("list");
        printLine();
        for(int j = 0; j<= taskAmount; j++) {
            if (taskList[j] != null) {
                System.out.println(String.format("%d." + taskList[j].toString(),j+1));
            }
        }
        printLine();
    }
    public static void markList(String[] input, boolean shouldMark) throws IOException {
        int sizeofString = input.length;
            for(int j = 0; j < sizeofString; j++) {
                int taskIndex = Integer.parseInt(input[j]);
                if (taskIndex > taskAmount || taskIndex <= 0) {
                    throw new InvalidInputException(String.format("Podles sincerely regres to inform you, that the podle.task.Task," +  // might have to shorten
                            " numbered with the index: " + taskIndex + " does not seem to currently exist within " +
                            " %n my database and it pains me to ask you to perhaps provide a taks index that is within" +
                            "the range of 1 <= <input> <= " + taskAmount + 1 + ", only if these conditions are met " +
                            " %n then will I have the power to MARK or UNMARK the specified task"
                            + " %n you can input multiple indexes, seperated by a comma or a space, to make up for time " +
                            "lost reading this long ass error message"));
                }
                if (shouldMark) {
                    taskList[taskIndex - 1].markDone();
                    updateMark(taskIndex);
                } else {
                    taskList[taskIndex - 1].markNotDone();
                    updateUnMark(taskIndex);
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

    public static void addTask(Task newTask){
        taskList[taskAmount] = newTask;
        taskAmount++;
        Ui.printLine();
        System.out.println(String.format(   "Podles has added: %n   " + taskList[taskAmount-1].toString() +
                                            "%n" + "now you have %d tasks in the list.", taskAmount));
        Ui.printLine();
    }
}
