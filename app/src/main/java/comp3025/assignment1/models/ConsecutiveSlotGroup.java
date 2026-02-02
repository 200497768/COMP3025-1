package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a group of slots from a board that are next to each other.
 * This class includes a method to determine if the same participant has added tokens in every slot in this group.
 * If the same participant has added tokens in every slot in this group, that participant wins.
 */
public class ConsecutiveSlotGroup {
    private int numberOfSlots;
    private List<Token> tokens=new ArrayList<>();
}
