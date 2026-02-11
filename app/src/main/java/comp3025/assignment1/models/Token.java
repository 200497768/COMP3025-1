package comp3025.assignment1.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is a token that has been added to the board.
 * Every token is associated with a participant.
 *
 * @author Hao Tian
 */
public class Token implements Serializable {

    /**
     * This field is the participant that this token was added by.
     */
    private Participant participant;

    /**
     * This field is the slot numbers for this token.
     * The slot numbers are used to retrieve this token from the board.
     * When this token is created, the board and vertical group are responsible for determining the slot numbers.
     * The vertical group number can be chosen by the participant, but the token number is chosen by the vertical group.
     * The token number depends on the number of existing tokens in the same vertical group.
     */
    private SlotNumbers slotNumbers;

    /**
     * This field is needed for Serializable.
     */
    private static final long serialVersionUID = 1;

    public Token(Participant participant, SlotNumbers slotNumbers) {
        Objects.requireNonNull(participant);
        this.participant = participant;

        Objects.requireNonNull(slotNumbers);
        this.slotNumbers = slotNumbers;
    }

    /**
     * This method returns the slot numbers for this token.
     * The slot numbers class includes methods that can be used to retrieve the vertical group number and token number, if needed.
     *
     * @return
     */
    public SlotNumbers getSlotNumbers() {
        return slotNumbers;
    }

    /**
     * This method returns the participant responsible for adding this token.
     *
     * @return
     */
    public Participant getParticipant() {
        return participant;
    }
}
