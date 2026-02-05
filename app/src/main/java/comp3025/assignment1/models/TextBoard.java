package comp3025.assignment1.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;

/**
 * This class provides the ability to show a board using text.
 * An existing board is needed in order to use this class.
 * When creating a text board, the text board must be associated with an existing board.
 * After a text board has been created, the text board remains associated with the board.
 * The text board provides a method that produces strings to show the tokens and slots in the board.
 */
public class TextBoard {

    //This field is the board that this view will show.
    private Board board;

    public TextBoard(Board board) {
        Objects.requireNonNull(board);

        this.board = board;
    }

    /**
     * This method returns the lines that show this board.
     * When using this method, the strings that are provided will be used in the strings that this method produces.
     *
     * @return
     */
    public List<String> getLines(String tokenString, String slotString) {
        List<String> lines = new ArrayList<>();

        //Start with the top horizontal line of the board.
        for (int tokenNumber = board.getVerticalGroupCapacity() - 1; tokenNumber >= 0; tokenNumber = tokenNumber - 1) {

            String line = "Line " + tokenNumber + " ";

            //Access the slot in each vertical group, going horizontally.
            for (int verticalGroupNumber = 0; verticalGroupNumber < board.getNumberOfVerticalGroups() - 1; verticalGroupNumber = verticalGroupNumber + 1) {

                //Retrieve the vertical group with this vertical group number.
                VerticalGroup verticalGroup = board.getVerticalGroup(verticalGroupNumber);

                //Retrieve the token for this slot from the vertical group.
                //The method from the board class, not the vertical group class, must be used to retrieve the token.
                //The method from the board class returns null if no token was added to the slot.
                //The method from the vertical group class doesn't allow accessing slots before a token has been added.
                SlotNumbers slotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);
                Token token = board.getToken(slotNumbers);

                if (token == null) {
                    line = line + slotString + " ";
                } else {
                    line = line + tokenString + " ";
                }
            }

            lines.add(line);
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
        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {
            Token startingToken = consecutiveSlotGroup.getStartingToken();
            SlotNumbers startingTokenSlotNumbers = startingToken.getSlotNumbers();

            Log.i("200497768", "This is a consecutive slot group starting with " + startingTokenSlotNumbers.getVerticalGroupNumber() + " " + startingTokenSlotNumbers.getTokenNumber());

            List<Token> tokens = consecutiveSlotGroup.getTokens();
            for (Token token : tokens) {
                if (token == null) {
                    Log.i("200497768", "No token in this slot. Slot numbers can't be retrieved.");
                } else {
                    SlotNumbers slotNumbers = token.getSlotNumbers();
                    Log.i("200497768", "A token from this consecutive slot group is " + slotNumbers.getVerticalGroupNumber() + " " + slotNumbers.getTokenNumber());
                }
            }
        }

        return lines;
    }


}
