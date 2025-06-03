package filipinofolklore;

import java.lang.Math;

public class Weapon {

    int minAtk;
    int maxAtk;
    int atkSpeed;
    String name;

    public Weapon(String name, int min, int max, int speed) {
        this.name = name;
        minAtk = min;
        maxAtk = max;
        atkSpeed = speed;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return minAtk;
    }

    public int getMax() {
        return maxAtk;
    }

    public int getSpeed() {
        return atkSpeed;
    }

    public String getSpeedMsg() {
        if (atkSpeed > 0) {
            return "It increases your speed by " + atkSpeed + " points.";
        }
        return "It decreases your speed by " + Math.abs(atkSpeed) + " points.";
    }
}
