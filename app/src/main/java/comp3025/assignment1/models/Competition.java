package comp3025.assignment1.models;

import android.graphics.Color;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.participants.ComputerParticipant;
import comp3025.assignment1.models.participants.Participant;
import comp3025.assignment1.views.ViewActions;


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
     * This field is the board that this competition involves.
     * All of the participants will be adding tokens to this board.
     */
    private Board board;

    /**
     * This field is the participants that will be participating in this competition.
     */
    private List<Participant> participants = new ArrayList<>();

    /**
     * This field is the view actions for this competition.
     * The view actions isn't needed for this class.
     */
    private ViewActions viewActions;

    /**
     * This field is the number of participants that have completed turns.
     * After every a participant adds a token, this number will be increased by 1.
     * When this number has been increased to the number of participants in this competition, the first participant starts again.
     * This number will be changed to 0 again.
     */
    private int turnsCompleted = 0;

    /**
     * This field is the maximum score for this competition.
     * The competition ends when the score for a participant has increased to the maximum score.
     */
    private int maximumScore = 3;

    /**
     * This field is needed for Serializable.
     */
    private static final long serialVersionUID = 1;

    /**
     * This method returns the participants in this competition.
     *
     * @return
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * This method returns the winning participant for this competition.
     * A participant has won the competition if the score of the participant is the maximum score.
     * The maximum score was determined when this competition was created.
     * If no participants have won this competition, this method returns null.
     * This method can be used to check whether this competition still needs another turn, or if it has been completed.
     * In order to determine whether another round is needed, check whether this method returns null for the winning participant.
     *
     * @return
     */
    public Participant getWinningParticipant() {
        //Go through all of the participants, and check the score of every participant.
        for (Participant participant : this.participants) {

            //Check whether the score for this participant is the maximum score for this competition.
            if (participant.getScore() >= this.maximumScore) {
                return participant;
            }
        }

        //No participants have won this competition.
        return null;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Creates a competition.
     * After this competition has been created, the view actions can be added using another method.
     *
     * @param board
     */
    public Competition(Board board, int maximumScore) {
        Objects.requireNonNull(board);
        this.board = board;

        if (maximumScore < 1) {
            throw new IllegalArgumentException();
        }

        this.maximumScore = maximumScore;
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
    public void changeViewActions(ViewActions viewActions) {
        this.viewActions = viewActions;
    }

    /**
     * This method adds a token to the vertical group for the turn participant, if possible.
     */
    public void addToken(int verticalGroupNumber) {
        //The token must be added using a method from the board class, not by accessing a vertical group.
        //At this time, the vertical group that's retrieved from the board refers to the vertical group in the board.
        //In the future, when the board has been cleared, the vertical group retrieved by this method might no longer be correct.
        //The vertical group retrieved by this method at this time might no longer be part of the board, depending on how the method to clear the board was written.

        //Check whether a slot is available.
        if (this.board.getSlotAvailable(verticalGroupNumber)) {
            //Add the token, since a slot is available.

            Participant turnParticipant = this.getTurnParticipant();
            Log.i("200497768", "Adding a token for " + turnParticipant.getName());
            board.addToken(turnParticipant, verticalGroupNumber);
            Log.i("200497768", "A token has been added.");

            //Show the token.
            this.viewActions.boardChanged();

            //Complete the turn.
            this.completeTurn();
        }

        //The turn must not be completed if the token wasn't added.
        //This ensures that the turn participant won't be changed, and the turn participant will be able to choose another vertical group.
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

            //Increase the turn number.
            //After the turn number has been increased, the turn participant will be the next participant in the competition.
            //Change the number of turns completed to the first participant again after all participants have completed a turn.
            Log.i("200497768", "Increasing turns completed by 1. The number of turns completed was " + this.turnsCompleted);
            this.turnsCompleted = this.turnsCompleted + 1;
            if (this.turnsCompleted >= this.participants.size()) {
                this.turnsCompleted = 0;
            }
            Log.i("200497768", "The number of turns completed has been changed to " + this.turnsCompleted);

            //Check whether the board has been completed.
            //Another turn is only possible if the board hasn't been completed.
            if (this.board.getCompleted()) {
                //The board has been completed, and another turn isn't possible.
                Log.i("200497768", "The board has been completed, and another turn isn't possible.");

                //This round has finished.
                //Complete the round by using the method from this class.
                //This method will show a message, clear the board, and start the next round.
                this.completeRound();
            }

            //Show the score message explaining that another turn is needed, using the view actions class.
            if (this.viewActions != null) {
                this.viewActions.showNeedsAnotherTurn();
            }

            //Check whether the turn participant is a computer participant.
            //This must happen after the turn number has been increased, since it involves retrieving the turn participant.
            //When this method has finished, another token needs to be able to be added.
            Participant participant = this.getTurnParticipant();
            if (!participant.getPersonChoosing()) {
                //The turn participant is a computer participant.
                ComputerParticipant computerParticipant = (ComputerParticipant) participant;

                //Choose a vertical group number to add the token to.
                int verticalGroupNumber =computerParticipant.chooseVerticalGroupNumber();

                //Check whether a slot is available in this vertical group.
                while (!this.board.getSlotAvailable(verticalGroupNumber)) {
                    //Choose another vertical group number.
                    verticalGroupNumber = computerParticipant.chooseVerticalGroupNumber();
                }

                //Add the token.
                this.board.addToken(participant, verticalGroupNumber);

                //Change the turn participant to the next participant.
                this.completeTurn();

                //The board has changed.
                //Use the method from the view actions class to show the token that has been added by the computer participant.
                if (this.viewActions != null) {
                    this.viewActions.boardChanged();
                }
            }
        } else {
//This round has finished.
            Log.i("200497768", scoreParticipant.getName() + " has added enough consecutive tokens, and this round has finished.");

            //The score for the participant must be increased before using the view actions to show the fact that the round has been completed.
            //This allows the view actions to retrieve the increased score.

            //Increase the score for the score participant.
            scoreParticipant.increaseScore();

            //Use the view actions to show that the round has completed.
            if (this.viewActions != null) {
                this.viewActions.boardChanged();
            }

            //Show the score message explaining that the round has finished, using the view actions class.
            if (this.viewActions != null) {
                this.viewActions.showRoundCompleted();
            }
        }
    }

    /**
     * This method is the actions that must happen after a turn has caused a round to be completed.
     * This method happens when the round has finished.
     * This includes showing a message, clearing the board, and starting the next round.
     * When this method happens, a score participant might exist.
     * Since this method will clear the board, the score participant will no longer be available after this method.
     * If the view actions needs to show a message involving the score participant, the message must be created before completing the round.
     * A round can finish if a participant has added enough consecutive tokens to increase the score, or if the board has been completed.
     * This method isn't responsible for increasing the score, since the score is supposed to have been increased before this method.
     * This method must not use the showRoundCompleted method from the view actions, since the next round option has caused this method to happen.
     * The showRoundCompleted method adds the next round option, and that option isn't needed at this time.
     */
    public void completeRound() {
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

        //Check whether this competition has been completed.
        Participant winningParticipant = this.getWinningParticipant();
        if (winningParticipant != null) {
            //A participant has won, and this competition has been completed.

            //Before running the method, remove the view actions field, in order to avoid a problem with Serializable.
            //The view actions is still needed, so it will be available during this method.
            ViewActions viewActions = this.viewActions;
            this.viewActions = null;

            //Run the method from the view actions.
            viewActions.competitionCompleted();
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
