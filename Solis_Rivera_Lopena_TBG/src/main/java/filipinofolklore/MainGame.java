package filipinofolklore;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainGame {

    private static final Random rand = new Random();
    private static Player player;
    private static MonsterHandler monsterHandler;
    private static final Travel travel = new Travel();
    ;
    private static final Scanner scn = new Scanner(System.in);
    private static final Colors color = new Colors();
    private static boolean inGame = true;
    private static String pName;
    private static boolean moved = false;

    public static void main(String[] args) {
        int choice = 0;

        while (true) {

            System.out.println(color.red() + "================================");
            System.out.println("||                            ||");
            System.out.println("||                            ||");
            System.out.println("||       Seek ye Horror       ||");
            System.out.println("||                            ||");
            System.out.println("||                            ||");
            System.out.println("================================");
            System.out.println(color.green() + "1. Start Game");
            System.out.println(color.red() + "2. Exit Game");

            while (true) {
                System.out.print(color.reset() + "\nEnter your choice: ");
                String input = scn.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(color.red() + "Invalid choice, try again.");
                    scn.nextLine();
                }
            }

            switch (choice) {
                case 1 -> {
                    if (travel.getAreaCounter() != 0) {
                        System.out.println("\n-*-*-*-*-GAME CONTINUED-*-*-*-*-");
                        gameStart();
                    } else {
                        System.out.println("\n-*-*-*-*-GAME START-*-*-*-*-");
                        System.out.print("Enter your username: ");
                        pName = scn.nextLine();
                        player = new Player(pName);
                        monsterHandler = new MonsterHandler();
                        gameStart();
                    }
                    scn.nextLine();
                }
                case 2 -> {
                    System.out.println(color.pink() + "Exiting game... Goodbye!");
                    return;
                }
                default -> {
                    System.out.println(color.red() + "Invalid choice, try again.");
                }
            }
        }
    }

    private static void gameStart() {
        inGame = true;
        while (inGame) {
            System.out.println("");
            System.out.println(travel.getAreaMessage() + travel.tileCheck() + "");

            if (moved) {
                player.spawnWeapons(travel.getAreaCounter()); // Spawn weapon when moving tiles
                monsterSpawned(); // Spawn Monster when moving tiles
                //Ends the game when player dies.
                if (inGame == false) {
                    travel.resetCounter();
                    break;
                }
                moved = false;
            }

            System.out.println("");
            System.out.println("What would you like to do right now?");
            System.out.println("");
            System.out.print(color.cyan() + pName + " HP: " + color.green());
            player.getPlayerHealthBar();
            System.out.println(color.reset() + "||" + color.green() + " Walk " + color.reset() + "||" + color.yellow() + " Sako (Check Inventory) " + color.reset() + "||" + color.red() + " Exit (Main Menu)" + color.reset() + " ||");
            System.out.print("> ");
            String action = scn.next();

            switch (action.toLowerCase()) {
                case "walk" -> {
                    travel.proceed();
                    checkBoss();
                    moved = true;
                }
                case "sako" -> {
                    player.openInventory(false);
                    System.out.println("");
                }
                case "exit" ->
                    inGame = false;
                default -> {
                    System.out.println("Invalid input, please try again");
                }
            }
        }
    }

    // Function to randomly spawn random monster from specific area
    private static void monsterSpawned() {
        ArrayList<Monster> spawnTable = monsterHandler.getSpawnPool(travel.getAreaCounter());

        // 1 in 3 chance for monster to spawn.
        if (rand.nextInt(3) > 0) { // When 0 is generated, monster will be spawned.
            return;
        }

        // randomly picks monster and starts battle
        if (!spawnTable.isEmpty()) {
            Monster monster = spawnTable.get(rand.nextInt(spawnTable.size()));
            startBattle(monster);
            if (!monster.isAlive()) {
                spawnTable.remove(monster);
            }
        }
    }

    // Function to start battle with monster as parameter
    private static void startBattle(Monster monster) {
        System.out.println("\n" + monster.getName() + " appeared!");
        BattleEncounter battle = new BattleEncounter(player, monster, travel);
        inGame = battle.startBattle();
    }

    private static void checkBoss() {
        if (travel.getAreaCounter() == 4 && travel.getTileCounter() == 1) {// Area 4 Tile 1 is the beginning of the Boss
            System.out.println(
                    "The mountain looms above you, the sky turns dark and the wind beginds to howl.\nA giant creature with incomprehensible nature stands before you.\nYou ready you weapon for the final showdown.");
            // INSERT BOSS BATTLE ENCOUNTER HERE

            // CODE AFTER BATTLE ENDS
            System.out.println(
                    "After defeating the beast you run up the mountain eager to return home. Right below, you see your cozy little house.\nThen you notice a darkness growing in the sea beside the mountain, a new creature emerges... ");
            System.out.println("Will you proceed to battle with the Bakunawa just beyond the Cliffs? (yes/no)");
            String choice = scn.next();

            boolean choosing = true;
            while (choosing) {
                switch (choice.toLowerCase()) {
                    case "yes" -> {
                        choosing = false;
                        System.out.println(
                                "\"You stare into the murky waters of the ocean, giant glowing eyes stare right back into your soul.\\n"
                                + //
                                "The horrendous creature from the depths breaches the water's surface and stands face to face with you.\\n"
                                + //
                                "Exhausted but determined, you brandish your weapom.\"");
                        // INSERT OPTIONAL BOSS BATTLE

                        goHome();
                    }
                    case "no" -> {
                        choosing = false;
                        goHome();
                    }
                    default ->
                        System.out.println("Not a valid input.");
                }
            }
        }
    }

    private static void goHome() {
        System.out.println("You went home.");
        inGame = false;

    }

    public static String getName() {
        return pName;
    }
    // END OF CLASS
}
