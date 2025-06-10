package filipinofolklore;

import java.util.Scanner;

public class BattleEncounter {
    private Player player;
    private Monster monster;
    private Travel travel;
    private Inventory inventory;
    private Scanner sc = new Scanner(System.in);

    public BattleEncounter(Player player, Monster monster, Travel travel, Inventory inventory) {
        this.player = player;
        this.monster = monster;
        this.travel = travel;
        this.inventory = inventory;
    }

    static String getHealthBar(int hp) {
        int totalBars = 20;
        int bars = Math.max(0, (int) Math.round(hp / 5.0));
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < bars; i++)
            bar.append("|");
        for (int i = bars; i < totalBars; i++)
            bar.append(" ");
        bar.append("]");
        return bar.toString();
    }

    public void startBattle() {
        // player's turn if player's atkSpeed is greater than or equal to monster's
        boolean playerFirst = player.getAtkSpeed() >= monster.getAtkSpeed();
        while (player.isAlive() && monster.isAlive()) {
            if (playerFirst) {
                // Player's turn
                System.out.println("What would you like to do right now?\n");

                // PRINT HERE: HEALTH BARS
                System.out.println("Players's HP: ");
                System.out.println(getHealthBar(player.getHp()));

                System.out.println(monster.getName() + "'s HP: ");
                System.out.println(getHealthBar(monster.getHp()));

                System.out.println("\n// Attack // Sako (Check Inventory) // Parry //");
                System.out.print("Your Action: ");
                
                // TO DO: Implement a catch for user input errors
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("attack")) {
                    // get the weapon
                    Weapon weapon = Player.getEquippedWeapon();
                    int weaponDamage = 0;
                    if (weapon != null) {
                        // this will randomize the weapon damage since weapon is initialized with int
                        // min and int max
                        weaponDamage = weapon.getMin()
                                + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                    }
                    int damage = player.attack() + weaponDamage; // player attack + weapon damage
                    monster.takeDamage(damage);
                    System.out.println("You attack " + monster.getName() + " for " + damage
                            + " damage! (Weapon: " + weapon.getName() + " dealt " + weaponDamage + ")");

                    // PRINT HERE: HEALTH BARS
                    System.out.println("Players's HP: ");
                    System.out.println(getHealthBar(player.getHp()));

                    System.out.println(monster.getName() + "'s HP: ");
                    System.out.println(getHealthBar(monster.getHp()));
                }
                if (!monster.isAlive()) {
                    break;
                }
                monsterTurn();
            } else {
                // Monster's turn
                monsterTurn();
                if (!player.isAlive()) {
                    break;
                }

                // Player's turn
                // PRINT HERE: HEALTH BARS
                System.out.println("Players's HP: ");
                System.out.println(getHealthBar(player.getHp()));

                System.out.println(monster.getName() + "'s HP: ");
                System.out.println(getHealthBar(monster.getHp()));

                System.out.println("\nYour turn!");
                System.out.println("// Attack // Sako (Check Inventory) // Parry //");
                System.out.print("Your Action: ");

                // TO DO: Implement a catch for user input errors 
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("attack")) {
                    // get ww
                    Weapon weapon = Player.getEquippedWeapon();
                    int weaponDamage = 0;
                    if (weapon != null) {
                        // this will randomize the weapon damage since weapon is initialized with int
                        // min and int max
                        weaponDamage = weapon.getMin()
                                + (int) (Math.random() * (weapon.getMax() - weapon.getMin() + 1));
                    }
                    int damage = player.attack() + weaponDamage; // player attack + weapon damage
                    monster.takeDamage(damage);
                    System.out.println("You attack " + monster.getName() + " for " + damage
                            + " damage! (Weapon: " + weapon.getName() + " dealt " + weaponDamage + ")");

                    // PRINT HERE: HEALTH BARS
                    System.out.println("Players's HP: ");
                    System.out.println(getHealthBar(player.getHp()));

                    System.out.println(monster.getName() + "'s HP: ");
                    System.out.println(getHealthBar(monster.getHp()));
                }
            }
        }

        if (player.isAlive()) {
            System.out.println("You defeated " + monster.getName() + "!");
        } else {
            System.out.println("You were defeated by " + monster.getName() + "...");
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
}
