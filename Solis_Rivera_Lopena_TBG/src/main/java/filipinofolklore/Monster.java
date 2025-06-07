package filipinofolklore;

import java.util.ArrayList;

public class Monster extends Character {

    public Monster(String name, int health, int minATK, int maxATK, double armor, double speed) {
        super(name, health, minATK, maxATK, armor, speed);
        this.name = super.name;
    }

    /*
     * MONSTERS
     * - as area increases, the stats of the monster also increases
     * - will use ARRAYLIST to organize monsters according to their area
     * 
     * TO DO: 
     * - change monster stats
     * - add spawn chance
     * - damage implementation 
     * - overall implementation (call to main)
     * 
     * Woods: 2 enemies 1 special ability per enemy
     * Swamp: 3 enemies 1 special ability per enemy
     * Village: 4 enemies 1 special ability per enemy
     * 
     * FOR BOSSES:
     * Mountain Boss: 
     * Ocean Boss:
     */

    // WOODS
    public static final Monster sigbin = new Monster("Sigbin", 10, 1, 10, 1, 1);
    public static final Monster kapre = new Monster("Kapre", 20, 5, 12, 2, 2);

    // SWAMP
    public static final Monster berberoka = new Monster("Berberoka", 40, 8, 15, 1, 3);
    public static final Monster siyokoy = new Monster("Siyokoy", 60, 10, 18, 5, 1);
    public static final Monster mutatedCarabao = new Monster("Mutated Carabao", 80, 6, 14, 1, 2);

    // VILLAGE
    public static final Monster batibat = new Monster("Batibat", 100, 7, 13, 2, 2);
    public static final Monster sarangay = new Monster("Sarangay", 100, 7, 13, 2, 2);
    public static final Monster tiyanak = new Monster("Tiyanak", 100, 7, 13, 2, 2);
    public static final Monster diwata = new Monster("Diwata", 120, 7, 13, 2, 2);

    // Like weaponSpawn in Player.java, each arraylist will have a spwan chances  
    public static final ArrayList<Monster> woodMonsters = new ArrayList<Monster>() {
        {
            add(sigbin);
            add(kapre);
        }
    };

    public static final ArrayList<Monster> swampMonsters = new ArrayList<Monster>() {
        {
            add(berberoka);
            add(siyokoy);
            add(mutatedCarabao);
        }
    };

    public static final ArrayList<Monster> villageMonsters = new ArrayList<Monster>() {
        {
            add(batibat);
            add(sarangay);
            add(tiyanak);
            add(diwata);
        }
    };

    public boolean isAlive() {
        return health > 0;
    }

    public int getAtkSpeed() {
        return (int) super.getSpeed();
    }

    public int attack() {
        return getAttack();
    }

    public void takeDamage(int Damage) {
        super.takeDamage(Damage);
    }

    public String getName() {
        return super.getName();
    }
}