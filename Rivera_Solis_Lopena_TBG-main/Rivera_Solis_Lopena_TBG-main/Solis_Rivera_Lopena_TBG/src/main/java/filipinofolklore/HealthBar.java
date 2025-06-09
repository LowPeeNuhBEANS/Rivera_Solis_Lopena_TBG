package filipinofolklore;

import java.util.Stack;

public class HealthBar {
    private Stack<String> healthStack;
    private int maxHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        healthStack = new Stack<>();
        initializeHealth();
    }

    private void initializeHealth() {
        for (int i = 0; i < maxHealth; i++) {
            healthStack.push("|");
        }
    }

    public void takeDamage(int damage) {
        for (int i = 0; i < damage && !healthStack.isEmpty(); i++) {
            healthStack.pop();
        }
        displayHealth();
    }

    public void heal(int amount) {
        for (int i = 0; i < amount && healthStack.size() < maxHealth; i++) {
            healthStack.push("|");
        }
        displayHealth();
    }

    public void displayHealth() {
        System.out.print("Health: [");
        for (String segment : healthStack) {
            System.out.print(segment);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        HealthBar healthBar = new HealthBar(15); // Set max health to 15
        healthBar.displayHealth();

        healthBar.takeDamage(5);
        healthBar.heal(3);
        healthBar.takeDamage(7);
    }
}
