package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;

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

    public List<Token> getTokens() {
        return tokens;
    }
}
