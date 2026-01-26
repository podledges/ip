import java.util.Scanner;

public class podleGPT {

        public static void podlesWill(String input){        // simply prints Podles will:
            System.out.println("Podles will " + input + ":");
        }

        public static void main(String[] args) throws InterruptedException {

            System.out.println(podleLogo.logo);
            System.out.println("HELLO! I am Podles! made by Podles!");
            System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");

            new TaskList();

            while(true) {
                String input;
                Scanner in = new Scanner(System.in);
                input = in.nextLine();

                if (input.equalsIgnoreCase("bye")) { // termination sequence
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
                }

                else if (input.equalsIgnoreCase("list")) {
                        podlesWill(input);
                        TaskList.listTask();
                }
                else if (input.contains("mark")) {
                    // add check for empty, and prompt if empty
                    String withoutCommand = input.replaceFirst("^[a-zA-Z]+\\s*", "");                           //strip "mark"
                    String tempString = withoutCommand.replaceAll("[a-zA-Z].*", ""); // remove rtandom text towards the end
                    String[] numberString = tempString.trim().split("[,\\s]+"); // split into
                    if (input.contains("unmark")){
                        TaskList.markList(numberString,false);
                    }
                    else {
                        TaskList.markList(numberString, true);
                    }
                }

                else{
                        TaskList.addTask(input);
                }
            }
        }
}
