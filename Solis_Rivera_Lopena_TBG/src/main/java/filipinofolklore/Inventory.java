package folkloregame;

import java.util.*;

public class Inventory {//Attempt 1
    int playerHp = 100;
    private static final HashMap<String, Stack> items = new HashMap<>();

    public void initial() {

        //default
        Stack<Integer> suman = new Stack<>();
        suman.push(20);

        items.put("Suman", suman);

        Stack<Integer> maruya = new Stack<>();
        maruya.push(10);

        items.put("Maruya", maruya);

        Stack<Integer> bingka = new Stack<>();
        bingka.push(15);


        items.put("Bingka", bingka);

        Stack<Integer> panyawan = new Stack<>();
        panyawan.push(45);

        items.put("Panyawan", panyawan);

        Stack<Integer> gabon = new Stack<>();
        gabon.push(50);

        items.put("Gabon", gabon);
    }

    public void printInventory() {
        System.out.println("Equipped item: Balisong");
        System.out.println("Items Inside Sako: ");
        for (String item : items.keySet()) {
            int i = 1;
            System.out.println(i + ". " + item);
            i++;
        }

    }

    public void use(int choice) {
        switch (choice) {
            case 1 -> {
                if (!items.get("Suman").isEmpty()) {
                    consume(items.get("Suman"));
                    System.out.println("Suman consumed!");
                    this.playerHp += 20;
                } else {
                    System.out.println("No more Suman left!");
                }
            }
            case 2 -> {
                if (!items.get("Maruya").isEmpty()) {
                    consume(items.get("Maruya"));
                    System.out.println("Maruya consumed!");
                    this.playerHp += 10;
                } else {
                    System.out.println("No more Maruya left!");
                }
            }
            case 3 -> {
                if (!items.get("Bingka").isEmpty()) {
                    consume(items.get("Bingka"));
                    System.out.println("Bingka consumed!");
                    this.playerHp += 15;
                } else {
                    System.out.println("No more Bingka left!");
                }
            }
            case 4 -> {
                if (!items.get("Panyawan").isEmpty()) {
                    consume(items.get("Panyawan"));
                    System.out.println("Panyawan consumed!");
                    this.playerHp += 45;
                } else {
                    System.out.println("No more Panyawan left!");
                }
            }
            case 5 -> {
                if (!items.get("Gabon").isEmpty()) {
                    consume(items.get("Gabon"));
                    System.out.println("Gabon consumed!");
                    this.playerHp += 50;
                } 
                else {
                    System.out.println("No more Gabon left!");
                }
            }

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

 public static void consume(Stack<Integer> itemsStack){
        if (!itemsStack.isEmpty()) {
            int playerHp = 100;
            int value = itemsStack.pop();
            playerHp += value;
            System.out.println("Player HP increased by " + value + ". Current HP: " + playerHp);
        } else {
            System.out.println("No items left to consume!");
        }
    }

    public void checkMethod(Stack items){

        //Checks if the stack item exceeds 4
        if(items.size() > 4){
            System.out.println("Your inventory is full!");
        }
    }

    public void showInventory() {
        System.out.println("Items Inside Sako: ");

        int i = 1;
        for (String item : items.keySet()) {
            System.out.println(i + ". " + item + " " + "["+ itemsLeft(items.get(item))+"]");
            i++;
        }
    }

    private static int itemsLeft(Stack items){
        return items.size();
    }
}
    
