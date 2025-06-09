package filipinofolklore;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Inventory {

    int playerHp = 100;

    private static final Scanner scn = new Scanner(System.in);
    private static final HashMap<String, Stack<Integer>> items = new HashMap<>();

    public Inventory() {
        initializeItems();
    }

    //insert all items to the HashMap when inventory is initialized
    private void initializeItems() {
        items.put("Suman", createStack(20));
        items.put("Maruya", createStack(10));
        items.put("Bingka", createStack(15));
        items.put("Panyawan", createStack(45));
        items.put("Gabon", createStack(50));
    }

    //Creates stacks automatically when first initializing
    private static Stack<Integer> createStack(int hp) {
        Stack<Integer> food = new Stack<>();
        food.push(hp);
        return food;
    }

    //Code for showing and interacting with inventory
    public void showInventory() {
        boolean inSako = true;
        while (inSako) {

            System.out.println("\nItems Inside Sako: ");
            int i = 1;
            for (String item : items.keySet()) {
                System.out.println(i + ". " + item + " " + "(" + items.get(item).size() + ")");
                i++;
            }

            System.out.println("\nWhat will you do with your inventory?");
            System.out.println("// Check // Eat // Back");
            String choice = scn.next();

            switch (choice.toLowerCase()) {
                case "check" -> {
                    System.out.println("\nWhich item? (Choose a number)");
                    int item = scn.nextInt();
                    scn.nextLine();
                    check(readChoice(item));
                }
                case "eat" -> {
                    System.out.println("\nWhich item? (Choose a number)");
                    int item = scn.nextInt();
                    scn.nextLine();
                    use(readChoice(item));
                }
                case "back" ->
                    inSako = false;
                default -> {
                    System.out.println("Invalid input, try again.");
                }
            }
        }
    }

    //Function to assign user's choice to HashMap key
    private String readChoice(int choice) {
        String itemName = switch (choice) {
            case 1 ->
                "Suman";
            case 2 ->
                "Maruya";
            case 3 ->
                "Bingka";
            case 4 ->
                "Panyawan";
            case 5 ->
                "Gabon";
            default ->
                null;
        };
        return itemName;
    }

    //Check how much health the item will restore
    private void check(String itemName) {
        if (itemName == null) {
            System.out.println("Invalid item choice.");
            return;
        }

        Stack<Integer> food = items.get(itemName);
        if (food != null && !food.isEmpty()) {
            System.out.println(itemName + " will restore " + food.peek() + " health.");
        } else {
            System.out.println("No more " + itemName + " left!");
        }
    }

    //Use item to regain health
    //NOTE: TO BE PAIRED WITH ACTUAL PLAYER'S HEALTH
    private void use(String itemName) {
        if (itemName == null) {
            System.out.println("Invalid item choice.");
            return;
        }

        Stack<Integer> food = items.get(itemName);
        if (food != null && !food.isEmpty()) {
            System.out.println(itemName + " consumed! You restored " + food.peek() + " health.");
            playerHp += food.pop();
        } else {
            System.out.println("No more " + itemName + " left!");
        }
    }

    //loot item Randomize 1 - 5, if 1 = get suman etc.(Switch case)
    private void randomItem() {
        Random r = new Random();
        int random = r.nextInt(5) + 1;
        addItem(readChoice(random), random);
    }
    //Monster items dropped upon death
    public void woodMonstersItems() {
        addItem("Suman", 20);
        System.out.println("Monster dropped Suman! Added to your inventory.");
    }
    public void swampMonstersItems() {
        addItem("Maruya", 10);
        System.out.println("Monster dropped Maruya! Added to your inventory.");
    }
    public void villageMonstersItems() {
        addItem("Bingka", 15);
        System.out.println("Monster dropped Bingka! Added to your inventory.");
    }

//FOR TESTING! CHECKER
    private void addItem(String itemName, int value) {
        Stack<Integer> stack = items.getOrDefault(itemName, new Stack<>());
        if (stack.size() >= 5) {
            System.out.println("Cannot add more " + itemName + ". Maximum of 5 reached!");
        } else {
            stack.push(value);
            items.put(itemName, stack);
        }
    }
}
    //adds item to player inventory

