package comp3025.assignment1.models;

/**
 * This class is a group of slot numbers.
 * A group of slot numbers is used to access a certain slot in a board.
 * A group of slot numbers includes a vertical slot number and a horizontal slot number.
 * The minimum slot number is 1.
 * The maximum slot number depends on the board.
 */
public class SlotNumbers {
    private int vertical;
    private int horizontal;

    public SlotNumbers(int vertical,int horizontal) {
        if(vertical<1){
            throw new IllegalArgumentException();
        }

        if(horizontal<1){
            throw new IllegalArgumentException();
        }

        this.vertical=vertical;
        this.horizontal=horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
