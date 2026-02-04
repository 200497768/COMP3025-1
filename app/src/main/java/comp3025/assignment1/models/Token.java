package comp3025.assignment1.models;

/**
 * This class is a token that has been added to the board.
 * Every token is associated with a participant.
 */
public class Token {
    private Participant participant;

    /**
     * This method returns the participant responsible for adding this token.
     *
     * @return
     */
    public Participant getParticipant() {
        return participant;
    }
}
