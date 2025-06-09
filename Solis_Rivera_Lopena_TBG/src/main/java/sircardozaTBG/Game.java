package sircardozaTBG;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static Scanner scanner = new Scanner(System.in);

    public static int startDungeon(Hero hero, Dungeon dungeon, int playerGold) {
        System.out.println("🏰 You enter the " + dungeon.getName() + " dungeon...");

        for (int level = 1; level <= dungeon.getLevels(); level++) {
            System.out.println("\n🚶 Advancing to Level " + level);

            if (Math.random() < dungeon.getMonsterSpawnRate()) { // Roll if monster is spawned
                Monster monster = dungeon.getRandomMonster(); //spawn a monster
                System.out.println("🔥 A " + monster.getName() + " appeared!");
                playerGold = startBattle(hero, monster, dungeon, playerGold); // Updated gold after battle
                if (hero.isDefeated()) {
                    return playerGold; // Return current gold since hero died
                }
            }

            if (Math.random() < dungeon.getLootSpawnRate()) { // NON FUNCTIONAL YET
                System.out.println("💰 You found " + dungeon.getRandomLoot() + "!");
            }

            while (true) {
                System.out.print("\nWhat do you want to do? (proceed, exit, inventory): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("proceed")) {
                    break;
                } else if (choice.equalsIgnoreCase("exit")) {
                    System.out.println("🚪 You exit the dungeon.");
                    return playerGold; // Return updated player gold to main method
                } else if (choice.equalsIgnoreCase("inventory")) {
                    System.out.println("🎒 Managing inventory... (Feature coming soon!)");
                } else {
                    System.out.println("⛔ Invalid choice! Try again.");
                }
            }
        }

        System.out.println("\n🏆 You completed the " + dungeon.getName() + "!");
        return playerGold; // Return updated gold after dungeon completion
    }

    public static int startBattle(Hero hero, Monster monster, Dungeon dungeon, int playerGold) {
        System.out.println("\n⚔️ Battle starts between " + hero.getName() + " and " + monster.getName());

        while (!hero.isDefeated() && !monster.isDefeated()) {
            if (hero.getSpeed() >= monster.getSpeed()) {
                playerTurn(hero, monster);
                if (monster.isDefeated()) {
                    break;
                }

                executeAttack(monster, hero);
                if (hero.isDefeated()) {
                    break;
                }
            } else {
                executeAttack(monster, hero);
                if (hero.isDefeated()) {
                    break;
                }

                playerTurn(hero, monster);
                if (monster.isDefeated()) {
                    break;
                }
            }
        }

        return endBattle(hero, monster, dungeon, playerGold); // Updated gold after battle
    }

    public static void playerTurn(Hero hero, Monster monster) {
        System.out.println("\n🎮 Your turn! Choose an action:");
        System.out.println("Type: 'attack', 'defend', or 'item'");

        String choice = scanner.nextLine();

        switch (choice.toLowerCase()) {
            case "attack":
                executeAttack(hero, monster);
                break;
            case "defend":
                System.out.println(hero.getName() + " braces for impact, reducing damage taken!");
                hero.reduceDamage();
                break;
            case "item":
                System.out.println("🎒 Inventory feature coming soon!");
                break;
            default:
                System.out.println("⛔ Invalid choice! You lose your turn.");
        }
    }

    private static void executeAttack(Character attacker, Character defender) {
        int attackValue = attacker.getAttack();
        int finalDamage = (int) (attackValue * (1 - (defender.getArmor() / (defender.getArmor() + 100))));

        defender.takeDamage(finalDamage);

        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + attackValue + " damage! (Final Damage: " + finalDamage + ")");

        // Display HP after each attack
        System.out.println("🔹 " + attacker.getName() + " HP: " + attacker.getHealth());
        System.out.println("🔹 " + defender.getName() + " HP: " + defender.getHealth());
    }

    private static int endBattle(Hero hero, Monster monster, Dungeon dungeon, int playerGold) {
        if (hero.isDefeated()) {
            System.out.println("💀 " + hero.getName() + " was defeated!");
            return playerGold; // No change in gold
        } else {
            System.out.println("🏆 " + hero.getName() + " defeated " + monster.getName() + "!");

            // Gold Drop Calculation (1/3 of dungeon entry fee ± random variation)
            int baseGoldDrop = dungeon.getEntryFee() / 3;
            int variance = (int) (Math.random() * 5 - 2); // ±2 gold randomness
            int goldEarned = Math.max(1, baseGoldDrop + variance); // Ensure at least 1 gold

            playerGold += goldEarned;
            System.out.println("💰 You earned " + goldEarned + " gold!");

            return playerGold; // Return updated gold amount
        }
    }

    public static void main(String[] args) {
        int playerGold = 200; // Start with 200 gold
        Hero hero = selectHero(); // Let players choose their hero
        //Hero hero = new Hero("Arthur", 100, 15, 30, 10.5, 12.8); old template character

        Dungeon crypt = new Dungeon("Crypt",
                new Monster[]{
                    new Monster("Skeleton", 60, 10, 20, 8.0, 5.5),
                    new Monster("Zombie", 80, 12, 15, 6.0, 4.0)},
                new String[]{"Ancient Coin", "Cursed Amulet"},
                5,
                0.4,
                0.2,
                 0);

        Dungeon cave = new Dungeon("Cave",
                new Monster[]{
                    new Monster("Goblin", 70, 14, 18, 7.5, 6.5),
                    new Monster("Troll", 120, 18, 25, 9.5, 3.5)},
                new String[]{"Gold Nugget", "Rare Gem"},
                6,
                0.5,
                0.3,
                 50);

        while (true) {
            System.out.println("\n🌆 Welcome to town! What would you like to do?");
            System.out.println("1. Enter Crypt (5 Levels) - 💰 Cost: " + crypt.getEntryFee());
            System.out.println("2. Enter Cave (6 Levels) - 💰 Cost: " + cave.getEntryFee());
            System.out.println("3. View Gold (" + playerGold + " gold)");
            System.out.println("X. Exit Program");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("1")) {
                if (playerGold >= crypt.getEntryFee()) {
                    playerGold -= crypt.getEntryFee(); // Deduct entry fee
                    playerGold = startDungeon(hero, crypt, playerGold); // Update gold after dungeon run
                    if (hero.isDefeated()) {
                        hero = buyNewHero(playerGold); // Offer hero purchase if defeated
                    }
                } else {
                    System.out.println("⛔ Not enough gold!");
                }
            } else if (choice.equals("2")) {
                if (playerGold >= cave.getEntryFee()) {
                    playerGold -= cave.getEntryFee();
                    playerGold = startDungeon(hero, cave, playerGold); // Update gold after dungeon run
                    if (hero.isDefeated()) {
                        hero = buyNewHero(playerGold);
                    }
                } else {
                    System.out.println("⛔ Not enough gold!");
                }
            } else if (choice.equals("3")) {
                System.out.println("💰 You have " + playerGold + " gold.");
            } else if (choice.equals("X")) {
                System.out.println("👋 Thanks for playing! Exiting game...");
                System.exit(0);
            } else {
                System.out.println("⛔ Invalid choice! Try again.");
            }
        }
    }

    public static Hero buyNewHero(int playerGold) {
        int heroCost = 100; // Fixed price for new hero

        System.out.println("\n💰 Your hero has fallen! You can buy a new hero for " + heroCost + " gold.");
        System.out.println("Do you want to recruit a new hero? (yes/no)");

        while (true) {
            System.out.print("> ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                if (playerGold >= heroCost) {
                    playerGold -= heroCost; // Deduct gold
                    System.out.println("🏆 You recruited a new hero!");
                    return selectHero(); // Reuse hero selection method instead of rewriting logic
                } else {
                    System.out.println("⛔ Not enough gold! Game Over.");
                    System.exit(0);
                }
            } else if (choice.equals("no")) {
                System.out.println("💀 You chose not to recruit a new hero. Game Over.");
                System.exit(0);
            } else {
                System.out.println("⛔ Invalid choice! Try again.");
            }
        }
    }

    public static Hero selectHero() {
        System.out.println("\n🦸 Choose your hero!");
        System.out.println("1. Warrior (High HP, Strong Attack)");
        System.out.println("2. Rogue (Fast, High Critical Damage)");
        System.out.println("3. Mage (Powerful Magic, Low Armor)");

        while (true) {
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                return new Hero("Warrior", 150, 20, 35, 12.0, 10.0);
            } else if (choice.equals("2")) {
                return new Hero("Rogue", 100, 15, 40, 8.0, 20.0);
            } else if (choice.equals("3")) {
                return new Hero("Mage", 80, 25, 50, 5.0, 12.0);
            } else {
                System.out.println("⛔ Invalid choice! Try again.");
            }
        }
    }

}
