package comp3025.assignment1.models.actions;

import static android.widget.LinearLayout.VERTICAL;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import comp3025.assignment1.WelcomeActivity;
import comp3025.assignment1.R;
import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.TextBoard;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;

/**
 * This class is responsible for changing board area elements when the board model changes.
 */
public class BoardAreaActions extends Actions {

    /**
     * This field is the board are that needs to be changed when the model changes.
     */
    private LinearLayout boardArea;

    private Context context;

    public BoardAreaActions(Board board, LinearLayout boardArea, Context context) {
        super(board);
        this.boardArea = boardArea;
        this.context = context;
    }

    @Override
    public void boardCreated() {
        //The vertical group number will be used to create a string that shows this number.
        int verticalGroupNumber = 0;

        //The board has been created, but the views still need to be added to the board area.
        for (VerticalGroup verticalGroup : this.board.getVerticalGroups()) {

            //Create an area for this vertical group, and add it to the board area.
            LinearLayout verticalGroupArea = new LinearLayout(this.context);
            verticalGroupArea.setOrientation(VERTICAL);
            //APA for creating this is (Yadav, 2019)
            int verticalGroupColor = Color.rgb(135, 206, 235);
            verticalGroupArea.setBackgroundColor(verticalGroupColor);
            //APA is SkyBlue.
            boardArea.addView(verticalGroupArea);

            //Add a string to the vertical group area showing the number for this vertical group.
            TextView verticalGroupNumberTextView = new TextView(this.context);
            //APA for creating this view is (Yadav, 2019)
            verticalGroupNumberTextView.setText("Vertical " + verticalGroupNumber + " ");
            verticalGroupArea.addView(verticalGroupNumberTextView);

//Go through all of the tokens in this vertical group.
            //The token number needs to start with the maximum token number so that tokens are ordered how the board is supposed to be.
            for (int tokenNumber = this.board.getVerticalGroupCapacity() - 1; tokenNumber >= 0; tokenNumber = tokenNumber - 1) {
                //Create an element for this slot, depending on whether it has a token, or if it's empty.

                //Retrieve the slot from this vertical group.
                //The method from the board class is used, instead of the vertical group class, so that it can retrieve null.
                //If the token was retrieved from the vertical group, the method would only allow accessing existing tokens.
                SlotNumbers slotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);
                Token slot = this.board.getToken(slotNumbers);

                if (slot == null) {
                    //This slot is empty.
                    TextView emptyTextView = new TextView(this.context);
                    //APA for creating this view is (Yadav, 2019)
                    emptyTextView.setText("Empty ");
                    verticalGroupArea.addView(emptyTextView);
                } else {
                    //A token exists in this slot.
                    TextView tokenTextView = new TextView(this.context);
                    //APA for creating this view is (Yadav, 2019)
                    tokenTextView.setText("Token ");
                    Participant participant = new Participant("Hao Tian");
                    tokenTextView.setBackgroundColor(participant.getColor());

                    verticalGroupArea.addView(tokenTextView);
                }
            }

            //Tokens and slots for this vertical group have been added.
            //The vertical group number needs to be increased.
            verticalGroupNumber = verticalGroupNumber + 1;
        }
    }

    @Override
    public void tokenAdded() {

    }

    @Override
    public void participantWon() {

    }

    @Override
    public void boardCleared() {

    }
}
