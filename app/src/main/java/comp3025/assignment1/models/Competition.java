package comp3025.assignment1.models;

import android.graphics.Color;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.actions.CreatedViewActions;

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
public class Competition implements Serializable {

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
     * This field is the view actions for this competition.
     * The view actions isn't needed for this class.
     */
    private CreatedViewActions viewActions;

    /**
     * This field is the number of participants that have completed turns.
     * After every a participant adds a token, this number will be increased by 1.
     * When this number has been increased to the number of participants in this competition, the first participant starts again.
     * This number will be changed to 0 again.
     */
    private int turnsCompleted = 0;

    /**
     * This field is needed for Serializable.
     */
    private static final long serialVersionUID = 1;

    public Board getBoard() {
        return board;
    }

    /**
     * Creates a competition.
     * After this competition has been created, the view actions can be added using another method.
     *
     * @param board
     */
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
     * This method changes the view actions for this competition.
     * After providing the view actions to this method, this competition will be able to access it as a field.
     * @param viewActions
     */
    public void changeViewActions(CreatedViewActions viewActions) {
        this.viewActions = viewActions;
    }

    /**
     * This method changes the participant to the next participant.
     * This method must be used when a participant has finished adding a token to the board.
     * After every participant has completed a turn, this method changes the participant to the first participant again.
     */
    public void completeTurn() {
        //Check whether any participant has added enough consecutive tokens to increase the score, or if another turn is needed.
        Participant scoreParticipant = this.board.getScoreParticipant();

        if (scoreParticipant == null) {
            //This board needs another turn.

            //Increase the number of turns completed.
            //Change the number of turns completed to the first participant again after all participants have completed a turn.
            Log.i("200497768", "Increasing turns completed by 1. The number of turns completed was " + this.turnsCompleted);
            this.turnsCompleted = this.turnsCompleted + 1;
            if (this.turnsCompleted >= this.participants.size()) {
                this.turnsCompleted = 0;
            }
            Log.i("200497768", "The number of turns completed has been changed to " + this.turnsCompleted);

            //Show the score message explaining that another turn is needed, using the view actions class.
            if (this.viewActions != null) {
                this.viewActions.showNeedsAnotherTurn();
            }
        } else {
//This round has finished.

            //Complete the round by using the method from this class.
            //This method will show a message, clear the board, and start the next round.
            this.completeRound();
        }
    }

    /**
     * This method is the actions that must happen after a turn has caused a round to be completed.
     * This includes increasing the score for the score participant, showing a message, clearing the board, and starting the next round.
     * When this method happens, a score participant must exist.
     * This method changes the model, including clearing the board.
     * Since this method will clear the board, the score participant will no longer be available after this method.
     * If the view actions needs to show a message involving the score participant, the message must be created before completing the round.
     */
    private void completeRound() {
        //Retrieve the score participant.
        Participant scoreParticipant = this.board.getScoreParticipant();

        //A score participant must exist.
        if (scoreParticipant == null) {
            throw new IllegalStateException();
        }

        Log.i("200497768", scoreParticipant.getName() + " has added enough consecutive tokens, and this round has finished.");

        //Increase the score for the score participant.
        scoreParticipant.increaseScore();

        //Show the score message explaining that the round has finished, using the view actions class.
        if (this.viewActions != null) {
            this.viewActions.showRoundCompleted();
        }

        //Clear the board.
        Log.i("200497768", "The competition is clearing the board.");
        this.board.clear();

        //Change the next turn participant to the first participant in this competition.
        Log.i("200497768", "The competition is changing the next turn participant to the first participant in this competition.");
        this.turnsCompleted = 0;

        //The board needs to be cleared.
        board.clear();

        //The board has changed during the method that completed the round.
        if (this.viewActions != null) {
            this.viewActions.boardChanged();
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

        Log.i("200497768", "The turn participant will now be determined. Turns completed is " + this.turnsCompleted);

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
