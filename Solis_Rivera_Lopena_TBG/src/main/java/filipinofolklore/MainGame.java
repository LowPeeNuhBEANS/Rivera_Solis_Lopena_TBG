package filipinofolklore;

import java.util.Random;
import java.util.Scanner;

public class MainGame {

    private static final Random rand = new Random();
    private static final Player player = new Player();
    private static final Travel travel = new Travel();
    private static final Scanner scn = new Scanner(System.in);
    private static boolean inGame = true;
    private static int playerHp = 100; //[TESTING FOR HEALTHBAR]
    private static String pName;

    //COLORS [TESTING]
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PINK = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        int choice = 0;

        while (true) {
            System.out.println(RED + "================================");
            System.out.println("||                            ||");
            System.out.println("||                            ||");
            System.out.println("||       Seek ye Horror       ||");
            System.out.println("||                            ||");
            System.out.println("||                            ||");
            System.out.println("================================");
            System.out.println(GREEN + "1. Start Game");
            System.out.println(RED + "2. Exit Game");

            while (true) { 
                System.out.print(RESET + "\nEnter your choice: ");
                String input = scn.nextLine();
                
                try {
                    choice = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Invalid choice, try again.");
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
                        gameStart();
                    }
                    scn.nextLine();
                }
                case 2 -> {
                    System.out.println(PINK + "Exiting game... Goodbye!");
                    return;
                }
                default -> {
                    System.out.println(RED + "Invalid choice, try again.");
                }
            }
        }
    }

    private static void gameStart() {
        inGame = true;
        while (inGame) {
            System.out.println("");
            System.out.println(travel.getAreaMessage() + travel.tileCheck() + "");
            player.weaponSpawn(travel.getAreaCounter());
            monsterSpawned(); //Spawn Monster when moving tiles
            System.out.println("");
            System.out.println("What would you like to do right now?");
            System.out.println("");
            System.out.println(CYAN + pName + " HP: " + GREEN);
            player.getPlayerHealthBar();
            System.out.println("//" + GREEN + " Walk " + RESET + "//" + YELLOW + "Sako (Check Inventory)" + RESET + "//" + RED + " Exit (Main Menu)" + RESET + " //");
            System.out.print("Your Action: ");
            String action = scn.next();

            switch (action.toLowerCase()) {
                case "walk" -> {
                    travel.proceed();
                    checkBoss();
                }
                case "sako" ->
                    player.openInventory();
                case "exit" ->
                    inGame = false;
                default -> {
                    System.out.println("Invalid input, please try again");
                }
            }
        }
    }

    //Function to randomly spawn random monster from specific area
    private static void monsterSpawned() {
        //1 in 3 chance for monster to spawn.
        if (rand.nextInt(3) > 0) { //When 0 is generated, monster will be spawned.
            return;
        }

        //randomly picks monster and starts battle
        switch (travel.getAreaCounter()) {
            case 1 -> {
                Monster woodsMon = Monster.woodMonsters.get(rand.nextInt(Monster.woodMonsters.size())); // Woods
                startBattle(woodsMon);
                if (!woodsMon.isAlive()) {
                    Monster.woodMonsters.remove(woodsMon);
                }
            }
            case 2 -> {
                Monster swampMon = Monster.swampMonsters.get(rand.nextInt(Monster.swampMonsters.size())); // Swamp
                startBattle(swampMon);
                if (!swampMon.isAlive()) {
                    Monster.swampMonsters.remove(swampMon);
                }
            }
            case 3 -> {
                Monster villageMon = Monster.villageMonsters.get(rand.nextInt(Monster.villageMonsters.size())); // Village
                startBattle(villageMon);
                if (!villageMon.isAlive()) {
                    Monster.villageMonsters.remove(villageMon);
                }
            }
            default -> {
                // no monster encounter for other areas
            }
        }
    }

    //Function to start battle with monster as parameter
    private static void startBattle(Monster monster) {
        System.out.println("\n" + monster.getName() + " appeared!");
        BattleEncounter battle = new BattleEncounter(player, monster, travel);
        battle.startBattle();
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

//END OF CLASS    
    }
}
