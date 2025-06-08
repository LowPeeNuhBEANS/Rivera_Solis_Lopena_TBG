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

    public void startBattle() {
        // player's turn if player's atkSpeed is greater than or equal to monster's
        boolean playerFirst = player.getAtkSpeed() >= monster.getAtkSpeed();
        while (player.isAlive() && monster.isAlive()) {
            if (playerFirst) {
                // Player's turn
                System.out.println("\nYour turn! (Type 'attack' to attack)");
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
                System.out.println("\nYour turn! (Type 'attack' to attack)");
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
                MonsterSkill.sigbinSkill(monster, player);
            }
        }

        // Swamp
        if (travel.getAreaCounter() == 2) {
            if (monster.getName().equals("Berberoka")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Siyokoy")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Mutated Carabao")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
        }

        // Village
        if (travel.getAreaCounter() == 3) {
            if (monster.getName().equals("Manananggal")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Sarangay")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Tiyanak")) {
                MonsterSkill.sigbinSkill(monster, player);
            }
            if (monster.getName().equals("Diwata")) {
                MonsterSkill.sigbinSkill(monster, player);
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
