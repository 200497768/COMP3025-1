package comp3025.assignment1.models;

/**
 * This class is a group of slot numbers.
 * A group of slot numbers is used to access a single slot in a board.
 * A group of slot numbers includes a vertical group number and a token number.
 * The minimum number is 0, and the maximum number depends on the board.
 * The vertical group number determines the vertical group that's chosen.
 * The token number is determines the token that's chosen in a vertical group.
 *
 * @author Hao Tian
 */
public class SlotNumbers {

    /**
     * This field is the vertical group number.
     * A board includes multiple vertical groups.
     * The vertical group number is used to choose a single vertical group from the board.
     */
    private int verticalGroupNumber;

    /**
     * This field is the token number.
     * A vertical group includes multiple tokens.
     * The token number is used to choose a single token from a vertical group.
     */
    private int tokenNumber;

    public SlotNumbers(int verticalGroupNumber, int tokenNumber) {
        if (verticalGroupNumber < 0) {
            throw new IllegalArgumentException();
        }
        this.verticalGroupNumber = verticalGroupNumber;

        if (tokenNumber < 0) {
            throw new IllegalArgumentException();
        }
        this.tokenNumber = tokenNumber;
    }

    /**
     * This method returns the vertical group number.
     * This number can be used to access a vertical group from a board.
     *
     * @return
     */
    public int getVerticalGroupNumber() {
        return verticalGroupNumber;
    }

    /**
     * This method returns the token number.
     * This number can be used to access a token from a vertical group.
     * @return
     */
    public int getTokenNumber() {
        return tokenNumber;
    }


}
