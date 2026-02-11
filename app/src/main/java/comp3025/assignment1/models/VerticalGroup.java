package comp3025.assignment1.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A vertical group is a vertical line in the board that can include multiple tokens.
 * The vertical group is responsible for determining order, since the tokens fall when added to a vertical line in the board.
 * When a vertical group is created, the capacity must be provided.
 * The vertical group starts with no tokens.
 * Tokens can be added to the vertical group using a method from this class.
 * A vertical group doesn't allow retrieving a token from a slot number if a token hasn't been added with that slot number.
 *
 * @author Hao Tian
 */
public class VerticalGroup implements Serializable {

    /**
     * This field is the tokens in this ordered token group.
     * This list starts with no tokens.
     * The vertical group class includes a method that can be used to add tokens, and retrieve tokens that have been added.
     * Using the method from the vertical group class to retrieve tokens isn't recommended.
     * Instead, this vertical group needs to be added to a board, and the method from the board class needs to be used to retrieve tokens.
     */
    private List<Token> tokens=new ArrayList<>();

    /**
     * This field is needed for Serializable.
     */
    private static final long serialVersionUID = 1;

    public VerticalGroup(int verticalGroupNumber, int capacity) {
        if(verticalGroupNumber<0){
            throw new IllegalArgumentException();
        }
        this.verticalGroupNumber = verticalGroupNumber;

        if(capacity<0){
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    /**
     * This field is the maximum number of tokens that can be added to this vertical group.
     * The board provides this number when creating this vertical group.
     * This number is supposed to be the same for every vertical group in a board.
     */
    private int capacity;

    /**
     * This field is the vertical group number of this vertical group.
     * A board includes multiple vertical groups.
     * The vertical group number is used to retrieve this vertical group from a board.
     * This field isn't needed if this vertical group hasn't been added to a board.
     * If this number isn't correct, the method that produces slot numbers must not be used.
     */
    private int verticalGroupNumber;

    /**
     * This method returns a token from this vertical group.
     * Using this method isn't recommended because this method only allows accessing a slot with a token.
     * If a token hasn't been added to a slot, this method can't be used to access that slot.
     * This method won't return null if a token hasn't been added to the slot.
     * If null is needed for a slot with no token, use the method from the board class.
     *
     * @return
     */
    public Token getToken(int tokenNumber) {
        if (tokenNumber < 0) {
            throw new IllegalArgumentException();
        }

        if (tokenNumber >= this.tokens.size()) {
            throw new IllegalArgumentException();
        }

        Token token = this.tokens.get(tokenNumber);
        return token;
    }

    /**
     * This method returns the tokens from this vertical group.
     * In other words, this method returns tokens with the same vertical number in the board.
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * This method returns the tokens from this vertical group, including slots.
     * If a slot exists in this vertical group, the list that's returned will include null.
     */
    public List<Token> getSlots() {
        List<Token> slots = new ArrayList<>();

        //The tokens that exist in this vertical group need to be added.
        for (Token token : this.tokens) {
            slots.add(token);
        }

        //In addition, the slots need to be added until the number of tokens and slots combined is the capacity of this vertical group.
        while (slots.size() < this.capacity) {
            slots.add(null);
        }

        return slots;
    }

    /**
     * This method returns whether a slot is still available in this vertical group.
     * If a slot is available, a token can be added using another method from this class.
     * If a slot isn't available, a token can't be added.
     *
     * @return
     */
    public boolean getSlotAvailable() {
        return this.tokens.size() < this.capacity;
    }

    /**
     * This method adds a token to this vertical group.
     * In other words, this method adds a token to this vertical line.
     * This ordered token group will be responsible for determining the vertical number for the token that was added.
     * The vertical number can't be chosen when using this method, since the token falls in the board.
     *
     * @param token
     */
    public void addToken(Token token) {
        Objects.requireNonNull(token);

        if (!this.getSlotAvailable()) {
            throw new IllegalStateException();
        }

        this.tokens.add(token);
    }

    /**
     * This method returns the number of tokens that have been added to this vertical group.
     *
     * @return
     */
    public int getNumberAdded() {
        return this.tokens.size();
    }

    /**
     * This method returns the slot numbers for the token that will be added to this vertical group.
     * This method can be used to determine the slot numbers for the next token that will be added to this vertical group.
     * This method produces slot numbers.
     * The vertical group number is the vertical group number for this vertical group.
     * The token number of the next token depends on the number of tokens that have been added to this vertical group.
     * The token number is the same number as the number of tokens that have been added to this vertical group.
     * @return
     */
    public SlotNumbers getNextTokenSlotNumbers() {
        int tokenNumber = this.getNumberAdded();

        SlotNumbers slotNumbers = new SlotNumbers(this.verticalGroupNumber, tokenNumber);
        return slotNumbers;
    }
}
