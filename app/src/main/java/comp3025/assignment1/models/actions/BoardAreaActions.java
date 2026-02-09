package comp3025.assignment1.models.actions;

import static android.widget.LinearLayout.VERTICAL;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment1.WelcomeActivity;
import comp3025.assignment1.R;
import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
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
     * This field is the competition.
     */
    private Competition competition;

    /**
     * This field is the board are that needs to be changed when the model changes.
     */
    private LinearLayout boardArea;

    private Context context;

    public BoardAreaActions(Competition competition, LinearLayout boardArea, Context context) {
        super(competition);

        Objects.requireNonNull(competition);
        this.competition = competition;

        Objects.requireNonNull(boardArea);
        this.boardArea = boardArea;

        Objects.requireNonNull(context);
        this.context = context;
    }

    @Override
    public void boardCreated() {
        this.createBoardArea();
    }

    /**
     * This method clears the board area, and adds it to the board area that was written as a field.
     */
    private void clearBoardArea() {
        //Every vertical group includes a list with all of the views.
        //This method will go through every vertical group, retrieve every list, and remove the views that the list refers to.
        Board board = this.competition.getBoard();
        for (VerticalGroup verticalGroup : board.getVerticalGroups()) {
            for (View view : verticalGroup.getViews()) {
                this.boardArea.removeView(view);
            }
        }

        //Change the vertical group by removing the views, since the views are no longer used.
        for (VerticalGroup verticalGroup : board.getVerticalGroups()) {
            verticalGroup.clearViews();
        }
    }

    /**
     * This method creates elements that correspond to the models, and adds it to the board area.
     */
    private void createBoardArea() {
        //The vertical group number will be used to create a string that shows this number.
        int verticalGroupNumber = 0;

        Board board = this.competition.getBoard();

        //The board has been created, but the views still need to be added to the board area.
        for (VerticalGroup verticalGroup : board.getVerticalGroups()) {

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

            //Create the option to allow adding a token to this vertical group.
            Button addButton = new Button(this.context);
            addButton.setText("Add");

            Competition competition = this.competition;
            BoardAreaActions boardAreaActions = this;
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Create a new token, and add it to the vertical group.
                    //The slot numbers will be retrieved from the vertical group.
                    //The participant depends on the participant this turn.
                    SlotNumbers slotNumbers = verticalGroup.getNextTokenSlotNumbers();
                    Participant participant = competition.getParticipantForTurn();
                    Token token = new Token(participant, slotNumbers);

                    verticalGroup.addToken(token);
                    boardAreaActions.tokenAdded();
                }
            });
            verticalGroupArea.addView(addButton);

//Go through all of the tokens in this vertical group.
            //The token number needs to start with the maximum token number so that tokens are ordered how the board is supposed to be.
            for (int tokenNumber = board.getVerticalGroupCapacity() - 1; tokenNumber >= 0; tokenNumber = tokenNumber - 1) {
                //Create an element for this slot, depending on whether it has a token, or if it's empty.

                //Retrieve the slot from this vertical group.
                //The method from the board class is used, instead of the vertical group class, so that it can retrieve null.
                //If the token was retrieved from the vertical group, the method would only allow accessing existing tokens.
                SlotNumbers slotNumbers = new SlotNumbers(verticalGroupNumber, tokenNumber);
                Token slot = board.getToken(slotNumbers);

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

                    //The code still needs to be able to access this view in the future.
                    List<View> tokenViews = verticalGroup.getViews();
                    tokenViews.add(tokenTextView);
                }
            }

            //Tokens and slots for this vertical group have been added.
            //The vertical group number needs to be increased.
            verticalGroupNumber = verticalGroupNumber + 1;
        }
    }

    @Override
    public void tokenAdded() {
        //At this time, the strategy is to clear the board area, and create it again.
        this.clearBoardArea();
        this.createBoardArea();
    }

    @Override
    public void participantWon() {

    }

    @Override
    public void boardCleared() {

    }
}
