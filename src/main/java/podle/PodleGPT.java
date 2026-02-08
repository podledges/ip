package podle;

import podle.task.Deadlines;
import podle.task.Events;
import podle.task.TaskList;
import podle.task.ToDo;
import podle.ui.PodleLogo;
import podle.command.Command;
import podle.exception.InvalidInputException;
import java.util.Scanner;

public class PodleGPT {



        public static void main(String[] args) throws InterruptedException {

            System.out.println(PodleLogo.logo);
            System.out.println("HELLO! I am Podles! made by Podles!");
            System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");

            new TaskList();
            Scanner in = new Scanner(System.in);
            while(true) {
                String input = in.nextLine();;

                String[] inputParts = input.trim().split(" ",2);    // split after the first space char
                String commandWord = inputParts[0].toUpperCase();
                String arguments = (inputParts.length > 1) ? inputParts[1] : "";   // checking if the second part is not empty,

                // add error handling here -- as in like the splitting of the input


                Command cmd = Command.UNKNOWN;
                for(Command c : Command.values()){
                    if(commandWord.equalsIgnoreCase(c.name())) {
                        cmd = c;
                        break;
                    }
                }
                switch(cmd) {
                case BYE:
                    System.out.println("Y dont u want to play with podles .·°՞(っ-ᯅ-ς)՞°·. SADGE");
                    System.out.println("\n" + "████ 22%");
                    Thread.sleep(800);
                    System.out.println("███████ 43.3893%");
                    Thread.sleep(900);
                    System.out.println("██████████ 67.667%");
                    Thread.sleep(800);
                    System.out.println("████████████████ 89%");
                    Thread.sleep(700);
                    System.out.println("█████████████████████]99% ");
                    Thread.sleep(3333);
                    System.out.println("podles was terminated...    (╥﹏╥)");
                    System.exit(0);
                    break;

                case BYEBYE:
                    System.out.println("deadge");
                    System.exit(0);

                case MARK, UNMARK:  //Argument must contain a number or multiple numbers,
                    try{
                        String tempString = arguments.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
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
                        TaskList.addTask(new ToDo(arguments));
                    } catch(InvalidInputException e){
                        System.out.println(String.format("PODLError ( ˶°ㅁ°) !! : " + e.getMessage() ));
                    }
                    break;

                case DEADLINE:  // argument must have a name followed by a Day or a Time or a DATE
                    String[] deadlineString = arguments.trim().split("/", 2); // split into
                    TaskList.addTask(new Deadlines(deadlineString));
                    break;

                case EVENT:     // argument must have a name followed by 2 Days or 2 Times or 2 DATEs
                    String[] eventString = arguments.trim().split("/", 3); // split into
                    TaskList.addTask(new Events(eventString));
                    break;

                case UNKNOWN:
                default:
                    System.out.println("Oh okay podles will go do that... (˶˃ ᵕ ˂˶)");
                    System.out.println("ONE SECOND!");
                    System.out.print(".");
                    Thread.sleep(600);
                    System.out.print(".");
                    Thread.sleep(700);
                    System.out.println(".");
                    Thread.sleep(800);
                    System.out.println("what is Podles supposed to do for invalid commands again?... ༼ つ ◕_◕ ༽つ");
                    Thread.sleep(800);
                    System.out.println("Y dont u tell me a valid command instead?... (^>⩊<^)⟆");
                    break;
                }
            }
        }
}
