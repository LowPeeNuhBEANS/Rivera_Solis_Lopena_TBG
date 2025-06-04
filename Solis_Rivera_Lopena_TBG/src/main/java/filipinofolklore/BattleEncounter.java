package filipinofolklore;

import java.util.Scanner;

import sircardozaTBG.Monster;
import sircardozaTBG.MonsterSkill;

public class BattleEncounter {
    private Player player;
    private Monster monster;
    private Scanner sc = new Scanner(System.in);

    public BattleEncounter(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public void startBattle() { 
        // player's turn if player's atkSpeed is greater than or equal to monster's atkSpeed
        boolean playerFirst = player.getAtkSpeed() >= monster.getAtkSpeed(); 
        while (player.isAlive() && monster.isAlive()){
            if (playerFirst) {
                // player's turn 
            } else { // enemy's turn
            // draft 
               if (monster.getName().equals("Baboy Rams")) {
                    MonsterSkill.baboyRamsPassive(monster, player);
                } else {
                    int damage = monster.attack();
                    player.takeDamage(damage);
                    System.out.println("Monster Name: " + monster.getName() + "\n Monster Damage: " + damage );
                }
            }
        }
    }
}