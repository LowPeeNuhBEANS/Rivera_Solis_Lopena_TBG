package sircardozaTBG;

import filipinofolklore.Player;

public class MonsterSkill {
    // SKILL PASSIVE DRAFT: Attack is increased by 1.5
    // the "baboyRamsPassive" name can be changed to atkIncreaseBy1.5
    public static void baboyRamsPassive(Monster monster, Player player){
        int baseDamage = monster.attack();
        int finalDamage = (int) (baseDamage * 1.5);
        player.takeDamage(finalDamage);
        System.out.println("Final Damage: " + finalDamage);
    }
}
