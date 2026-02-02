package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    //Consecutive slot groups can be created from multiple tokens in this board.
    //The tokens can be chosen from a vertical, horizontal, or diagonal line.

    //I will repeatedly create consecutive slot groups to check the board to determine whether any participant has won.
    //I'll start with both slot numbers as 1.
    //In a graph, this will be the origin.

    //When choosing the numbers in a consecutive slot group, I only need to increase the numbers.
    //I don't need to check slots that are less than the starting slot, since that slot would have been checked before.

    //Creating a consecutive slot group for a vertical line is easy.
    //I simply need to retrieve a few tokens with the same horizontal number.

    //Creating a consecutive slot group for a horizontal line is slightly more difficult, but still easy.
    //I need to retrieve a number of tokens from 3 different lists.
    //Every token will be retrieved with the same vertical number.

    //Creating a consecutive slot group for a diagonal line
    private Map<SlotNumbers, Token> map = new HashMap<>();

    /**
     * This method adds the token to the board.
     * Only the horizontal number can be chosen.
     * The board will choose the vertical number, depending on the number of tokens that have been added with the same horizontal number.
     */
    public void setToken(Token token, int horizontalNumber) {
        Objects.requireNonNull(token);

        //I know the horizontal number that I need to add this token to.
        //I need to determine the vertical number.

        //The vertical number depends on the existing tokens that have been added to the board.
        //This class is able to retrieve the tokens.
        //I need to determine the maximum number out of all the existing tokens.
        //The problem is that I need to add it to the slot with the next available vertical number.
        //I've written this class with Map<SlotNumbers,Token>, so this might not be the best way.

        //First, I'll retrieve all tokens with the same horizontal number.
        List<Token> verticalTokens = new ArrayList<>();
        for (Token boardToken : this.map.values()) {
            if (boardToken.get)

            //I was thinking about creating a list with all of the tokens with the same horizontal number, and determining the maximum vertical number from that list.
            //This won't be possible because a token isn't aware of the slot numbers that it has been added to.
            //Instead, the board is responsible for knowing the slot numbers.
            //I need to think about this.
        }
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
