package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a consecutive slot group.
 * A consecutive slot group is a group of slots from a board that are next to each other.
 * A consecutive slot group can be created with the slot numbers of a starting slot and direction.
 * The direction can be vertical, horizontal, or diagonal.
 * The consecutive slot group will be created by repeatedly adding 1 to the starting slot numbers.
 * This class includes a method to determine if the same participant has added tokens in every slot in this group.
 * If the same participant has added tokens in every slot in this group, that participant wins.
 */
public class ConsecutiveSlotGroup {
    private int numberOfSlots;

    private List<Token> tokens=new ArrayList<>();
}
