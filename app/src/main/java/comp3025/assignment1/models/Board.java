package comp3025.assignment1.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class is a board.
 * A board includes multiple slots.
 * When the board is created, the number of vertical and horizontal slots must be entered.
 * After the board has been created, slots can be accessed using numbers.
 */
public class Board {

    //I need to choose how I want to write this class.

    //I need to be able to add tokens to a horizontal number.
    //When this happens, I need to be able to retrieve all of the existing tokens with the same horizontal number.
    //Tokens are added in order, so order is important.
    //This suggests that List<Token> can be used for a single horizontal number.

    //I will also need to create consecutive slot groups.
    //Consecutive slot groups allow me to check whether tokens from the same participant have been added to all slots in that group.
    //
    private Map<SlotNumbers, Token> map = new HashMap<>();

    /**
     * This method adds the token to the board.
     * Only the horizontal number can be chosen.
     * The board will choose the vertical number, depending on the number of tokens that have been added with the same horizontal number.
     */
    public void setToken(Token token, int horizontalNumber) {
        Objects.requireNonNull(token);

        //This has been a mistake.
        //I need to change Map<SlotNumbers,Token>.
        //I won't be able to retrieve how many existing tokens with the same horizontal number.
    }

    /**
     * This method retrieves the token with the provided slot numbers.
     * If no token has been added with the provided slot numbers, this method returns null.
     *
     * @param slotNumbers
     * @return
     */
    public Token getToken(SlotNumbers slotNumbers) {
        Objects.requireNonNull(slotNumbers);

        Token retrievedToken = this.map.get(slotNumbers);
//The get method returns null if the slot numbers hasn't been added.

        return retrievedToken;
    }
}
