package filipinofolklore;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Player {

    int atkSpeed = 30; //Player's attack speed
    int atkDmg = 25; //Attack Damage
    int health = 100; //Player health
    private static final Random randy = new Random(); //Random variable
    private static final Scanner scn = new Scanner(System.in); //Scanner
    private static Weapon equipped; //Currently equipped weapon

    private static Vector<Weapon> woodsWeapon = new Vector<>(); //Vector for woods weapons
    private static Vector<Weapon> swampWeapon = new Vector<>(); //Vector for swamp weapons
    private static Vector<Weapon> villageWeapon = new Vector<>(); //Vector for village weapons

    //Constructor for player
    public Player() {
    }

    public boolean isAlive(){return health > 0;}
    public int getAtkSpeed() {return atkSpeed;}
    public int attack(){return atkDmg;}
    public String getName(){return "Player";}

    //Receive damage
    public void takeDamage(int damage){
        health -= damage;
    }

    //Initialize all weapons
    public void initWeapons() {
        //Default weapon
        equipped = new Weapon("Stick", 5, 20, 20);

        //Woods weapon
        Weapon Balisong = new Weapon("Balisong", 15, 30, 10);
        Weapon BoloKnife = new Weapon("Arnis", 10, 20, 15);
        woodsWeapon.add(BoloKnife);
        woodsWeapon.add(Balisong);

        //Swamp weapon
        Weapon StandIn1 = new Weapon("Sibat Spear", 15, 30, 5);
        Weapon StandIn2 = new Weapon("Sundang", 20, 50, -5);
        swampWeapon.add(StandIn1);
        swampWeapon.add(StandIn2);

        //Village weapon
        Weapon StandIn3 = new Weapon("Sinawit Axe", 30, 70,-10);
        Weapon StandIn4 = new Weapon("Kris Sword", 40, 50, 10);
        Weapon StandIn5 = new Weapon("Kampilan", 60, 80, -20);
        villageWeapon.add(StandIn3);
        villageWeapon.add(StandIn4);
        villageWeapon.add(StandIn5);

    }

    //Spawn chance of weapons
    public void weaponSpawn(int area) {
        if (randy.nextDouble() <= 0.33) { //1 in 3 chances to encounter weapon
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

    //Equip weapon logic
    private static void equipWeapon(Vector weapons) {
        Weapon found = (Weapon) weapons.elementAt(randy.nextInt(weapons.size()));

        System.out.println("You found a " + found.getName() + "!");
        System.out.println("The " + found.getName() + " deals " + found.getMin() + "-" + found.getMax() + " damage. " + found.getSpeedMsg());
        System.out.println("Equipped item: " + equipped.getName());
        System.out.println("The " + equipped.getName() + " deals " + equipped.getMin() + "-" + equipped.getMax() + " damage. " + equipped.getSpeedMsg());

        boolean equipping = true;
        while (equipping) {
            System.out.print("\nSwitch your weapon? (yes/no): ");
            String choice = scn.nextLine();

            switch (choice.toLowerCase()) {
                case "yes" -> {
                    System.out.println("You equipped the " + found.getName() + " and left your " + equipped.getName() + ".");
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
}
