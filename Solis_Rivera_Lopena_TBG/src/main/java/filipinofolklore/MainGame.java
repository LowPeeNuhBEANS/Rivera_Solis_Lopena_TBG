package filipinofolklore;

import java.util.*;

public class MainGame {

    private static final Travel travel = new Travel();
    private static final Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPAUWI KA NA");
            System.out.println("\n-*-*-*-*-Main Menu-*-*-*-*-");
            System.out.println("1. Start Game");
            System.out.println("2. Exit Game");

            System.out.print("\nEnter your choice: ");
            int choice = scn.nextInt();
            scn.nextLine();

            switch (choice) {
                case 1 -> {
                    if (travel.getAreaCounter() != 0) {
                        System.out.println("\n-*-*-*-*-GAME CONTINUED-*-*-*-*-");
                        gameStart();
                    } else {
                        System.out.println("\n-*-*-*-*-GAME START-*-*-*-*-");
                        gameStart();
                    }
                }

                case 2 -> {
                    System.out.println("Exiting game... Goodbye!");
                    return;
                }

                default -> {
                    System.out.println("Invalid choice, try again.");
                }
            }
        }
    }

    private static void gameStart() {
        boolean inGame = true;

        while (inGame) {
            System.out.println("");
            System.out.println(travel.getAreaMessage() + travel.tileCheck() + "");
            System.out.println("What would you like to do right now?");
            System.out.println("");
            System.out.println("Players's HP: ");
            System.out.println("// Proceed // Sako (Check Inventory) // Exit (Return to Main Menu) //");
            System.out.print("Your Action: ");
            String action = scn.next();

            switch (action.toLowerCase()) {
                case "proceed" ->
                    travel.proceed();
                case "sako" ->
                    System.out.println("INSERT SACK");
                case "exit" ->
                    inGame = false;
                default -> {
                    System.out.println("Invalid input, please try again");
                }
            }
        }
    }

//END OF CLASS    
}
