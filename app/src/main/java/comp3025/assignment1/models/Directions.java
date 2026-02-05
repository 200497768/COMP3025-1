package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods that produce directions.
 * The methods can be used to create vertical, horizontal, and diagonal directions.
 */
public class Directions {

    /**
     * This method produces a list with all of the directions.
     *
     * @return
     */
    public static List<Direction> getDirections() {
        List<Direction> directions = new ArrayList<>();

        directions.add(Directions.getVerticalDirection());
        directions.add(Directions.getHorizontalDirection());
        directions.add(Directions.getDiagonalDirection());

        return directions;
    }

    /**
     * This method creates and returns the vertical direction.
     *
     * @return
     */
    public static Direction getVerticalDirection() {
        Direction verticalDirection = new Direction() {
            @Override
            public int getVerticalAddAmount() {
                return 1;
            }

            @Override
            public int getHorizontalAddAmount() {
                return 0;
            }

            @Override
            public String getString() {
                return "Vertical";
            }
        };

        return verticalDirection;
    }

    /**
     * This method creates and returns the horizontal direction.
     * @return
     */
    public static Direction getHorizontalDirection() {
        Direction horizontalDirection = new Direction() {
            @Override
            public int getVerticalAddAmount() {
                return 0;
            }

            @Override
            public int getHorizontalAddAmount() {
                return 1;
            }

            @Override
            public String getString() {
                return "Horizontal";
            }
        };

        return horizontalDirection;
    }

    /**
     * This method creates and returns the diagonal direction.
     * @return
     */
    public static Direction getDiagonalDirection() {
        Direction diagonalDirection = new Direction() {
            @Override
            public int getVerticalAddAmount() {
                return 1;
            }

            @Override
            public int getHorizontalAddAmount() {
                return 1;
            }

            @Override
            public String getString() {
                return "Diagonal";
            }
        };

        return diagonalDirection;
    }
}
