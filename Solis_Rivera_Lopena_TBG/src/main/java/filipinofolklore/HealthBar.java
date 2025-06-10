package filipinofolklore;

import java.util.Stack;

public class HealthBar {
    private Stack<String> healthStack;
    private int divHealth;
    private static Stack<String> emptyHealth = new Stack();
    
    public HealthBar(int maxHealth) {
        this.divHealth = maxHealth/10;
        healthStack = new Stack<>();
        initializeHealth();
    }

    private void initializeHealth() {
        for (int i = 0; i < divHealth; i++) {
            healthStack.push("█");
        }
    }

    public void takeDamage(int damage) {
        for (int i = 0; i < damage && !healthStack.isEmpty(); i++) {
            healthStack.pop();
            emptyHealth.push(" ");
        }
        displayHealth();
    }

    public void heal(int amount) {
        for (int i = 0; i < amount && healthStack.size() < divHealth; i++) {
            healthStack.push("█");
            emptyHealth.pop();
        }
        displayHealth();
    }

    public void displayHealth() {
        System.out.print(" [");
        for (String segment : healthStack) {
            System.out.print(segment);
        }
        for (String loss : emptyHealth) {
            System.out.print(" ");
        }
        System.out.println("]");
    }
}

