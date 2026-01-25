import java.util.Scanner;

public class podleGPT {

        public static void podlesWill(String input){        // simply prints Podles will:
            System.out.println("Podles will " + input + ":");
        }
        public static void added(String input){
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
        }
        public static void list(String[] tasks, int taskAmount){
            System.out.println("____________________________________________________________");
            for(int j = 0; j<= taskAmount; j++){
                if(tasks[j] != null)
                    System.out.println( j+1 + ".[" + tasks[j]);
            }
            System.out.println("____________________________________________________________");
        }

        public static void main(String[] args) throws InterruptedException {

            System.out.println(podleLogo.logo);
            System.out.println("HELLO! I am Podles! made by Podles!");
            System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");

            String[] todo = new String[101];
            int taskAmount = 0;

            while(true) {
                String input;
                Scanner in = new Scanner(System.in);
                input = in.nextLine();


                if (input.equalsIgnoreCase("bye")) {                                         // termination command
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
                        list(todo, taskAmount);
                }

                else{
                    todo[taskAmount] = input;
                    taskAmount += 1;
                    added(input);

                }



            }

        }
}
