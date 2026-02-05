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

    //I will also need to create consecutive slot groups.
    //Consecutive slot groups allow me to check whether tokens from the same participant have been added to all slots in that group.
    //Consecutive slot groups can be created from multiple tokens in this board.
    //The tokens can be chosen from a vertical, horizontal, or diagonal line.


    //This field determines the number of tokens needed in order to win.
    //The tokens must be added by the same participant.
    //This class uses this field to determine the number of slots to add to a consecutive slot group.

    //This field is the number of tokens that must be added consecutively in order for a participant to win.
    private int consecutiveNumber;

    private int numberOfSlots;

    //Tokens, or slots, are added to this list.
    //Some elements will be tokens, and some will be null.
    //If an element is null, no tokens in that slot, or the slot is outside of the board.
    private List<Token> tokens=new ArrayList<>();

    //This field is the token that this consecutive slot group started with.
    //The starting token is used to retrieve other tokens that are next to it.
    private Token startingToken;


    public ConsecutiveSlotGroup(Token startingToken, int consecutiveNumber) {
        this.startingToken = startingToken;

        if (consecutiveNumber < 1) {
            throw new IllegalArgumentException();
        }
        this.consecutiveNumber = consecutiveNumber;
    }

    /**
     * This method returns whether this consecutive slot group has been completed.
     * When this consecutive slot group has been completed, enough slots have been added to determine whether a participant has won.
     *
     * @return
     */
    public boolean getCompletelyCreated() {
        return this.tokens.size() >= this.consecutiveNumber;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * This method returns whether every slot in this consecutive slot group includes a token.
     *
     * @return
     */
    public boolean getTokenAddedEverySlot() {
        if (!this.getCompletelyCreated()) {
            throw new IllegalStateException();
        }


        for (Token token : this.tokens) {
            //The tokens list can include null because not all tokens might have been added.
            //This can happen if a token wasn't added to the slot, or the slot is outside of the board.
            if (token == null) {
                return false;
            }
        }

        return true;
    }
    /**
     * This method allows a slot to be added to this consecutive slot group.
     * A slot can only be added if this consecutive slot group isn't complete.
     * The slot that's added can be null if it's a slot with no token.
     *
     * @param token
     */
    public void addSlot(Token token) {
        //Tokens can't be added to this consecutive token group if it has been completed.
        if (this.getCompletelyCreated()) {
            throw new IllegalStateException();
        }

        this.tokens.add(token);
    }

    /**
     * This method returns the winning participant.
     * The winning participant is determined by checking all of the slots in this consecutive slot group.
     * If the same participant has added a token to every slot, the participant has won.
     * This consecutive slot group must be completed in order for a participant to win.
     *
     * @return
     */
    public Participant getWinningParticipant() {
        //This consecutive slot group might not have been completely created.
        //The winning participant can only be determined if completely created.
        if (!this.getCompletelyCreated()) {
            return null;
        }

        //Some slots might have been added as null.
        //If a token hasn't been added to any slot, no participant can win.
        if (!this.getTokenAddedEverySlot()) {
            return null;
        }

        //The participant for every token will be checked.
        //In order for a participant to win, the participant must be the same for every token.

        Participant startingParticipant = this.startingToken.getParticipant();

        for (Token token : this.tokens) {
            Participant participant = token.getParticipant();

            //A participant can only win if all of the tokens are from the same participant.
            if (!participant.equals(startingParticipant)) {
                return null;
            }
        }

        return startingParticipant;
    }

    /**
     * This method returns whether a participant has won with this consecutive slot group.
     * This method determines this by checking whether every token was added by the same participant.
     *
     * @return
     */
    public boolean getWinning() {
        boolean winning = this.getWinningParticipant() == null;

        return winning;
    }
}
