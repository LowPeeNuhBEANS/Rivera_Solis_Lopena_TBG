package filipinofolklore;

public class MonsterSkill {

    // SKILL PASSIVE DRAFT: Attack is increased by 1.5
    // the "baboyRamsPassive" name can be changed to atkIncreaseBy1.5 depending if this method will be used again
    public static void baboyRamsPassive(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = (int) (baseDamage * 1.5);
        player.takeDamage(finalDamage);
        System.out.println("Final Damage: " + finalDamage);
    }

    public void tikbalangSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = (int) (baseDamage * 1.5);
        player.takeDamage(finalDamage);
    }

    public void manananggalSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;
        player.takeDamage(finalDamage);

        System.out.println(monster.getName() + "drains life! \n Damage Dealt: " + finalDamage + " damage.");
        // Heal itself for half the damage dealt
        monster.health += finalDamage / 2;
        System.out.println(monster.getName() + " heals for " + (finalDamage / 2) + " HP.");
    }

    public void kapreSkill(Monster monster, Player player) {

        // Extra damage only 
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;

        player.takeDamage(finalDamage);
    }

    public void tiyanakSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;

        player.takeDamage(finalDamage);
    }

    public void aswangSkill(Monster monster, Player player) {
        int baseDamage = monster.attack();
        int finalDamage = baseDamage;

        player.takeDamage(finalDamage);
    }
}