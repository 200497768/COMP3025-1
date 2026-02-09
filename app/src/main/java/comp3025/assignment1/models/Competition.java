package comp3025.assignment1.models;

import java.util.ArrayList;
import java.util.List;

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
    private List<Participant> participants = new ArrayList<>();

    //During a round, participants are added to this list.
    //When the number of participants added to this list is the number of participants in this competition, the action group has finished.
    //This list will be cleared, and the participant action group starts again with the first participant.
    private List<Participant> participantActionGroup = new ArrayList<>();

    /**
     * This method returns the participant that needs to add a token now.
     *
     * @return
     */
    public Participant getParticipantNow() {
        int participantActionsCompleted = participantActionGroup.size();

        Participant participant = this.participants.get(participantActionsCompleted);

        return participant;
    }
}
