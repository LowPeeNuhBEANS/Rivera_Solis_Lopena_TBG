package filipinofolklore;

import java.util.Random;

public class MonsterSkill {
    /*
     * This is the damage implementation:
     * Monsters from woods will deal normal damage and
     * Monsters from swamp and village will have different amplified damage
     */

    // Swamps
    private static int siyokoyAttackCounter = 0;
    private static int berberokaAttackCounter = 0;
    private static int minokawaAttackCounter = 0;

    // WOOD MONSTERS
    public static void sigbinSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " ran towards you and attacks you for " + damage + " damage!");
    }

    public static void kapreSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " flew and attacks you for " + damage + " damage!");
    }

    // SWAMP MONSTERS
    public static void berberokaSkill(Monster monster, Player player) {
        // Berberoka will deal 1.5x the damage on the 3rd succeeding attack and resets
        // the counter.
        int damage;
        berberokaAttackCounter++;

        if (berberokaAttackCounter == 4) {
            damage = (int) (monster.attack() * 1.5);
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            berberokaAttackCounter = 0;
        } else {
            damage = monster.attack();
        }
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

    public static void siyokoySkill(Monster monster, Player player) {
        // Once Siyokoy reaches his 3rd succeeding attack, Siyokoy will deal 2x the
        // damage from then on.
        int damage;

        if (siyokoyAttackCounter < 3) {
            siyokoyAttackCounter++;
        }

        if (siyokoyAttackCounter == 3) {
            damage = (int) (monster.attack() * 2);
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            System.out.println("From now on, " + monster.getName() + " will deal 2x damage!");
        } else {
            damage = monster.attack();
        }
        player.takeDamage(damage);
        System.out.println(monster.getName() + " charges. It attacks you for " + damage + " damage!");
    }

    public static void santelmoSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    // VILLAGE MONSTERS
    public static void manananggalSkill(Monster monster, Player player) {
        // Manananggal will heal itself for half the damage dealt
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);

        System.out.println(monster.getName() + "drains life! Damage Dealt: " + finalDamage + " damage.");
        monster.health += finalDamage / 2;
        System.out.println(monster.getName() + " heals for " + (finalDamage / 2) + " HP.");
    }

    public static void tikbalangSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    public static void tiyanakSkill(Monster monster, Player player) {
        /*
         * There is a 50% chance that the Tiyanak will deal
         * continuous attacks.
         * The first damage is its normal attack damage.
         * The second damage is 1/4 of that, and
         * The third damage is 1/6 of that.
         * Add those damages and it will be the final damage to the player.
         */
        Random rand = new Random();
        int damage1;
        int damage2;
        int damage3;
        if (rand.nextDouble() < 0.50) {
            System.out.println(monster.getName() + " unleashes continuous attacks! ");
            damage1 = monster.attack();
            damage2 = (int) (monster.attack() / 4);
            damage3 = (int) (monster.attack() / 6);
            int damage = damage1 + damage2 + damage3;
            player.takeDamage(damage);
            System.out.println("");
            System.out.println(
                    monster.getName() + " attacks you for " + damage1 + ", " + damage2 + ", and " + damage3 + "!");
            System.out.println("Continuous attacks hurt a lot! This is nothing like ever!");
        } else {
            int damage = monster.attack();
            player.takeDamage(damage);
            System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
        }

    }

    public static void bruhaSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

    // Mountain Boss
    public static void minokawaSkill(Monster monster, Player player) {
        // Once Minokawa reaches his 5th succeeding attack, Minokawa will deal 2x the
        // damage from then on.
        int damage;
        double multiplier = 1.0;

        switch (minokawaAttackCounter) {
            case 0:
                multiplier = 1.0;
                break;
            case 1:
                multiplier = 1.25;
                break;
            case 2:
                multiplier = 1.5;
                break;
            case 3:
                multiplier = 1.75;
                break;
            default:
                multiplier = 2.0;
                break;
        }

        if (multiplier == 2.0) {
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            System.out.println("From now on, " + monster.getName() + " will deal 2x damage!");
        }

        damage = (int) (monster.attack() * multiplier);
        player.takeDamage(damage);
        System.out.println(monster.getName() + " charges. It attacks you for " + damage + " damage!");
        minokawaAttackCounter++;
    }

    // Ocean Boss
    public static void bakunawaSkill(Monster monster, Player player) {
        
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }
}