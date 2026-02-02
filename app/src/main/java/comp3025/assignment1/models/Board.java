package comp3025.assignment1.models;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a board.
 * A board includes multiple slots.
 * When the board is created, the number of vertical and horizontal slots must be entered.
 * After the board has been created, slots can be accessed using numbers.
 */
public class Board {
private Map<SlotNumbers,Token>map=new HashMap<>();

    /**
     * This method retrieves the token with the provided slot numbers.
     * If no token has been added with the provided slot numbers, this method returns null.
     * @param slotNumbers
     * @return
     */
    public Token getToken(SlotNumbers slotNumbers){
Token retrievedToken=this.map.get(slotNumbers);
//The get method returns null if the slot numbers hasn't been added.

        return retrievedToken;
}
}
