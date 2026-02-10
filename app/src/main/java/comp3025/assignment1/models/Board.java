package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.actions.Action;

/**
 * This class is a board.
 * A board includes multiple slots.
 * When the board is created, the number of vertical and horizontal slots must be entered.
 * After the board has been created, slots can be accessed using numbers.
 *
 * @author Hao Tian
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

    public int getConsecutiveNumber() {
        return consecutiveNumber;
    }

    //This field is a list showing the actions that have happened to this board since it was created.
    private List<Action> actions = new ArrayList<>();

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

        this.createVerticalGroups();
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
     * This method returns the vertical groups in this board.
     * This method must only be used to show the vertical groups at this time.
     * Using this method to refer to vertical groups isn't recommended because the vertical groups that are in this board might change in the future.
     * If the board is cleared, the vertical groups retrieved using this method might no longer be in the board.
     *
     * @return
     */
    public List<VerticalGroup> getVerticalGroups() {
        return verticalGroups;
    }

    /**
     * This method returns a vertical group from this board, using the vertical group number.
     * Using this method isn't recommended because accessing the vertical groups can cause problems.
     * If this method is used to retrieve vertical groups to add a token, using the methods from the board class instead of this method is recommended.
     * The vertical groups retrieved using this method must only be used to show the board.
     * If the vertical groups need to be accessed again in the future, the vertical groups must be retrieved using this method again.
     * After some actions, a vertical group retrieved using this method because it was part of the board might no longer be part of the board.
     *
     * @param verticalGroupNumber
     * @return
     */
    public VerticalGroup getVerticalGroup(int verticalGroupNumber) {
        return this.verticalGroups.get(verticalGroupNumber);
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
     * This method adds a token to the board.
     * When using this method, the participant that's adding the token must be provided.
     * The participant can be retrieved using the competition.
     * In addition, the vertical group number must be provided.
     * The vertical group number determines the vertical group in the board that the token will be added to.
     * The board and the vertical group in this board will decide the token number, depending on the number of existing tokens in the vertical group.
     *
     * @param participant
     * @param verticalGroupNumber
     */
    public void addToken(Participant participant, int verticalGroupNumber) {
        VerticalGroup verticalGroup = this.verticalGroups.get(verticalGroupNumber);

        //In order to create a token, the slot numbers of the token in the board must be provided to the token class.

        //Create the slot numbers for the token that will be created.
        //This determines the token number of the token that this method will add.
        SlotNumbers slotNumbers = verticalGroup.getNextTokenSlotNumbers();

        //Create the token.
        Token token = new Token(participant, slotNumbers);

        //Add the token to the vertical group.
        verticalGroup.addToken(token);
    }

    /**
     * This method creates consecutive slot groups for every slot in this board.
     * This method returns the consecutive slot groups that were created as a list.
     * Every consecutive slot groups can be used to determine if a participant has won.
     * A consecutive slot group can be created with the slot numbers of a starting slot and direction.
     * The direction can be vertical, horizontal, or diagonal.
     * This method starts with the starting slot, and uses the direction to determine the slot numbers of the next slot.
     * The next slot numbers are determined by using the direction to increase the vertical group number, token number, or both.
     * Only slots with tokens added will be chosen as the starting slot for consecutive slot groups.
     * If no token has been added to a slot, this method won't create a consecutive slot group starting with that slot.
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
                        //Both numbers will be used to retrieve the slot.
                        SlotNumbers slotNumbers = new SlotNumbers(changedVerticalGroupNumber, changedTokenNumber);
                        Token nextToken = this.getToken(slotNumbers);

                        consecutiveSlotGroup.addSlot(nextToken);

                        //The vertical and horizontal numbers will be changed by the numbers from the direction.
                        changedVerticalGroupNumber = changedVerticalGroupNumber + direction.getHorizontalAddAmount();
                        changedTokenNumber = changedTokenNumber + direction.getVerticalAddAmount();
                    }

                    consecutiveSlotGroups.add(consecutiveSlotGroup);
                }

                //Increase the token number in order to create a consecutive slot group for the next token.
                tokenNumber = tokenNumber + 1;
            }

            //Increase the vertical group number in order to create consecutive slot groups for the next vertical group.
            verticalGroupNumber = verticalGroupNumber + 1;

            //Change the token number to the first token in this vertical group.
            tokenNumber = 0;
        }

        return consecutiveSlotGroups;
    }



    /**
     * This method returns the participant with enough consecutive tokens to increase the score.
     * If no participant has added enough consecutive tokens, this method returns null.
     *
     * @return
     */
    public Participant getScoreParticipant() {
        List<ConsecutiveSlotGroup> consecutiveSlotGroups = this.getConsecutiveSlotGroups();

        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {
            if (consecutiveSlotGroup.getScore()) {
                return consecutiveSlotGroup.getScoreParticipant();
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

    /**
     * This method changes the board by adding the action.
     *
     * @param action
     */
    public void addAction(Action action) {
        Objects.requireNonNull(action);

        //This board includes a list that shows the actions that have happened.
        //This action needs to be added to that list.
        this.actions.add(action);

        //This board will be changed by this action.
    }

    /**
     * This method removes all of the tokens that have been added to the board.
     * Depending on how this method was written, this method might clear the existing vertical groups, or it might create new vertical groups.
     * Depending on how this method was written, vertical groups that were retrieved before this method might no longer be part of this board.
     * If the vertical groups need to be accessed, retrieving the vertical groups again after this method is recommended.
     */
    public void clear() {
        this.createVerticalGroups();
    }

    /**
     * This method creates the vertical groups for this board.
     * This method creates a list, and adds vertical groups to that list.
     * After this method, any existing vertical groups can no longer be accessed.
     */
    private void createVerticalGroups() {
        //Vertical groups will be created and added to this board.
        this.verticalGroups = new ArrayList<>();
        for (int verticalGroupNumber = 0; verticalGroupNumber < numberOfVerticalGroups; verticalGroupNumber = verticalGroupNumber + 1) {
            //An ordered token group will be created, and added to this board.
            VerticalGroup verticalGroup = new VerticalGroup(verticalGroupNumber, verticalGroupCapacity);
            this.verticalGroups.add(verticalGroup);
        }
    }
}
