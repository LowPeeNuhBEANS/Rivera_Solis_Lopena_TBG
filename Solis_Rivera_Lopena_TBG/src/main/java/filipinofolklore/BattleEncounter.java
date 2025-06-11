package filipinofolklore;

import java.util.Scanner;

public class BattleEncounter {

    private Player player;
    private Monster monster;
    private Travel travel;
    private Scanner sc = new Scanner(System.in);

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
                System.out.println(player.getName() + "'s HP: ");
                player.getPlayerHealthBar();

                System.out.println(monster.getName() + "'s HP: ");
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

                            // System.out.println(player.getName() + "'s HP: ");
                            // player.getPlayerHealthBar();
                            // System.out.println(monster.getName() + "'s HP: ");
                            // monster.getHealthBar();

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
                            System.out.println(player.getName() + "'s HP: ");
                            player.getPlayerHealthBar();

                            System.out.println(monster.getName() + "'s HP: ");
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
                System.out.println(player.getName() + "'s HP: ");
                player.getPlayerHealthBar();

                System.out.println(monster.getName() + "'s HP: ");
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

                            // System.out.println(player.getName() + "'s HP: ");
                            // player.getPlayerHealthBar();
                            // System.out.println(monster.getName() + "'s HP: ");
                            // monster.getHealthBar();

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
                            System.out.println(player.getName() + "'s HP: ");
                            player.getPlayerHealthBar();

                            System.out.println(monster.getName() + "'s HP: ");
                            monster.getHealthBar();
                        }
                    }
                }
            }
        }

        if (player.isAlive()) {
            System.out.println("You defeated " + monster.getName() + "!");
            return true;
        } else {
            System.out.println("You were defeated by " + monster.getName() + "...");

            gameOver();
            return false;
        }
    }

    public void monsterTurn() {
        // I made it this way instead of having many else if statements.
        // This gets the AreaCounter and will match the current monster for the turn.
        // Normal attack method is in MonsterSkill.java
        // Woods
        if (travel.getAreaCounter() == 1) {
            if (monster.getName().equals("Sigbin")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Kapre")) {
                MonsterSkill.kapreSkill(monster, player);
            }
        }

        // Swamp
        if (travel.getAreaCounter() == 2) {
            if (monster.getName().equals("Berberoka")) {
                MonsterSkill.berberokaSkill(monster, player);
            }
            if (monster.getName().equals("Siyokoy")) {
                MonsterSkill.siyokoySkill(monster, player);
            }
            if (monster.getName().equals("Santelmo")) {
                MonsterSkill.santelmoSkill(monster, player);
            }
        }

        // Village
        if (travel.getAreaCounter() == 3) {
            if (monster.getName().equals("Manananggal")) {
                MonsterSkill.manananggalSkill(monster, player);
            }
            if (monster.getName().equals("Tikbalang")) {
                MonsterSkill.tikbalangSkill(monster, player);
            }
            if (monster.getName().equals("Tiyanak")) {
                MonsterSkill.tiyanakSkill(monster, player);
            }
            if (monster.getName().equals("Bruha")) {
                MonsterSkill.bruhaSkill(monster, player);
            }
        }

        // Mountain
        if (travel.getAreaCounter() == 4) {
            if (monster.getName().equals("Sigbin")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
        }

        // Ocean
        if (travel.getAreaCounter() == 5) {
            if (monster.getName().equals("Sigbin")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
        }
    }

    public static void gameOver(){
        System.out.println("\nRIP BOZO\n");
        System.out.println("You'll return to the Main Menu.\n");
        // RETURN TO MAIN MENU
        // System.exit(0);
        // Maybe display player in-game statistics like in minecraft?
    }
}
