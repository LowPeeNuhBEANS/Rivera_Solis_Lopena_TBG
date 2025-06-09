package filipinofolklore;

public class Hero extends Character {

    public Hero(String name, int health, int minATK, int maxATK, double speed) {
        super(name, health, minATK, maxATK, speed); // Passing double armor to Character class
    }

    // Hero sample skill/spell
    public void specialMove() {
        System.out.println(name + " uses a powerful strike!");
    }

    public void stun() {
    }

    public void reduceDamage() {
    }
}