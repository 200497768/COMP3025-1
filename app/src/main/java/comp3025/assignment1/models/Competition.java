package comp3025.assignment1.models;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is a competition.
 * A competition includes participants, and the board that the participants will be using.
 * The competition class is responsible for determining turns.
 * This class provides a method that allows retrieving the participant that must add a token with this turn.
 * The competition class is also responsible for remembering score.
 * When a board has won, the competition class increases the score for that participant.
 *
 * @author Hao Tian
 */
public class Competition {

    /**
     * This field is the board that that this competition involves.
     * All of the participants will be adding tokens to this board.
     */
    private Board board;

    /**
     * This field is the participants that will be participating in this competition.
     */
    private List<Participant> participants = new ArrayList<>();

    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * This field is the number of participants that have completed turns.
     * After every a participant adds a token, this number will be increased by 1.
     * When this number has been increased to the number of participants in this competition, the first participant starts again.
     * This number will be changed to 0 again.
     */
    private int turnsCompleted = 0;

    public Board getBoard() {
        return board;
    }

    public Competition(Board board) {
        Objects.requireNonNull(board);
        this.board = board;
    }

    /**
     * This method adds a participant to this competition.
     * After a participant has been added, the method that returns the participant for this turn will include this participant.
     *
     * @param participant
     */
    public void addParticipant(Participant participant) {
        this.participants.add(participant);

        //Change token color for this participant.
        participant.changeTokenColor(this.getNextTokenColor());
    }

    /**
     * This method changes the participant to the next participant.
     * This method must be used when a participant has finished adding a token to the board.
     * After every participant has completed a turn, this method changes the participant to the first participant again.
     */
    public void completeTurn() {
        this.turnsCompleted = this.turnsCompleted + 1;

        //Change the number of turns completed to the first participant again after all participants have completed a turn.
        if (this.turnsCompleted >= this.participants.size()) {
            this.turnsCompleted = 0;
        }

        //Check whether the score for any participant needs to be increased.
        //In other words, check if any participant has added enough consecutive tokens.
        Participant scoreParticipant = this.board.getScoreParticipant();
        if (scoreParticipant == null) {
            //This board needs another turn.

        } else {
//This board has finished.

            //The board needs to be cleared.
            //The view actions class will also clear the board, so I still need to change this.
            board.clear();

            //Increase the score for this participant.
            scoreParticipant.increaseScore();
        }
    }

    /**
     * This method returns the participant for this turn.
     * This is the participant that needs to add a token to the board.
     * When a token is added to the board, it will have been added by this participant.
     *
     * @return
     */
    public Participant getTurnParticipant() {
        //Ensure that a participant has been added to this competition.
        if (this.participants.size() == 0) {
            throw new IllegalStateException();
        }

        Participant participant = this.participants.get(this.turnsCompleted);

        return participant;
    }

    /**
     * This method increases the score for the participant that won this round, and clears the board.
     */
    public void roundCompleted() {
        Participant participant = this.board.getScoreParticipant();
        this.board.clear();
    }

    public int getNextTokenColor() {
        int tokenColor = Color.rgb(220, 20, 60);
        //APA for numbers is Crimson.

        return tokenColor;
    }
}
