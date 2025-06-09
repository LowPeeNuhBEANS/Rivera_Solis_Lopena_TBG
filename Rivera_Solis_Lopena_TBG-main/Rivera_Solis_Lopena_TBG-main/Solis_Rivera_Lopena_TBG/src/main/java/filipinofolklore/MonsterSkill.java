package filipinofolklore;

import java.util.Random;

public class MonsterSkill {
    /*
     * This is the damage implementation:
     * Monsters from woods will deal normal damage and
     * Monsters from swamp and village will have different amplified damage
     */

    // Swamps
    private static int mutatedCarabaoAttackCounter = 0;
    private static int berberokaAttackCounter = 0;

    // Monsters from Woods will deal normal damage
    public static void sigbinSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " ran towards you and attacks you for " + damage + " damage!");
    }

    public void kapreSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " flew and attacks you for " + damage + " damage!");
    }

    // Monsters from swamp will have different amplified damage
    public void berberokaSkill(Monster monster, Player player) {
        // Berberoka will deal 1.5x the damage on the 3rd succeeding attack and retains it.
        berberokaAttackCounter++;
        int damage;
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

    public void siyokoySkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    public void mutatedCarabaoSkill(Monster monster, Player player) {
        // Mutated Carabao will deal 1.5x the damage on the once it reaches its 3rd succeeding attack.
        if (mutatedCarabaoAttackCounter < 3) {
            mutatedCarabaoAttackCounter++;
        }
        int damage;
        if (mutatedCarabaoAttackCounter == 3) {
            damage = (int) (monster.attack() * 2);
            System.out.println(monster.getName() + " unleashes a CHARGED attack!");
            System.out.println("From now on, " + monster.getName() + " will deal 1.5x damage!");
        } else {
            damage = monster.attack();
        }
        player.takeDamage(damage);
        System.out.println(monster.getName() + " charges. It attacks you for " + damage + " damage!");
    }

    // Monsters from village will have different amplified damage
    public void manananggalSkill(Monster monster, Player player) {
        // Manananggal will heal itself for half the damage dealt
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);

        System.out.println(monster.getName() + "drains life! Damage Dealt: " + finalDamage + " damage.");
        monster.health += finalDamage / 2;
        System.out.println(monster.getName() + " heals for " + (finalDamage / 2) + " HP.");
    }

    public void sarangaySkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);
    }

    public void tiyanakSkill(Monster monster, Player player) {
        /*
         * Chance based skill. There is a 50% chance that the Tiyanak will deal
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

    public void diwataSkill(Monster monster, Player player) {
        int damage = monster.attack();
        player.takeDamage(damage);
        System.out.println(monster.getName() + " attacks you for " + damage + " damage!");
    }

}