package podle.ui;
import podle.ui.PodleLogo;

import java.util.Scanner;

public class Ui {

    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }


    public String getInput() {
        return input.nextLine();
    }
    public  void printGreeting() {
        System.out.println(PodleLogo.logo);
        System.out.println("HELLO! I am Podles! made by Podles!");  // print greeting
        System.out.println("What SHALL Podles do for you?! ( •◡-)-♡");
    }

    public static void printError(String e) {
        System.out.println(String.format("PODLError ( ˶°ㅁ°) !! : " + e + "%n Try again!"));
    }

    public static void printMessage(String s){
        System.out.println(s);
    }

    public static void podlesWill(String input){
        System.out.println("Podles will " + input + ":");
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printMarkedMessage(){
        System.out.println("what an AMAZING job!!" + "❀.(*´◡`*)❀" + "       MARKED!!");
    }
    public static void printUnmarkedMessage(){
        System.out.println("OOOPS   " + "(｡•́︿•̀｡)" + "        how did that get marked...");
    }
    public void printByeBye(){
        System.out.println("deadge");
    }

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
