package comp3025.assignment1.models.actions;

import java.util.Objects;

import comp3025.assignment1.models.Participant;

/**
 * This class is an action that can happen to a board.
 */
public class Action {

    //This field is the participant that caused the action.
    private Participant participant;

    public Action(Participant participant) {
        Objects.requireNonNull(participant);
        this.participant = participant;
    }
}
