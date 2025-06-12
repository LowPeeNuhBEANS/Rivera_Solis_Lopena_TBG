package filipinofolklore;

import java.util.Stack;

public class HealthBar {

    private final Stack<String> healthStack;
    private int lastHealth;
    private final static Stack<String> emptyHealth = new Stack<>();

    public HealthBar(int maxHealth) {
        lastHealth = maxHealth;
        healthStack = new Stack<>();
        initializeHealth();
    }

    private void initializeHealth() {
        for (int i = 0; i < lastHealth/10; i++) {
            healthStack.push("█");
        }
    }

    public void displayHealth(int health) {

        //If health is lower than before
        if (health < lastHealth) {
            int lifeLoss = (lastHealth / 10) - (health / 10);
            for (int i = 0; i < lifeLoss && !healthStack.isEmpty(); i++) {
                healthStack.pop();
                emptyHealth.push(" ");
            }
            lastHealth = health;
        }

        if (health > lastHealth) {
            int lifeGain = (health / 10) - (lastHealth / 10);
            for (int i = 0; i < lifeGain; i++) {
                healthStack.push("█");
                emptyHealth.pop();
            }
            lastHealth = health;
        }

        System.out.print(" |");
        for (String segment : healthStack) {
            System.out.print(segment);
        }
        for (String loss : emptyHealth) {
            System.out.print(loss);
        }
        System.out.println("|");
    }
}
