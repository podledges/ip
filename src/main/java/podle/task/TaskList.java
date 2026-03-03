package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.*;

import java.util.ArrayList;

import java.io.IOException;

import static podle.storage.Storage.*;
import podle.storage.Storage;

public class TaskList {

    private static int taskAmount;
    private static ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
        this.taskAmount = 0;
    }

    public static String taskString(int index){
        return String.format("%d." + taskList.get(index).toString(),index+1);
    }

    public static void listTask(){
        Ui.podlesWill("list");
        Ui.printLine();
        for(int j = 0; j < taskList.size(); j++) {
            Ui.printMessage(taskString(j));
        }
        Ui.printLine();
    }

    public static void markList(int inputNumber, boolean shouldMark, boolean shouldPrint) throws IOException {
        if ((inputNumber > taskList.size()) || inputNumber <= 0) {
            throw new InvalidInputException(String.format("Podles is sad because the task index: %d ", inputNumber +
                            "is out of range, try an index that is 1 <= <input> <= %d ", inputNumber));
        }
        int taskListIndex = inputNumber -1;
        if (shouldMark) {
            taskList.get(taskListIndex).markDone(shouldPrint);
        }
        else {
            taskList.get(taskListIndex).markNotDone(shouldPrint);
        }
        if (shouldPrint) {
            listTask();
        }
    }

    public void addTask(Task newTask, Boolean shouldPrint){
        taskList.add(newTask) ;
        if(shouldPrint) {
            Ui.printLine();
            Ui.printMessage(String.format("Podles has added: %n   %s", newTask.toString() +
                    String.format(" %n now you have %d tasks left in the list.", taskList.size())));
            Ui.printLine();
        }
    }

    public static void deleteTask(int index) throws IOException {
        if ((index > taskList.size()) || index <= 0) {
            throw new InvalidInputException(String.format("Podles sincerely regrets to inform you, that %d", index) +
                            String.format("seems to be out of range, try an index that is 1 <= <input> <= %d", index));
        }
        int taskListIndex = index -1;
        Ui.printLine();
        Ui.printMessage("Podles has removed the task below from the list!!!!");
        Ui.printMessage(taskString(taskListIndex));
        taskList.remove(taskListIndex);
        System.out.println("You have " + taskList.size() + " remaining tasks in the list!!" );
        Ui.printLine();
    }
}
