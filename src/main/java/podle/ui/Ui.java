package podle.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the application. Handles all read and write interactions with the console.
 */
public class Ui {

    private final Scanner input;

    /**
     * Constructs a new Ui instance. Initializes the scanner to read from system input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user. Returns the full string entered into the console.
     *
     * @return The user's input line.
     */
    public String getInput() {
        return input.nextLine();
    }

    /**
     * Displays the initial greeting sequence. Prints the Podle logo and a welcome message.
     */
    public void printGreeting() {
        System.out.println(PodleLogo.LOGO);
        System.out.println("HELLO! I am Podles! made by Podles!");  // print greeting
        System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");
    }

    /**
     * Displays a formatted error message. Wraps the provided exception text in a standardized error layout.
     *
     * @param e The specific error message to display.
     */
    public void printError(String e) {
        System.out.println("PODLError ( ˶°ㅁ°) !! : " + e + "%n Try again!");
    }

    /**
     * Prints a standard text message. Outputs the provided string directly to the console.
     *
     * @param s The message to print.
     */
    public void printMessage(String s) {
        System.out.println(s);
    }

    /**
     * Displays a confirmation of an upcoming action. Used to acknowledge a user's request before processing it.
     *
     * @param input The action that Podles is about to perform.
     */
    public void printPodlesWill(String input){
        System.out.println("Podles will " + input + ":");
    }

    /**
     * Prints a horizontal divider line. Used to visually separate different UI outputs.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a celebratory message. Called when a task is successfully marked as done.
     */
    public static void printMarkedMessage() {
        System.out.println("what an AMAZING job!!" + "❀.(*´◡`*)❀" + "       MARKED!!");
    }

    /**
     * Displays a disappointed message. Called when a task is changed back to an incomplete status.
     */
    public static void printUnmarkedMessage() {
        System.out.println("OOOPS   " + "(｡•́︿•̀｡)" + "        how did that get marked...");
    }

    /**
     * Displays a brief farewell message. Used for the quick exit command.
     */
    public void printByeBye(){
        System.out.println("deadge");
    }

    /**
     * Displays a dramatic, animated farewell sequence. Prints a mock progress bar before application termination.
     *
     * @throws InterruptedException If the thread sleep timer is interrupted.
     */
    public static void printBye() throws InterruptedException {
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
    }

    /**
     * Displays an animated response to unrecognized commands. Pretends to think before prompting the user for valid input.
     *
     * @throws InterruptedException If the thread sleep timers are interrupted.
     */
    public static void printUnknownCommand() throws InterruptedException {
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
    }

}
