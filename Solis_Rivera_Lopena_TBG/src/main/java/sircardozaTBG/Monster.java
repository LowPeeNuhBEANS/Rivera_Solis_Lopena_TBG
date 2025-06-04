package sircardozaTBG;

public class Monster extends Character {
    
    public Monster(String name, int health, int minATK, int maxATK, double armor, double speed) {
        super(name, health, minATK, maxATK, armor, speed);
        this.name = super.name;
    }

    // MONSTERS (DRAFT)
    Monster baboyRams = new Monster("Baboy Rams", 50, 1, 10, 1, 1);
    
    /*Sample Monster Spell/skill - for each spell create a new spell/skill method
    public void roar() {
        System.out.println(name + " lets out a terrifying roar!");
    }
    */

    public boolean isAlive(){return health > 0;}
    public int getAtkSpeed(){return (int) super.getSpeed();}
    public int attack(){return getAttack();}
    public void takeDamage(int Damage){super.takeDamage(Damage);}  
    public String getName(){return super.getName();}

    
}