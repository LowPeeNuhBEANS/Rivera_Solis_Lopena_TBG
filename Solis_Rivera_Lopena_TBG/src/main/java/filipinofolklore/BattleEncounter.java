package filipinofolklore;

import java.util.Scanner;

public class BattleEncounter {
    private Player player;
    private Monster monster;
    private Scanner sc = new Scanner(System.in);

    public BattleEncounter(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public void startBattle() {
        // player's turn if player's atkSpeed is greater than or equal to monster's
        // atkSpeed
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
                        // this will randomize the weapon damage since weapon is initialized with int min and int max
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
                        // this will randomize the weapon damage since weapon is initialized with int min and int max
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

    
    // this is only temporary, special ability not yet coded
    // basic attack of the monster 
    public void monsterTurn() {
        int damage = monster.attack();
            player.takeDamage(damage);
            System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

}