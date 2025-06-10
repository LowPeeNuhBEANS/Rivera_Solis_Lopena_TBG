package filipinofolklore;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    int atkSpeed = 30;
    int atkDmg = 25;
    int health = 100;

    private final WeaponHandler weaponHandler = new WeaponHandler();
    private final Inventory inventory = new Inventory();
    private static final Random randy = new Random();
    private static final Scanner scn = new Scanner(System.in);
    private static Weapon equipped = new Weapon("Stick", 5, 20, 20);
    private HealthBar healthBar = new HealthBar(health);

    private static LinkedList<Weapon> woodsWeapon = new LinkedList<>(); // for woods weapons
    private static LinkedList<Weapon> swampWeapon = new LinkedList<>(); // for swamp weapons
    private static LinkedList<Weapon> villageWeapon = new LinkedList<>(); // for village weapons

    public Player() {

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


    public void takeDamage(int damage) {
        health -= damage;
    }

    public void openInventory() {
        inventory.showInventory(health);
    }

    //To equip a new weapon, weaponHandler spawns a new weapon for the player
    //The current weapon depends on whether the player took the spawned weapon or not
    public void spawnWeapons(int area) {
        equipped = weaponHandler.weaponSpawn(area, equipped);
    }

    
    // Health bar representation [TESTING]
    public static String getHealthBar(int hp) {
        int totalBars = 20;
        int bars = Math.max(0, (int) Math.round(hp / 5.0));
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < bars; i++) {
            bar.append("|");
        }
        for (int i = bars; i < totalBars; i++) {
            bar.append(" ");
        }
        bar.append("]");
        return bar.toString();
    }

    public static Weapon getEquippedWeapon() {
        return equipped;
    }

    //Add item to inventory test environment
    public static void addItemToInventory(){ 
        
    }
    //Player Health Bar
    public void getPlayerHealthBar(){
        healthBar.displayHealth();
    }
}

//END OF CLASS    