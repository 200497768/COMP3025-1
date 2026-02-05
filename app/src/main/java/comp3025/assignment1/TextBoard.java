package comp3025.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;

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
                SlotNumbers slotNumbers = new SlotNumbers(tokenNumber, verticalGroupNumber);
                Token token = board.getToken(slotNumbers);

                if (token == null) {
                    line = line + slotString + " ";
                } else {
                    line = line + tokenString + " ";
                }
            }

        }
        return lines;
    }


}
