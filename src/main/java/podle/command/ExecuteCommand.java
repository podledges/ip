package podle.command;

import podle.exception.InvalidInputException;
import podle.task.Deadlines;
import podle.task.Events;
import podle.task.TaskList;
import podle.task.ToDo;
import podle.ui.Ui;

public class ExecuteCommand {

    private Command cmd;
    private String arg;

    public ExecuteCommand(Command cmd){
        this.cmd = cmd;
    }

    public ExecuteCommand(Command cmd, String s){
        this.cmd = cmd;
        this.arg = s;
    }

    public void Execute(TaskList tasks, Ui ui) throws InterruptedException {
        switch(cmd) {   // move to PARSER class
        case BYE:
            ui.printBye();
            System.exit(0);
            break;

        case BYEBYE:
            ui.printByeBye();
            System.exit(0);

        case MARK, UNMARK:  //Argument must contain a number or multiple numbers,
            try{
                String tempString = arg.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
                String[] numberString = tempString.trim().split("[,\\s]+"); // split into
                if (cmd.equals(Command.UNMARK)) {
                    TaskList.markList(numberString, false);
                } else {
                    TaskList.markList(numberString, true);
                }
            } catch(InvalidInputException e) {
                System.out.println(String.format("PODLError ( ˶°ㅁ°) !! : " + e.getMessage() + "%n Try again!"));
            }
            break;

        case LIST:
            TaskList.listTask();
            break;

        case ADD, TODO: // argument must contain some text or anything
            try {
                TaskList.addTask(new ToDo(arg));
            } catch(InvalidInputException e){
                System.out.println(String.format("PODLError ( ˶°ㅁ°) !! : " + e.getMessage() ));
            }
            break;

        case DEADLINE:  // TODO: HANDLE ERRORS FOR DEADLINE AND EVENT
            String[] deadlineString = arg.trim().split("/", 2); // split into
            TaskList.addTask(new Deadlines(deadlineString));
            break;

        case EVENT:     // argument must have a name followed by 2 Days or 2 Times or 2 DATEs
            String[] eventString = arg.trim().split("/", 3); // split into
            TaskList.addTask(new Events(eventString));
            break;

        case UNKNOWN:
        default:
            ui.printUnknownCommand();
            break;
        }
    }
}
