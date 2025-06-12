/*
The Weapon Handler class is reponsible for initializing the various preset Weapon objects.
- Each weapon has a set name and pre-established stats (damage range and speed).
- LinkedLists are Weapon Spawn Pools specific to an area.
- weaponSpawn() method spawns a random weapon from area for player to equip.

 */
package filipinofolklore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WeaponHandler {

    private static final Random randomNum = new Random();
    private static final Scanner scn = new Scanner(System.in);

    private static final LinkedList<Weapon> woodsWeapon = new LinkedList<>(); // for woods weapons
    private static final LinkedList<Weapon> swampWeapon = new LinkedList<>(); // for swamp weapons
    private static final LinkedList<Weapon> villageWeapon = new LinkedList<>(); // for village weapons

    private static final ArrayList<Monster> woodMonsters = new ArrayList<Monster>();
    private static final ArrayList<Monster> swampMonsters = new ArrayList<Monster>();
    private static final ArrayList<Monster> villageMonsters = new ArrayList<Monster>();

    public WeaponHandler() {
        initializeWeapons();
        initializeMonsters();
    }

    private static void initializeWeapons() {
        // Woods weapon
        Weapon Balisong = new Weapon("Balisong", 15, 30, 10);
        Weapon BoloKnife = new Weapon("Arnis", 10, 20, 15);
        woodsWeapon.add(BoloKnife);
        woodsWeapon.add(Balisong);

        // Swamp weapon
        Weapon SibatSpear = new Weapon("Sibat Spear", 15, 30, 5);
        Weapon Bolo = new Weapon("Bolo Knife", 20, 50, -5);
        swampWeapon.add(SibatSpear);
        swampWeapon.add(Bolo);

        // Village weapon
        Weapon SinawitAxe = new Weapon("Sinawit Axe", 30, 70, -10);
        Weapon Kris = new Weapon("Kris", 40, 50, 10);
        Weapon Kampilan = new Weapon("Kampilan", 60, 80, -20);
        villageWeapon.add(SinawitAxe);
        villageWeapon.add(Kris);
        villageWeapon.add(Kampilan);
    }

    public Weapon weaponSpawn(int area, Weapon current) {
        Weapon chosenWeapon = current;
        if (randomNum.nextDouble() <= 0.25) { // 1 in 4 chances to encounter weapon
            switch (area) {
                case 1 -> {
                    if (!woodsWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(woodsWeapon, current);
                    }
                }
                case 2 -> {
                    if (!swampWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(swampWeapon, current);
                    }
                }
                case 3 -> {
                    if (!villageWeapon.isEmpty()) {
                        chosenWeapon = equipWeapon(villageWeapon, current);
                    }
                }
            }
        }
        return chosenWeapon;
    }

    // Equip weapon logic
    private static Weapon equipWeapon(LinkedList<Weapon> weapons, Weapon current) {
        Weapon chosenWeapon = current;

        Weapon found = (Weapon) weapons.get(randomNum.nextInt(weapons.size()));

        System.out.println();
        System.out.println("You found a " + found.getName() + "!");
        System.out.println("The " + found.getName() + " deals " + found.getMin() + "-" + found.getMax() + " damage. " + found.getSpeedMsg());
        System.out.println("Equipped item: " + current.getName());
        System.out.println("The " + current.getName() + " deals " + current.getMin() + "-" + current.getMax() + " damage. " + current.getSpeedMsg());

        boolean equipping = true;
        while (equipping) {
            System.out.print("\nSwitch your weapon? (yes/no): ");
            String choice = scn.nextLine();

            switch (choice.toLowerCase()) {
                case "yes" -> {
                    System.out.println("You equipped the " + found.getName() + " and left your " + current.getName() + ".");
                    chosenWeapon = found;
                    equipping = false;
                }
                case "no" -> {
                    System.out.println("You left the " + found.getName() + ".");
                    equipping = false;
                }
                default ->
                    System.out.println("Invalid input. Please try again.");
            }
        }
        weapons.remove(found);
        return chosenWeapon;
    }

    private static void initializeMonsters() {
        // WOODS
        Monster sigbin = new Monster("Sigbin", 100, 10, 20, 5, 11);
        Monster kapre = new Monster("Kapre", 100, 20, 20, 5, 12);

        // SWAMP
        Monster berberoka = new Monster("Berberoka", 150, 20, 30, 10, 21);
        Monster siyokoy = new Monster("Siyokoy", 150, 20, 30, 10, 22);
        Monster santelmo = new Monster("Santelmo", 150, 20, 30, 15, 23);

        // VILLAGE
        Monster manananggal = new Monster("Manananggal", 200, 30, 40, 15, 31);
        Monster tikbalang = new Monster("Tikbalang", 200, 30, 40, 15, 32);
        Monster tiyanak = new Monster("Tiyanak", 200, 30, 40, 20, 33);
        Monster bruha = new Monster("Bruha", 200, 30, 40, 20, 34);

        woodMonsters.add(sigbin);
        woodMonsters.add(kapre);

        swampMonsters.add(berberoka);
        swampMonsters.add(siyokoy);
        swampMonsters.add(santelmo);

        villageMonsters.add(manananggal);
        villageMonsters.add(tikbalang);
        villageMonsters.add(tiyanak);
        villageMonsters.add(bruha);
    }

    public ArrayList<Monster> getSpawnPool(int area) {
        return switch (area) {
            case 1 ->woodMonsters;
            case 2 ->swampMonsters;
            case 3 ->villageMonsters;
            default -> null;
        };
    }
}
