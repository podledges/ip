package podle.command;

import podle.storage.Storage;
import podle.task.TaskList;
import podle.ui.Ui;

public class FindCommand extends Command {
    private String searchedString;

    public FindCommand(String searchedString) {
        this.searchedString = searchedString;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.searchList(searchedString);
    }

    }
