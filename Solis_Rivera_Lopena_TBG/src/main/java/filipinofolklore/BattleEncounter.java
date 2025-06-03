package filipinofolklore;

import java.util.Scanner;

import sircardozaTBG.Monster;

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
            } else {
               // monster's turn
            }
        }
    }
}