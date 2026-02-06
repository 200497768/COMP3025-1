package comp3025.assignment1.models.actions;

import comp3025.assignment1.models.Board;

/**
 * This class includes methods that will happen when the board model has been changed.
 * Another class can extend this class in order to cause actions to happen when the board model changes.
 */
public class BoardChanged {
    private Board board;

    public BoardChanged(Board board) {
        this.board = board;
    }

    /**
     * This method happens when the board has been created.
     */
    public void boardCreated(){

    }

    /**
     * This method happens when a token has been added to the board.
     */
    public void tokenAdded(){

    }

    /**
     * This method happens when a participant has won.
     */
    public void participantWon(){

    }

    /**
     * This method happens when the board has been cleared.
     */
    public void boardCleared(){

    }
}
