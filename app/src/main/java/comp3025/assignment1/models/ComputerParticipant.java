package comp3025.assignment1.models;

/**
 * This class is a computer participant.
 * The name for a computer participant can't be chosen.
 * In addition, another method will choose the vertical group that a computer participant adds tokens to.
 *
 * @author Hao Tian
 */
public class ComputerParticipant extends Participant {

    public ComputerParticipant() {
        super("Computer");
    }

    @Override
    public boolean getPersonChoosing() {
        return false;
    }
}
