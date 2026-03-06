package podle.task;
import podle.exception.InvalidInputException;
import podle.ui.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents a list of tasks and handles operations related to task management.
 * Provides functionality to add, delete, search, and list tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Initializes a new TaskList with the specified user interface.
     *
     * @param ui The user interface used for printing messages.
     */
    public TaskList(Ui ui){
        this.taskList = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Retrieves the string representation of a task at the given index, formatted for display.
     *
     * @param index The zero-based index of the task.
     * @return The formatted task string.
     */
    public String taskString(int index){
        return String.format("%d." + taskList.get(index).toString(),index+1);
    }

    /**
     * Prints all tasks currently stored in the task list.
     */
    public void listTask(){
        ui.printPodlesWill("list");
        ui.printLine();
        for (int j = 0; j < taskList.size(); j++) {
            ui.printMessage(taskString(j));
        }
        ui.printLine();
    }

    /**
     * Prints specific tasks from the list based on provided indices.
     *
     * @param resultList A list of indices corresponding to the search results.
     * @param description The search term that was used to find these tasks.
     */
    public void listRelevantTasks(ArrayList<Integer> resultList, String description){
        ui.printLine();
        ui.printPodlesWill("Search for");
        ui.printMessage(description);
        for (int i : resultList){
            ui.printMessage(taskString(i));
        }
        ui.printLine();
    }

    /**
     * Marks a specific task as done or not done.
     *
     * @param inputNumber The 1-based index of the task to mark.
     * @param shouldMark True to mark the task as done, false to mark it as not done.
     * @param shouldPrint True to print the updated list after marking, false otherwise.
     * @throws IOException If an input/output error occurs during marking.
     * @throws InvalidInputException If the inputNumber is out of the valid range.
     */
    public void markList(int inputNumber, boolean shouldMark, boolean shouldPrint) throws IOException {
        if ((inputNumber > taskList.size()) || inputNumber <= 0) {
            throw new InvalidInputException(String.format("Podles is sad because the task index: %d %n", inputNumber) +
                            "is out of range, view valid indexes using: list");
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


    /**
     * Searches the task list for tasks containing the specified keyword and prints them.
     *
     * @param description The keyword or description to search for.
     */
    public void searchList(String description) {
       ArrayList<Integer> searchResults = new ArrayList<>();
       int i = 0;
       for (Task task : taskList) {
            if (task == null || taskList.isEmpty()) {
                continue;
            }
            else if (task.hasSearchTerm(description)) {
                searchResults.add(i);
            }
           i++;
        }

        listRelevantTasks(searchResults, description);

    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask The task to be added.
     * @param shouldPrint True to print a confirmation message, false otherwise.
     */
    public void addTask(Task newTask, Boolean shouldPrint){
        taskList.add(newTask);
        if (shouldPrint) {
            ui.printLine();
            ui.printMessage(String.format("Podles has added: %n   %s", newTask.toString()) +
                    String.format("%n" + "now you have %d tasks left in the list.", taskList.size()));
            ui.printLine();
        }
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The 1-based index of the task to delete.
     * @throws IOException If an input/output error occurs.
     * @throws InvalidInputException If the index is out of the valid range.
     */
    public void deleteTask(int index) throws IOException {
        if ((index > taskList.size()) || index <= 0) {
            throw new InvalidInputException(String.format("Podles sadge because, %d %n", index) +
                            String.format("seems to be out of range, view deletable indexes using: list"));
        }
        int taskListIndex = index -1;
        ui.printLine();
        ui.printMessage("Podles has removed the task below from the list!!!!");
        ui.printMessage(taskString(taskListIndex));
        taskList.remove(taskListIndex);
        System.out.println("You have " + taskList.size() + " remaining tasks in the list!!" );
        ui.printLine();
    }
}
