package comp3025.assignment1.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.ConsecutiveSlotGroup;
import comp3025.assignment1.models.Direction;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;

/**
 * This class provides the ability to show a board using text.
 * An existing board is needed in order to use this class.
 * When creating a text board, the text board must be associated with an existing board.
 * After a text board has been created, the text board remains associated with the board.
 * The text board provides a method that produces strings to show the tokens and slots in the board.
 *
 * @author Hao Tian
 */
public class TextBoard {

    //This field is the board that this view will show.
    private Board board;

    //This field is the string that will be used to show a token that has been added to the board.
    private String tokenString;

    //This field is the string that will be used to show a slot in the board.
    private String slotString;

    public TextBoard(Board board, String tokenString, String slotString) {
        Objects.requireNonNull(board);
        this.board = board;

        Objects.requireNonNull(tokenString);
        this.tokenString = tokenString;

        Objects.requireNonNull(slotString);
        this.slotString = slotString;
    }

    /**
     * This method returns the lines that show this board.
     * When using this method, the strings that are provided will be used in the strings that this method produces.
     *
     * @return
     */
    public List<String> getLines() {
        List<String> lines = new ArrayList<>();

        //Start with the top horizontal line of the board.
        for (int tokenNumber = board.getVerticalGroupCapacity() - 1; tokenNumber >= 0; tokenNumber = tokenNumber - 1) {

            String line = "Line " + tokenNumber + " ";

            //Access the slot in each vertical group, going horizontally.
            for (int verticalGroupNumber = 0; verticalGroupNumber < board.getNumberOfVerticalGroups(); verticalGroupNumber = verticalGroupNumber + 1) {

                //Retrieve the vertical group with this vertical group number.
                VerticalGroup verticalGroup = board.getVerticalGroup(verticalGroupNumber);

                //Retrieve the token for this slot from the vertical group.
                //The method from the board class, not the vertical group class, must be used to retrieve the token.
                //The method from the board class returns null if no token was added to the slot.
                //The method from the vertical group class doesn't allow accessing slots before a token has been added.
                SlotNumbers slotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);
                Token token = board.getToken(slotNumbers);

                if (token == null) {
                    line = line + this.slotString + " ";
                } else {
                    line = line + this.tokenString + " ";
                }
            }

            lines.add(line);
        }

        //Add whether the score can be increased by a participant to the string.
        Participant winningParticipant = board.getScoreParticipant();
        if (winningParticipant == null) {
            lines.add("No participant has added enough consecutive tokens to increase the score at this time.");
        } else {
            lines.add("A participant has added enough consecutive tokens to increase the score. The participant is " + winningParticipant.getName());
        }

        return lines;
    }

    /**
     * This method returns strings that show the consecutive slot groups that are created by the board to determine the winning participant.
     *
     * @return
     */
    public List<String> getConsecutiveSlotGroups() {
        List<String> lines = new ArrayList<>();

        List<ConsecutiveSlotGroup> consecutiveSlotGroups = board.getConsecutiveSlotGroups();
        lines.add("Number of consecutive slot groups " + consecutiveSlotGroups.size());
        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {
            SlotNumbers startingSlotNumbers = consecutiveSlotGroup.getStartingSlotNumbers();
            Direction direction = consecutiveSlotGroup.getDirection();
            lines.add("Slot numbers " + startingSlotNumbers.getVerticalGroupNumber() + " " + startingSlotNumbers.getTokenNumber() + " " + direction.getString());
        }

        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {

            //The consecutive slot group includes a few fields that are available, even if a token wasn't added to the starting slot.
            SlotNumbers startingSlotNumbers = consecutiveSlotGroup.getStartingSlotNumbers();
            Direction direction = consecutiveSlotGroup.getDirection();
            lines.add("Vertical group number " + startingSlotNumbers.getVerticalGroupNumber() + " token number " + startingSlotNumbers.getTokenNumber() + " direction " + direction.getString() + " consecutive number " + consecutiveSlotGroup.getConsecutiveNumber());

            //The participant might be retrieved as null.
            Participant scoreParticipant = consecutiveSlotGroup.getScoreParticipant();
            if (scoreParticipant == null) {
                lines.add("No participant can increase the score.");
            } else {
                lines.add("A participant can increase the score " + scoreParticipant.getName());
            }

            //The consecutive slot group might include a starting token, depending on whether a token was added to the starting slot.
            //If a token wasn't added to the starting slot, null will be retrieved as the starting token.
            Token startingToken = consecutiveSlotGroup.getStartingToken();

            //Retrieve the tokens in this consecutive slot group.
            List<Token> tokens = consecutiveSlotGroup.getTokens();
            for (Token token : tokens) {

                //The token might not exist if a token hasn't been added to this slot, or if the slot is outside of the board.
                if (token == null) {
                    lines.add("No token exists for slot in consecutive slot group.");
                } else {
                    //The slot numbers can only be retrieved if a token was added to this slot.
                    SlotNumbers slotNumbers = token.getSlotNumbers();
                    Participant participant = token.getParticipant();

                    lines.add("A token from this consecutive slot group is " + slotNumbers.getVerticalGroupNumber() + " " + slotNumbers.getTokenNumber() + " participant " + participant.getName());
                }
            }
        }

        return lines;
    }


}
