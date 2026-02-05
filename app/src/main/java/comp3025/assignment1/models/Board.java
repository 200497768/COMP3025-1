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
    private List<VerticalGroup> verticalGroups = new ArrayList<>();

    //This field is the number of vertical groups in this board.
    private int numberOfVerticalGroups;

    //This field is the capacity for every vertical group.
    //The capacity for every vertical group in this board is the same.
    //I might need to retrieve the vertical group capacity in the future.
    private int verticalGroupCapacity;

    //This field is the number of tokens that must be added consecutively in order for a participant to win.
    private int consecutiveNumber;


    public Board(int numberOfVerticalGroups, int verticalGroupCapacity, int consecutiveNumber) {
        //The board must include a single vertical group.
        if (numberOfVerticalGroups < 1) {
            throw new IllegalArgumentException();
        }
        this.numberOfVerticalGroups = numberOfVerticalGroups;

        //Every vertical group must include a single slot.
        if (verticalGroupCapacity < 1) {
            throw new IllegalArgumentException();
        }
        this.verticalGroupCapacity = verticalGroupCapacity;

        if (consecutiveNumber < 1) {
            throw new IllegalArgumentException();
        }
        this.consecutiveNumber = consecutiveNumber;

        //Vertical groups will be created and added to this board.
        for (int number = 0; number < numberOfVerticalGroups; number = number + 1) {
            //An ordered token group will be created, and added to this board.
            VerticalGroup verticalGroup = new VerticalGroup(verticalGroupCapacity);
            this.verticalGroups.add(verticalGroup);
        }
    }

    /**
     * This method returns the number of vertical groups in this board.
     *
     * @return
     */
    public int getNumberOfVerticalGroups() {
        return numberOfVerticalGroups;
    }

    /**
     * This method returns the vertical group capacity.
     * This is the maximum number of tokens in a vertical group.
     * The vertical group capacity is the same for every vertical group in this board.
     */
    public int getVerticalGroupCapacity() {
        return verticalGroupCapacity;
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

        int tokenNumber = slotNumbers.getTokenNumber();
        int verticalGroupNumber = slotNumbers.getVerticalGroupNumber();

        //This board includes a list with multiple ordered token groups.
        //The vertical number is used to retrieve an single ordered token group.
        if (verticalGroupNumber >= this.verticalGroups.size()) {
            return null;
        }
        VerticalGroup orderedTokenGroup = this.verticalGroups.get(verticalGroupNumber);

        try {
            Token token = orderedTokenGroup.getToken(tokenNumber);
            return token;
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    /**
     * This method returns a vertical group from this board.
     * Instead of accessing the vertical groups, using the methods from the board class is recommended.
     *
     * @param verticalGroupNumber
     * @return
     */
    public VerticalGroup getVerticalGroup(int verticalGroupNumber) {
        return this.verticalGroups.get(verticalGroupNumber);
    }

    public void addToken(Participant participant, int verticalGroupNumber) {
        VerticalGroup orderedTokenGroup = this.verticalGroups.get(verticalGroupNumber);

        //In order to create a token, the slot numbers of the token in the board must be provided to the token class.
        //The slot numbers includes the vertical group number and token number.
        //This method is provided with the vertical group number, but still needs to determine the token number.

        //This determines the token number of the token that this method will add.
        //The token number can be determined by checking the number of tokens that have been added to this vertical group.
        int tokenNumber = orderedTokenGroup.getNumberAdded();

        SlotNumbers slotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);

        Token token = new Token(participant, slotNumbers);

        orderedTokenGroup.addToken(token);
    }

    /**
     * This method returns an ordered token group from this board using the horizontal number.
     *
     * @param horizontalNumber
     * @return
     */
    public VerticalGroup getOrderedTokenGroup(int horizontalNumber) {
        VerticalGroup orderedTokenGroup = this.verticalGroups.get(horizontalNumber);
        return orderedTokenGroup;
    }

    /**
     * This method creates consecutive slot groups for every slot in this board.
     * This method returns the consecutive slot groups that were created as a list.
     * Every consecutive slot groups can be used to determine if a participant has won.
     * A consecutive slot group can be created with the slot numbers of a starting slot and direction.
     * The direction can be vertical, horizontal, or diagonal.
     * This method starts with the starting slot, and uses the direction to determine the slot numbers of the next slot.
     * The next slot numbers are determined by using the direction to increase the vertical group number, token number, or both.
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

        //The slot numbers that the consecutive slot group will start with will start with 0.
        int verticalGroupNumber = 0;
        int tokenNumber = 0;

        //When creating consecutive slot groups, I only need to increase the numbers.
        //I don't need to check slots that are less than the starting slot, since that slot would have been checked before.

        //First, every vertical group needs to be accessed.
        for (VerticalGroup verticalGroup : this.verticalGroups) {
            //Every token from this ordered token group needs to be retrieved.
            List<Token> tokens = verticalGroup.getTokens();

            for (Token startingToken : tokens) {
                //Multiple consecutive slot groups will be created, since multiple directions exist.

                for (Direction direction : Directions.getDirections()) {
                    //The slot numbers for this direction need to be changed repeatedly in this direction.

                    //The vertical group number and token numbers are combined to produce the starting slot numbers.
                    //This is the slot numbers that this consecutive slot group will start with.
                    SlotNumbers startingSlotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);

                    //A consecutive slot group will be created using this combination of slot numbers and direction.
                    ConsecutiveSlotGroup consecutiveSlotGroup = new ConsecutiveSlotGroup(startingToken, this.consecutiveNumber, startingSlotNumbers, direction);

                    //The slot numbers will start with the starting slot numbers, and will be changed by adding the add amounts from the direction.
                    int changedVerticalGroupNumber = verticalGroupNumber;
                    int changedTokenNumber = tokenNumber;

                    while (!consecutiveSlotGroup.getCompletelyCreated()) {
                        //The vertical and horizontal numbers will be changed by the numbers from the direction.
                        changedVerticalGroupNumber = changedVerticalGroupNumber + direction.getHorizontalAddAmount();
                        changedTokenNumber = changedTokenNumber + direction.getVerticalAddAmount();

                        //Both numbers will be used to retrieve the slot.
                        SlotNumbers slotNumbers = new SlotNumbers(changedVerticalGroupNumber, changedTokenNumber);
                        Token nextToken = this.getToken(slotNumbers);

                        consecutiveSlotGroup.addSlot(nextToken);
                    }

                    consecutiveSlotGroups.add(consecutiveSlotGroup);
                }

                //Increase the token number in order to create a consecutive slot group for the next token.
                tokenNumber = tokenNumber + 1;
            }

            //Increase the vertical group number in order to create consecutive slot groups for the next vertical group.
            verticalGroupNumber = verticalGroupNumber + 1;
        }

        return consecutiveSlotGroups;
    }

    /**
     * This method returns the winning participant.
     * If no participant has won, this method returns null.
     *
     * @return
     */
    public Participant getWinningParticipant() {
        List<ConsecutiveSlotGroup> consecutiveSlotGroups = this.getConsecutiveSlotGroups();

        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {
            if (consecutiveSlotGroup.getWinning()) {
                return consecutiveSlotGroup.getWinningParticipant();
            }
        }

        return null;
    }

    /**
     * This method returns the number of vertical groups in this board.
     *
     * @return
     */
    public int getNumberOfOrderedTokenGroups() {
        return this.verticalGroups.size();
    }

}
