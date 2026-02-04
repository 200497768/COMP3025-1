package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An ordered token group is a vertical line in the board that can include multiple tokens.
 * The ordered token group is responsible for determining order, since the tokens fall when added to a vertical line in the board.
 */
public class OrderedTokenGroup {

    //This field is the tokens in this ordered token group.
    private List<Token> tokens=new ArrayList<>();

    public OrderedTokenGroup(int verticalMaximum) {
        this.verticalMaximum = verticalMaximum;
    }

    private int verticalMaximum;

    /**
     * This method returns a token from this ordered token group using the vertical number.
     *
     * @param verticalNumber
     * @return
     */
    public Token getToken(int verticalNumber) {
        if (verticalNumber < 0) {
            throw new IllegalArgumentException();
        }

        if (verticalNumber >= this.tokens.size()) {
            throw new IllegalArgumentException();
        }

        Token token = this.tokens.get(verticalNumber);
        return token;
    }

    /**
     * This method returns the tokens from this ordered token group.
     * In other words, this method returns tokens with the same vertical number.
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * This method returns whether a slot is still available in this ordered token group.
     * If a slot is available, a token can be added using another method from this class.
     * If a slot isn't available, a token can't be added.
     *
     * @return
     */
    public boolean getSlotAvailable() {
        return this.tokens.size() < this.verticalMaximum;
    }

    /**
     * This method adds a token to this ordered token group.
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
}
