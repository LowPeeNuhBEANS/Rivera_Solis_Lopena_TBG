package filipinofolklore;

public class Travel {

    public Travel() {
    }

    //Counters to progress through tiles and areas
    protected static int areaCounter = 0;
    protected static int tileCounter = 0;

    //Arrays of Areas
    private static String[] spawn = {
        "You're walking through the woods on your way home.\nYou have forgotten to say \"Tabi-tabi lang po\" and you are now in grave danger.\nThe only way out is to move forward. "};
    private static String[] woods = {
        "The shadows of the woods loom above you, something is watching you... ",
        "The chilly air sends a chill down your spine... ",
        "Twigs and leaves crunch under your feet as you walk, something hears you... ",
        "The woods are eerily silent as you pass... "};
    private static String[] swamp = {
        "The forest thickens, its attempts at keeping you fail as you cut through the branches.\nThe swamp before you smells terrible, and forgotten horrors lurk beneath the surface.\n",
        "Your feet sink slightly with every step. The muck wants to pull you down, so you march forward. ",
        "Croaking toads and buzzing insects fall into silence as you move... something bigger is listening. ",
        "Half-submerged wooden idols carved with faces line the water’s edge. They stare into you.. ",
        "A thin mist clings to the swamp like a veil, hiding figures that vanish when you look directly at them... ",
        "You swear you saw something blink from beneath the murky water, but it’s gone before you can react... "
    };
    private static String[] village = {
        "You step into a village overrun by silence, the kind that screams louder than noise...  ",
        "The nipa huts stand crooked and rotting, something once disturbed this vilage and left, or is still hiding... ",
        "You try not to look back, but you hear footsteps slightly behind your own, only one beat behind... ",
        "A duyan sways gently nearby and you hear faint laughter. You feel no wind blowing... ",
        "The path narrows between the huts as the hairs on your neck begin to rise. You're being watched... ",
        "Each time you pass a hut, you feel as if something inside shifts. Following, watching, waiting... ",
        "Something moves inside one of the huts as you pass by. You look and see that it’s empty... ",
        "You see movement in your peripheral vision, quick and low to the ground, but it’s gone before you can turn. ",
    };

    //Method to get an area's size
    private static int getAreaSize() {
        String[] area = getArea();
        return area.length;
    }

    //Method to get Integer representing area
    public int getAreaCounter() {
        return areaCounter;
    }

    //Method to get array of area
    private static String[] getArea() {
        switch (areaCounter) {
            case 0 -> {
                return spawn;
            }
            case 1 -> {
                return woods;
            }
            case 2 -> {
                return swamp;
            }
            case 3 -> {
                return village;
            }
            default -> {
                return null;
            }
        }
    }

    //Method to get message attached to current tile in Area Array
    public static String getAreaMessage() {
        String[] area = getArea();
        return area[tileCounter];
    }

    //Method to get how number of tiles finished over total tiles in area
    public static String tileCheck() {
        if (areaCounter == 0) {
            return "";
        }
        return "(Tile " + (tileCounter + 1) + "/" + getAreaSize() + ")";
    }

    //Method to progress forward through tiles
    public static void proceed() {
        tileCounter++;
        if (tileCounter > getAreaSize() - 1) {
            areaCounter++;
            tileCounter = 0;
        }
    }
}
