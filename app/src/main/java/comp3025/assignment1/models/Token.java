package comp3025.assignment1.models;

import java.util.Objects;

/**
 * This class is a token that has been added to the board.
 * Every token is associated with a participant.
 */
public class Token {
    private Participant participant;

    private String information;


    public Token(Participant participant) {
        Objects.requireNonNull(participant);

        this.participant = participant;

        this.information = "No information added";
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
