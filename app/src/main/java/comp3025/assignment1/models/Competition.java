package comp3025.assignment1.models;

import android.graphics.Color;
import android.util.Log;

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
        //Change token color for this participant.
        int tokenColor = this.getNextTokenColor();
        participant.changeTokenColor(tokenColor);

        this.participants.add(participant);
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

            //Increase the score for this participant.
            scoreParticipant.increaseScore();
        }
    }

    /**
     * This method increases the score for the participant that won this round, and clears the board.
     * When this method happens, a score participant must exist.
     */
    public void completeRound() {
        //Retrieve the score participant.
        Participant scoreParticipant = this.board.getScoreParticipant();

        //When this method happens, a score participant must exist.
        if (scoreParticipant == null) {
            throw new IllegalArgumentException();
        }

        Log.i("200497768", scoreParticipant.getName() + " has added enough consecutive tokens, and this round has finished.");

        //Clear the board.
        Log.i("200497768", "The competition is clearing the board.");
        this.board.clear();

        //Change the next turn participant to the first participant in this competition.
        Log.i("200497768", "The competition is changing the next turn participant to the first participant in this competition.");
        this.turnsCompleted = 0;

        //The board needs to be cleared.
        board.clear();
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
     * This method returns a token color that can be provided to a participant.
     * Ensure that this method produces the token color before the participant has been added to this competition.
     * This method is supposed to return a different token color for every participant, but that might not be possible.
     *
     * @return
     */
    private int getNextTokenColor() {
        if (this.participants.size() == 0) {
            int tokenColor = Color.rgb(220, 20, 60);
            //APA for numbers is Crimson.

            return tokenColor;
        } else {
            int tokenColor = Color.rgb(255, 215, 0);
            //APA for numbers is Gold.

            return tokenColor;
        }

    }
}
