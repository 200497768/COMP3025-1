package comp3025.assignment1.models;

/**
 * This class is a group of slot numbers.
 * A group of slot numbers is used to access a certain slot in a board.
 * A group of slot numbers includes a vertical slot number and a horizontal slot number.
 * The minimum slot number is 1.
 * The maximum slot number depends on the board.
 * The horizontal number determines the vertical group that's chosen.
 * The vertical number is determines the token that's chosen in a vertical group.
 */
public class SlotNumbers {
    int verticalGroupNumber;

    private int tokenNumber;

    public SlotNumbers(int verticalGroupNumber, int tokenNumber) {
        if (verticalGroupNumber < 0) {
            throw new IllegalArgumentException();
        }

        if (tokenNumber < 0) {
            throw new IllegalArgumentException();
        }

        this.verticalGroupNumber = verticalGroupNumber;
        this.tokenNumber = tokenNumber;
    }

    public int getVerticalGroupNumber() {
        return verticalGroupNumber;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }


}
