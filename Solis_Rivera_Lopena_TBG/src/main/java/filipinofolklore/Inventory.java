package filipinofolklore;

import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

public class Inventory {

    int playerHp = 100;

    private static final Scanner scn = new Scanner(System.in); //Scanner for input
    private static final HashMap<String, Stack<Integer>> items = new HashMap<>(); //Hashmap for Item Name and Health Points

    public Inventory() {
        initializeItems();
    }

    //Inserts all items to the HashMap when inventory is initialized
    private void initializeItems() {
        items.put("suman", createStack(20));
        items.put("maruya", createStack(10));
        items.put("bingka", createStack(15));
        items.put("panyawan", createStack(45));
        items.put("abon", createStack(50));
    }

    //Creates stacks automatically, pushes the passed integer
    private static Stack<Integer> createStack(int hp) {
        Stack<Integer> food = new Stack<>();
        food.push(hp);
        return food;
    }

    //Shows prompts to interact with inventory
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
                    System.out.println("\nWhich item?");
                    String item = scn.next();
                    check(readChoice(item));
                }
                case "eat" -> {
                    System.out.println("\nWhich item?");
                    String item = scn.next();
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

    //Checks if user's choice matches with a key
    private String readChoice(String choice) {
        String itemName = null;

        if (choice.equalsIgnoreCase("suman") ||
            choice.equalsIgnoreCase("maruya") ||
            choice.equalsIgnoreCase("bingka") ||
            choice.equalsIgnoreCase("panyawan") ||
            choice.equalsIgnoreCase("gabon") ||) {
                itemName = choice.toLowerCase();
            }
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
