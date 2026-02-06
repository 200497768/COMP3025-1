package comp3025.assignment1.models.actions;

import android.widget.LinearLayout;

import comp3025.assignment1.models.Board;

/**
 * This class is responsible for changing elements when the board model changes.
 */
public class BoardAreaActions extends Actions {

    private LinearLayout boardArea;

    public BoardAreaActions(Board board, LinearLayout boardArea) {
        super(board);
        this.boardArea = boardArea;
    }

    @Override
    public void boardCreated() {

    }

    @Override
    public void tokenAdded() {

    }

    @Override
    public void participantWon() {

    }
}
