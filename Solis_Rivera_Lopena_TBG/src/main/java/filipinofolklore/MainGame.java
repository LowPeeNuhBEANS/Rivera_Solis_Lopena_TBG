package filipinofolklore;

import java.util.*;

public class MainGame {

    private static final Player player = new Player();
    private static final Travel travel = new Travel();
    private static final Scanner scn = new Scanner(System.in);
    private static boolean inGame = true;

    public static void main(String[] args) {
        player.initWeapons();
        
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
        while (inGame) {
            System.out.println("");
            System.out.println(travel.getAreaMessage() + travel.tileCheck() + "");
            player.weaponSpawn(travel.getAreaCounter());
            System.out.println("What would you like to do right now?");
            System.out.println("");
            System.out.println("Players's HP: ");
            System.out.println("// Walk // Sako (Check Inventory) // Exit (Main Menu) //");
            System.out.print("Your Action: ");
            String action = scn.next();

            switch (action.toLowerCase()) {
                case "walk" ->
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
