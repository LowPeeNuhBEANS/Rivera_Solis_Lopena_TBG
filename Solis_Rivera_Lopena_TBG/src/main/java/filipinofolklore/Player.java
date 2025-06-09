package filipinofolklore;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    int atkSpeed = 30;
    int atkDmg = 25;
    int health = 100;
    private static final Random randy = new Random();
    private static final Scanner scn = new Scanner(System.in);
    private static Weapon equipped;

    private static LinkedList<Weapon> woodsWeapon = new LinkedList<>(); // for woods weapons
    private static LinkedList<Weapon> swampWeapon = new LinkedList<>(); // for swamp weapons
    private static LinkedList<Weapon> villageWeapon = new LinkedList<>(); // for village weapons

    public Player() {
        initWeapons();
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getAtkSpeed() {
        return atkSpeed;
    }

    public int attack() {
        return atkDmg;
    }

    public String getName() {
        return "Player";
    }

    public int getHp() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    private void initWeapons() {
        // Default weapon
        equipped = new Weapon("Stick", 5, 20, 20);

        // Woods weapon
        Weapon Balisong = new Weapon("Balisong", 15, 30, 10);
        Weapon BoloKnife = new Weapon("Arnis", 10, 20, 15);
        woodsWeapon.add(BoloKnife);
        woodsWeapon.add(Balisong);

        // Swamp weapon
        Weapon SibatSpear = new Weapon("Sibat Spear", 15, 30, 5);
        Weapon Bolo = new Weapon("Bolo Knife", 20, 50, -5);
        swampWeapon.add(SibatSpear);
        swampWeapon.add(Bolo);

        // Village weapon
        Weapon SinawitAxe = new Weapon("Sinawit Axe", 30, 70, -10);
        Weapon Kris = new Weapon("Kris", 40, 50, 10);
        Weapon Kampilan = new Weapon("Kampilan", 60, 80, -20);
        villageWeapon.add(SinawitAxe);
        villageWeapon.add(Kris);
        villageWeapon.add(Kampilan);

    }

    public void weaponSpawn(int area) {
        if (randy.nextDouble() <= 0.25) { // 1 in 4 chances to encounter weapon
            switch (area) {
                case 1 -> {
                    if (!woodsWeapon.isEmpty()) {
                        equipWeapon(woodsWeapon);
                    }
                }
                case 2 -> {
                    if (!swampWeapon.isEmpty()) {
                        equipWeapon(swampWeapon);
                    }
                }
                case 3 -> {
                    if (!villageWeapon.isEmpty()) {
                        equipWeapon(villageWeapon);
                    }
                }
            }
        }
    }

    // Equip weapon logic
    private static void equipWeapon(LinkedList<Weapon> weapons) {
        Weapon found = (Weapon) weapons.get(randy.nextInt(weapons.size()));

        System.out.println("You found a " + found.getName() + "!");
        System.out.println("The " + found.getName() + " deals " + found.getMin() + "-" + found.getMax() + " damage. "
                + found.getSpeedMsg());
        System.out.println("Equipped item: " + equipped.getName());
        System.out.println("The " + equipped.getName() + " deals " + equipped.getMin() + "-" + equipped.getMax()
                + " damage. " + equipped.getSpeedMsg());

        boolean equipping = true;
        while (equipping) {
            System.out.print("\nSwitch your weapon? (yes/no): ");
            String choice = scn.nextLine();

            switch (choice.toLowerCase()) {
                case "yes" -> {
                    System.out.println(
                            "You equipped the " + found.getName() + " and left your " + equipped.getName() + ".");
                    equipped = found;
                    equipping = false;
                }
                case "no" -> {
                    System.out.println("You left the " + found.getName() + ".");
                    equipping = false;
                }
                default ->
                    System.out.println("Invalid input. Please try again.");
            }
        }
        weapons.remove(found);
        System.out.println("");
    }

    public static Weapon getEquippedWeapon() {
        return equipped;
    }
}