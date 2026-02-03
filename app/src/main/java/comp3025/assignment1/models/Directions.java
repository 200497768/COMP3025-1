package comp3025.assignment1.models;

/**
 * This class includes methods that produce directions.
 */
public class Directions {

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
        };

        return verticalDirection;
    }

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
        };

        return horizontalDirection;
    }

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
        };

        return diagonalDirection;
    }
}
