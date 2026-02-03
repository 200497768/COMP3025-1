package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is a board.
 * A board includes multiple slots.
 * When the board is created, the number of vertical and horizontal slots must be entered.
 * After the board has been created, slots can be accessed using numbers.
 */
public class Board {

    //This field is a list with all of the tokens in this board.
    //The tokens are accessed through ordered token groups.
    private List<OrderedTokenGroup>orderedTokenGroups=new ArrayList<>();

    public Board(int numberOfOrderedTokenGroups) {
        //Some number of ordered token groups will be created and added to this board.

        //An ordered token group will be created, and added to this board.
        OrderedTokenGroup orderedTokenGroup = new OrderedTokenGroup();
        this.orderedTokenGroups.add(orderedTokenGroup);
    }

    /**
     * This method is used to retrieve a token using slot numbers.
     * This method will access the ordered token group with the provided horizontal number.
     * Next, this method will access the token with the provided vertical number.
     * This method returns the token that was retrievd.
     * If no token was added to the slot, this method returns null.
     *
     * @param slotNumbers
     * @return
     */
    public Token getToken(SlotNumbers slotNumbers) {
        Objects.requireNonNull(slotNumbers);

        int verticalNumber = slotNumbers.getVertical();
        int horizontalNumber = slotNumbers.getHorizontal();

        //This board includes a list with multiple ordered token groups.
        //The vertical number is used to retrieve an single ordered token group.
        OrderedTokenGroup orderedTokenGroup = this.orderedTokenGroups.get(horizontalNumber - 1);

        Token token = orderedTokenGroup.getToken(verticalNumber);

        return token;

    }

    /**
     * This method returns an ordered token group from this board using the horizontal number.
     *
     * @param horizontalNumber
     * @return
     */
    public OrderedTokenGroup getOrderedTokenGroup(int horizontalNumber) {
        OrderedTokenGroup orderedTokenGroup = this.orderedTokenGroups.get(horizontalNumber - 1);
        return orderedTokenGroup;
    }

    /**
     * This method creates consecutive slot groups for every slot in this board.
     * This method returns the consecutive slot groups that were created as a list.
     * Every consecutive slot groups can be used to determine if a participant has won.
     *
     * @return
     */
    public List<ConsecutiveSlotGroup> getConsecutiveSlotGroups() {
        List<ConsecutiveSlotGroup> consecutiveSlotGroups = new ArrayList<>();

        //I will repeatedly create consecutive slot groups to check the board to determine whether any participant has won.
        //I'll start with both slot numbers as 1.
        //In a graph, this will be the origin.

        //Creating a consecutive slot group for a vertical line is easy.
        //I simply need to retrieve a few tokens with the same horizontal number.

        //Creating a consecutive slot group for a horizontal line is slightly more difficult, but still easy.
        //I need to retrieve a number of tokens from 3 different lists.
        //Every token will be retrieved with the same vertical number.

        int horizontalNumber = 1;
        int verticalNumber = 1;

        //When creating consecutive slot groups, I only need to increase the numbers.
        //I don't need to check slots that are less than the starting slot, since that slot would have been checked before.

        //First, every ordered token group needs to be accessed.
        for (OrderedTokenGroup orderedTokenGroup : this.orderedTokenGroups) {
            //Every token from this ordered token group needs to be retrieved.
            List<Token> tokens = orderedTokenGroup.getTokens();

            for (Token startingToken : tokens) {
                //Multiple consecutive slot groups will be created, since multiple directions exist.

                for (Direction direction : Directions.getDirections()) {
                    ConsecutiveSlotGroup consecutiveSlotGroup = new ConsecutiveSlotGroup(startingToken);

                    while (!consecutiveSlotGroup.getCompleted()) {
                        //The vertical and horizontal numbers will be changed by the numbers from the direction.
                        verticalNumber = verticalNumber + direction.getVerticalAddAmount();
                        horizontalNumber = horizontalNumber + direction.getHorizontalAddAmount();

                        //Both numbers will be used to retrieve the slot.
                        SlotNumbers slotNumbers = new SlotNumbers(verticalNumber, horizontalNumber);
                        Token nextToken = this.getToken(slotNumbers);

                        consecutiveSlotGroup.add(nextToken);
                    }

                    consecutiveSlotGroups.add(consecutiveSlotGroup);
                }
            }
        }

        return consecutiveSlotGroups;
    }

    /**
     * This method returns the number of ordered token groups in this board.
     * In other words, this method returns the maximum horizontal number.
     *
     * @return
     */
    public int getNumberOfOrderedTokenGroups() {
        return this.orderedTokenGroups.size();
    }


}
