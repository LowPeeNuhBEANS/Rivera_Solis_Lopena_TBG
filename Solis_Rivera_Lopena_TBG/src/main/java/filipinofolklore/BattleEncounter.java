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
}