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

    /**
     * This method returns a token from this ordered token group using the vertical number.
     *
     * @param verticalNumber
     * @return
     */
    public Token getToken(int verticalNumber) {
        Token token = this.tokens.get(verticalNumber - 1);
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
     * This method adds a token to this ordered token group.
     * In other words, this method adds a token to this vertical line.
     * This ordered token group will be responsible for determining the vertical number for the token that was added.
     * The vertical number can't be chosen when using this method, since the token falls in the board.
     *
     * @param token
     */
    public void addToken(Token token) {
        Objects.requireNonNull(token);

        this.tokens.add(token);
    }
}
