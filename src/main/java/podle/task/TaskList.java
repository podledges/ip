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

    public TaskList(){  //Constructor
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

    public static void markList(int inputNumber, boolean shouldMark) throws IOException {
        if ((inputNumber > taskList.size()) || inputNumber <= 0) {
            throw new InvalidInputException(String.format("Podles is sad because the task index: %d ", inputNumber +
                            "is out of range, try an index that is 1 <= <input> <= %d ", inputNumber));
        }
        int taskListIndex = inputNumber -1;
        if (shouldMark) {
            taskList.get(taskListIndex).markDone();
        }
        else {
            taskList.get(taskListIndex).markNotDone();
        }
        markStorageList(inputNumber,shouldMark);
        listTask();
    }

    public static void addTask(Task newTask, Boolean shouldPrint){
        taskList.add(newTask) ;
        taskAmount++;
        if(shouldPrint) {
            Ui.printLine();
            Ui.printMessage(String.format("Podles has added: %n   %s", newTask.toString() +
                    "%n" + "now you have %d tasks in the list.", taskList.size()));
            Ui.printLine();
        }
    }

    public static void deleteTask(String index) throws IOException {
        int inputNumber = Integer.parseInt(index);
        if ((inputNumber > taskAmount) || inputNumber <= 0) {
            throw new InvalidInputException(String.format("Podles sincerely regrets to inform you, that %d", inputNumber +
                            "seems to be out of range, try an index that is 1 <= <input> <= %d", inputNumber));
        }
        int taskListIndex = inputNumber -1;
        Ui.printLine();
        Ui.printMessage("Podles has removed the task below from the list!!!!");
        Ui.printMessage(taskString(taskListIndex));
        taskList.remove(taskListIndex);
        Storage.deleteLine(inputNumber);
        System.out.println("You have " + taskList.size() + " remaining tasks in the list!!" );
        Ui.printLine();
    }
}
