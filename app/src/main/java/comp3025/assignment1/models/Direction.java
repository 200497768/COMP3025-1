package comp3025.assignment1.models;

/**
 * This class is the direction model.
 * A direction includes a vertical add amount, and a horizontal add amount.
 * The add amounts can be added to a existing vertical and horizontal numbers in order to proceed in this direction.
 * The directions class can be used to retrieve direction models for vertical, horizontal, and diagonal directions.
 *
 * @author Hao Tian
 */
public class Direction {

    /**
     * This method returns the number that needs to be added in order to proceed vertically in this direction.
     *
     * @return
     */
    public int getVerticalAddAmount() {
        return 0;
    }

    /**
     * This method returns the number that needs to be added in order to proceed horizontally in this direction.
     * @return
     */
    public int getHorizontalAddAmount() {
        return 0;
    }

    /**
     * This method returns a word that explains this direction.
     *
     * @return
     */
    public String getString() {
        return "string";
    }


}
