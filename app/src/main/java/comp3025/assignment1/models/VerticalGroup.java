package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A vertical group is a vertical line in the board that can include multiple tokens.
 * The vertical group is responsible for determining order, since the tokens fall when added to a vertical line in the board.
 */
public class VerticalGroup {

    //This field is the tokens in this ordered token group.
    private List<Token> tokens=new ArrayList<>();

    public VerticalGroup(int capacity) {
        this.capacity = capacity;
    }

    private int capacity;

    /**
     * This method returns a token from this vertical group.
     * This method only allows accessing a slot with a token.
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
     * In other words, this method returns tokens with the same vertical number.
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
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
}
