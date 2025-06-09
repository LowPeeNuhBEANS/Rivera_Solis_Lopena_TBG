package filipinofolklore;

import java.util.*;

public class Inventory {
    private HashMap<String, Integer> items;
    private Stack<String> itemStack;

    public Inventory() {
        items = new HashMap<>();
        itemStack = new Stack<>();
    }

    // Add item to inventory
    public void addItem(String itemName) {
        items.put(itemName, items.getOrDefault(itemName, 0) + 1);
        itemStack.push(itemName);
    }

    // Remove one quantity of an item
    public boolean removeItem(String itemName) {
        if (items.containsKey(itemName) && items.get(itemName) > 0) {
            items.put(itemName, items.get(itemName) - 1);
            if (items.get(itemName) == 0) {
                items.remove(itemName);
            }
            return true;
        }
        return false;
    }

    // Undo last added item
    public boolean undoLastAdd() {
        if (!itemStack.isEmpty()) {
            String lastItem = itemStack.pop();
            return removeItem(lastItem);
        }
        return false;
    }

    // Get current inventory
    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    // Print inventory
    public void printInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}