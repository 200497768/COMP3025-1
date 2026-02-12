package comp3025.assignment1.models.participants;

import java.util.Random;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;

/**
 * This class is a computer participant.
 * The name for a computer participant can't be chosen.
 * In addition, another method will choose the vertical group that a computer participant adds tokens to.
 *
 * @author Hao Tian
 */
public class ComputerParticipant extends Participant {

    /**
     * This field is the competition that this computer participant is participating in.
     * The computer participant will access this competition in order to decide the vertical group to add a token to.
     */
    public Competition competition;

    public ComputerParticipant(Competition competition) {
        super("Computer");
        this.competition = competition;
    }

    @Override
    public boolean getPersonChoosing() {
        return false;
    }

    /**
     * This method chooses a vertical group to add a token to, and returns the number for the vertical group.
     * When the turn participant becomes the computer participant, the competition will use this method to retrieve a vertical group number.
     * This method can return any vertical group number, even if a slot isn't available in that vertical group, as long the vertical group number isn't outside of the board.
     * If this method returns a vertical group number with no slot available, the competition won't add the token to that vertical group.
     * If the competition determines that no slot is available in the vertical group, the competition will retrieve another number from this method.
     * The competition must ensure that a slot is available in the board.
     *
     * @return
     */
    public int chooseVerticalGroupNumber() {
        //Retrieve the board.
        Board board = this.competition.getBoard();

        //Retrieve the number of vertical groups in this board.
        int numberOfVerticalGroups = board.getNumberOfVerticalGroups();

        //Choose a vertical group number.
        //The number that's chosen can be 0, but it must be less than the number of vertical groups in the board.
        Random random = new Random();
        int verticalGroupNumber = random.nextInt(numberOfVerticalGroups);

        return verticalGroupNumber;
    }
}
