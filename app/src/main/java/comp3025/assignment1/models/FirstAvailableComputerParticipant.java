package comp3025.assignment1.models;

import comp3025.assignment1.models.participants.ComputerParticipant;

/**
 * This class is a computer participant.
 * This computer participant chooses the first vertical group in the board that's available.
 *
 * @author Hao Tian
 */
public class FirstAvailableComputerParticipant extends ComputerParticipant {
    public FirstAvailableComputerParticipant(Competition competition) {
        super(competition);
    }

    @Override
    public int chooseVerticalGroupNumber() {
        //Retrieve the board.
        Board board = this.competition.getBoard();

        //Check whether the board has been completed.
        //This method won't be able to produce a vertical group number if the board has been completed.
        if (board.getCompleted()) {
            throw new IllegalStateException();
        }

        //Retrieve the number of vertical groups in this board.
        int numberOfVerticalGroups = board.getNumberOfVerticalGroups();

        //Go through the vertical group numbers, until a vertical group with a slot available.
        for (int verticalGroupNumber = 0; verticalGroupNumber < numberOfVerticalGroups; verticalGroupNumber = verticalGroupNumber + 1) {
            //Check whether this vertical group has a slot available.
            if (board.getSlotAvailable(verticalGroupNumber)) {
                return verticalGroupNumber;
            }
        }

        //No vertical groups are available.
        return 0;
    }
}
