package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is a consecutive slot group.
 * A consecutive slot group is a group of slots or tokens in a board that are next to each other.
 * The slots or tokens can be chosen from the board using a vertical, horizontal, or diagonal line.
 * Consecutive slot groups are used to check whether tokens from the same participant have been added to all slots in that group.
 * A consecutive slot group allows the board to determine whether a participant has won by adding a group of tokens.
 * The board class uses this class by creating all of the possible consecutive slot groups for the board.
 * This class includes a method to determine if the same participant has added tokens in every slot in this group.
 * If the same participant has added tokens in every slot in this group, that participant can increase the score.
 * @author Hao Tian
 */
public class ConsecutiveSlotGroup {

    /**
     * This field is the number of consecutive tokens that must be added in order to complete this consecutive slot group.
     * After the consecutive slot group has been created, this number of slots must be added to the consecutive slot group.
     * The method that determines the score participant can't be used until the consecutive slot group has been completed.
     * When determining whether a consecutive slot group has been completed, slots with no tokens and slots that are outside of the board are included.
     */
    private int consecutiveNumber;

    /**
     * This field is the tokens, or slots, that have been added to this consecutive slot group.
     * If a token hasn't been added to a slot, or the slot is outside of the board, null can be added, until the number of slots added has increased to this number.
     */
    private List<Token> tokens=new ArrayList<>();

    /**
     * This field is the first slot or token in this consecutive slot group.
     * In other words, this field is the slot or token that this consecutive slot group started with when it was created.
     * The remaining slots or tokens in this consecutive slot group are next to this slot or token.
     */
    private Token startingToken;

    /**
     * This field is the slot numbers of the first slot or token in this consecutive slot group.
     * In other words, this field is the slot numbers of the slot or token that this consecutive slot group started with when it was created.
     * The remaining slots or tokens in this consecutive slot group are next to this slot or token.
     */
    private SlotNumbers startingSlotNumbers;

    /**
     * This field is the direction that was used in order to create this consecutive slot group.
     */
    private Direction direction;

    /**
     * This method returns the starting token for this consecutive slot group.
     * The starting token is the first token that was added when the board created this consecutive slot group.
     *
     * @return
     */
    public Token getStartingToken() {
        return startingToken;
    }

    public ConsecutiveSlotGroup(Token startingToken, int consecutiveNumber, SlotNumbers startingSlotNumbers, Direction direction) {
        Objects.requireNonNull(startingToken);
        this.startingToken = startingToken;

        if (consecutiveNumber < 1) {
            throw new IllegalArgumentException();
        }
        this.consecutiveNumber = consecutiveNumber;

        Objects.requireNonNull(startingSlotNumbers);
        this.startingSlotNumbers = startingSlotNumbers;

        Objects.requireNonNull(direction);
        this.direction = direction;
    }

    /**
     * This method returns whether this consecutive slot group has been completed.
     * When this consecutive slot group has been completed, enough slots have been added to determine whether a participant has won.
     * This consecutive slot group can't determine whether a participant has won until it has been completely created.
     * After creating a consecutive slot group, the board is responsible for repeatedly adding slots to the consecutive slot group until it has been completely created.
     *
     * @return
     */
    public boolean getCompletelyCreated() {
        return this.tokens.size() >= this.consecutiveNumber;
    }

    /**
     * This method returns the slots that have been added to this consecutive slot group.
     * If no token was added to a slot, it will be null.
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * This method returns whether a token has been added to every slot in this consecutive slot group.
     * A participant might have won if a token has been added to every slot.
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
     * This method returns the participant with the same token in every slot.
     * The score participant is determined by checking all of the slots in this consecutive slot group.
     * If the same participant has added a token to every slot, the participant has added enough tokens to increase the score.
     * This consecutive slot group must be completed in order for a participant to win.
     *
     * @return
     */
    public Participant getScoreParticipant() {
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
     * This method returns whether every slot in this consecutive slot group has a token from the same participant.
     * This method determines this by checking whether every token was added by the same participant.
     *
     * @return
     */
    public boolean getScore() {
        boolean scoreParticipantExists = this.getScoreParticipant() != null;

        return scoreParticipantExists;
    }

    public Direction getDirection() {
        return direction;
    }

    public SlotNumbers getStartingSlotNumbers() {
        return startingSlotNumbers;
    }

    public int getConsecutiveNumber() {
        return consecutiveNumber;
    }
}
