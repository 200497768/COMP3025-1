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
    private Participant participant;

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
