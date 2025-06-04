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
     * Woods: 2 enemies
     * Swamp: 3 enemies
     * Village: 4 enemies
     * 
     * FOR BOSSES:
     * Mountain Boss:
     * Ocean Boss:
     */

    // WOODS
    public static final Monster woodMons1 = new Monster("Baboy Rams", 50, 1, 10, 1, 1);
    public static final Monster woodMons2 = new Monster("Tikbalang", 60, 5, 12, 2, 2);

    // SWAMP
    public static final Monster swampMons1 = new Monster("Manananggal", 45, 8, 15, 1, 3);
    public static final Monster swampMons2 = new Monster("Kapre", 80, 10, 18, 5, 1);
    public static final Monster swampMons3 = new Monster("Tiyanak", 40, 6, 14, 1, 2);

    // VILLAGE
    public static final Monster villMons1 = new Monster("Aswang", 55, 7, 13, 2, 2);
    public static final Monster villMons2 = new Monster("Aswang", 55, 7, 13, 2, 2);
    public static final Monster villMons3 = new Monster("Aswang", 55, 7, 13, 2, 2);
    public static final Monster villMons4 = new Monster("Aswang", 55, 7, 13, 2, 2);

    public static final ArrayList<Monster> woodMonsters = new ArrayList<Monster>() {
        {
            add(woodMons1);
            add(woodMons2);
        }
    };

    public static final ArrayList<Monster> swampMonsters = new ArrayList<Monster>() {
        {
            add(swampMons1);
            add(swampMons2);
            add(swampMons3);
        }
    };

    public static final ArrayList<Monster> villageMonsters = new ArrayList<Monster>() {
        {
            add(villMons1);
            add(villMons2);
            add(villMons3);
            add(villMons4);
        }
    };

    /*
     * Sample Monster Spell/skill - for each spell create a new spell/skill method
     * public void roar() {
     * System.out.println(name + " lets out a terrifying roar!");
     * }
     */

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