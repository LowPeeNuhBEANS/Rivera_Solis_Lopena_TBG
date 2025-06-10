package filipinofolklore;

import java.util.ArrayList;

public class Monster extends Character {
    // private int health;

    public Monster(String name, int health, int minATK, int maxATK, double speed) {
        super(name, health, minATK, maxATK, speed);
        this.name = super.name;
        // this.health = health;
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
    public static final Monster sigbin = new Monster("Sigbin", 100, 5, 10, 5);
    public static final Monster kapre = new Monster("Kapre", 100, 5, 10, 5);

    // SWAMP
    public static final Monster berberoka = new Monster("Berberoka", 150, 10, 20, 10);
    public static final Monster siyokoy = new Monster("Siyokoy", 150, 10, 20, 10);
    public static final Monster santelmo = new Monster("Santelmo", 150, 15, 20, 15);

    // VILLAGE
    public static final Monster manananggal = new Monster("Manananggal", 200, 20, 30, 15);
    public static final Monster tikbalang = new Monster("Tikbalang", 200, 20, 30, 15);
    public static final Monster tiyanak = new Monster("Tiyanak", 200, 30, 35, 20);
    public static final Monster bruha = new Monster("Bruha", 200, 30, 35, 20);

    // MOUNTAIN
    public static final Monster minokawa = new Monster("Minokawa", 200, 30, 35, 30);

    // OCEAN
    public static final Monster bakunawa = new Monster("Bakunawa", 200, 30, 35, 30);

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
            add(santelmo);
        }
    };

    public static final ArrayList<Monster> villageMonsters = new ArrayList<Monster>() {
        {
            add(manananggal);
            add(tikbalang);
            add(tiyanak);
            add(bruha);
        }
    };

    public boolean isAlive() {
        return health > 0;
    }

    public int getHp() {
        return health;
    }

    public int getAtkSpeed() {
        return (int) super.getSpeed();
    }

    public int attack() {
        return getAttack();
    }

    public String getName() {
        return super.getName();
    }
}