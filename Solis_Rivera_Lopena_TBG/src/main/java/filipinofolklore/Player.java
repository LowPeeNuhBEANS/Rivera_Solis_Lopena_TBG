package filipinofolklore;

import java.util.LinkedList;

public class Player {

    int atkSpeed = 30;
    int atkDmg = 25;
    int health = 100;

    private final WeaponHandler weaponHandler = new WeaponHandler();
    private final Inventory inventory = new Inventory();
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

    public static Weapon getEquippedWeapon() {
        return equipped;
    }

    public String getWeaponName() {
        return equipped.getName();
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