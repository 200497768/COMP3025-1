package comp3025.assignment1.models.actions;

import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;

/**
 * This class includes methods that will happen when the board model has been changed.
 * Another class can extend this class in order to cause actions to happen when the board model changes.
 *
 * @author Hao Tian
 */
public class Actions {
    public Competition competition;

    public Actions(Competition competition) {
        Objects.requireNonNull(competition);
        this.competition = competition;
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
     * This method happens when a participant has increased the score.
     * The class that extends this class is responsible for writing this method in order to show that the participant has increased the score.
     */
    public void participantScoreIncreased(){

    }

    /**
     * This method happens when the board has been cleared.
     * This class that extends this class is responsible for writing this method in order to show that the board has been cleared.
     */
    public void boardCleared(){

    }
}
