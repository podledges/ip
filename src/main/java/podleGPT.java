import java.util.Scanner;

public class podleGPT {



        public static void main(String[] args) throws InterruptedException {

            System.out.println(podleLogo.logo);
            System.out.println("HELLO! I am Podles! made by Podles!");
            System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");

            new TaskList();
            Scanner in = new Scanner(System.in);
            while(true) {
                String input = in.nextLine();;

                String[] inputParts = input.trim().split(" ",2);
                String commandWord = inputParts[0].toUpperCase();
                String arguments = (inputParts.length > 1) ? inputParts[1] : "";

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
                    System.out.println("podles was terminated...");
                    System.exit(0);
                    break;
                case BYEBYE:
                    System.out.println("deadge");
                    System.exit(0);
                case MARK:
                    String withoutCommand = input.replaceFirst("^[a-zA-Z]+\\s*", "");                           //strip "mark"
                    String tempString = withoutCommand.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
                    String[] numberString = tempString.trim().split("[,\\s]+"); // split into
                    if (input.contains("unmark")) {
                        TaskList.markList(numberString, false);
                    } else {
                        TaskList.markList(numberString, true);
                    }
                    break;

                case LIST:
                    TaskList.listTask();
                    break;

                case TODO:
                    TaskList.addTask(new ToDo(arguments));
                    break;

                case DEADLINE:
                    String[] deadlineString = arguments.trim().split("/", 2); // split into
                    TaskList.addTask(new Deadlines(deadlineString));
                    break;

                case EVENT:
                    String[] eventString = arguments.trim().split("/", 3); // split into
                    TaskList.addTask(new Events(eventString));
                    break;

                case UNKNOWN:
                default:
                    TaskList.addTask(new Tasks(input, Category.TASK));
                    break;
                }
            }
        }
}
