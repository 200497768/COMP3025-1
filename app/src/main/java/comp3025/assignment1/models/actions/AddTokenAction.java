package comp3025.assignment1.models.actions;

import java.util.Objects;

import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.SlotNumbers;

/**
 * This class is an action that can be used to add a token to a board.
 *
 * @author Hao Tian
 */
public class AddTokenAction extends Action {

    //This field is the slot numbers that the token needs to be added to.
    private SlotNumbers slotNumbers;

    public AddTokenAction(Participant participant, SlotNumbers slotNumbers) {
        super(participant);

        Objects.requireNonNull(slotNumbers);
        this.slotNumbers = slotNumbers;
    }
}
