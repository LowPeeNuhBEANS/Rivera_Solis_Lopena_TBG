package filipinofolklore;

import java.util.Scanner;

public class BattleEncounter {

    private final Player player;
    private final Monster monster;
    private final Travel travel;
    private final Scanner sc = new Scanner(System.in);

    public BattleEncounter(Player player, Monster monster, Travel travel) {
        this.player = player;
        this.monster = monster;
        this.travel = travel;
    }

    public boolean startBattle() {
        // player's turn if player's atkSpeed is greater than or equal to monster's
        boolean playerFirst = player.getAtkSpeed() >= monster.getAtkSpeed();
        while (player.isAlive() && monster.isAlive()) {
            if (playerFirst) {
                // Player's turn
                System.out.println("What would you like to do right now?\n");

                // PRINT HERE: HEALTH BARS
                System.out.print(player.getName() + "'s HP:");
                player.getPlayerHealthBar();

                System.out.print(monster.getName() + "'s HP:");
                monster.getHealthBar();

                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\n// Attack // Sako (Check Inventory) // Parry //");
                    System.out.print("Your Action: ");
                    String input = sc.nextLine();
                    switch (input.toLowerCase()) {
                        case "attack" -> {
                            Weapon weapon = Player.getEquippedWeapon();
                            int weaponDamage = 0;
                            if (weapon != null) {
                                weaponDamage = weapon.getMin()
                                        + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                            }
                            int damage = player.attack() + weaponDamage;
                            monster.takeDamage(damage);
                            System.out.println("You attack " + monster.getName() + " for " + damage
                                    + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage + ")\n");

                            if (!monster.isAlive()) {
                                // automatic end
                            } else {
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        case "sako" -> {
                            player.openInventory(true);
                            validInput = true;
                        }
                        case "parry" -> {
                            validInput = true;
                        }
                        default -> {
                            System.out.println("Not a valid input. Please try again.\n");
                            System.out.println(player.getName() + "'s HP:");
                            player.getPlayerHealthBar();

                            System.out.println(monster.getName() + "'s HP:");
                            monster.getHealthBar();
                        }
                    }
                }
            } else {
                monsterTurn();
                if (!player.isAlive()) {
                    break;
                }

                // Player's turn
                System.out.print(player.getName() + "'s HP:");
                player.getPlayerHealthBar();

                System.out.print(monster.getName() + "'s HP:");
                monster.getHealthBar();

                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\nYour turn!");
                    System.out.println("// Attack // Sako (Check Inventory) // Parry //");
                    System.out.print("Your Action: ");
                    String input = sc.nextLine();
                    switch (input.toLowerCase()) {
                        case "attack" -> {
                            Weapon weapon = Player.getEquippedWeapon();
                            int weaponDamage = 0;
                            if (weapon != null) {
                                weaponDamage = weapon.getMin()
                                        + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                            }
                            int damage = player.attack() + weaponDamage;
                            monster.takeDamage(damage);
                            System.out.println("You attack " + monster.getName() + " for " + damage
                                    + " damage! (Weapon: " + player.getWeaponName() + " dealt " + weaponDamage + ")\n");

                            if (!monster.isAlive()) {
                                // automatic end
                            } else {
                                monsterTurn();
                            }
                            validInput = true;
                        }
                        case "sako" -> {
                            validInput = true;
                        }
                        case "parry" -> {

                            validInput = true;
                        }
                        default -> {
                            System.out.println("Not a valid input. Please try again.\n");
                            System.out.print(player.getName() + "'s HP:");
                            player.getPlayerHealthBar();

                            System.out.print(monster.getName() + "'s HP:");
                            monster.getHealthBar();
                        }
                    }
                }
            }
        }

        if (player.isAlive()) {
            System.out.println("You defeated " + monster.getName() + "!");
            player.lootBody(monster.getName());
            return true;
        } else {
            System.out.println("You were defeated by " + monster.getName() + "...");

            gameOver();
            return false;
        }
    }

    public void monsterTurn() {
        monster.getSkill(monster, player);
    }

    public static void gameOver(){
        System.out.println("\nRIP BOZO\n");
        System.out.println("You'll return to the Main Menu.\n");
        // RETURN TO MAIN MENU
        // Maybe display player in-game statistics like in minecraft?
    }
}
