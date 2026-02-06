package comp3025.assignment1.models.actions;

import java.util.Objects;

import comp3025.assignment1.models.Board;

/**
 * This class includes methods that will happen when the board model has been changed.
 * Another class can extend this class in order to cause actions to happen when the board model changes.
 */
public class Actions {
    public Board board;

    public Actions(Board board) {
        Objects.requireNonNull(board);
        this.board = board;
    }

    /**
     * This method happens when the board has been created.
     * The class that extends this class is responsible for writing this method in order to show the board.
     */
    public void boardCreated(){

    }

    /**
     * This method happens when a token has been added to the board.
     * The class that extends this class is responsible for writing this method in order to show the token that was added.
     */
    public void tokenAdded(){

    }

    /**
     * This method happens when a participant has won.
     * The class that extends this class is responsible for writing this method in order to show that the participant has won.
     */
    public void participantWon(){

    }

    /**
     * This method happens when the board has been cleared.
     * This class that extends this class is responsible for writing this method in order to show that the board has been cleared.
     * This isn't possible with the board model at this time, but I might add the ability to clear the board in the future.
     */
    public void boardCleared(){

    }
}
