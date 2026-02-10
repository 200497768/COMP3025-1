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
public class CreatedViewActions extends Actions {

    /**
     * This field is the competition.
     */
    private Competition competition;

    /**
     * This field is the board are that needs to be changed when the model changes.
     */
    private LinearLayout boardArea;

    /**
     * This field is the area that will be used for the options to add tokens to the board.
     */
    private LinearLayout addArea;

    /**
     * This field is the area that will be used to show the vertical groups.
     * This class will create a vertical group area for every vertical group in the board.
     * The vertical group areas will be added to the vertical groups area.
     */
    private LinearLayout verticalGroupsArea;

    private TextView scoreTextView;

    private Context context;

    /**
     * This field is all of the vertical group areas that have been added to the vertical groups area.
     */
    private List<LinearLayout> verticalGroupAreas = new ArrayList<>();

    public CreatedViewActions(Competition competition, LinearLayout boardArea, LinearLayout verticalGroupsArea, LinearLayout addArea, TextView scoreTextView, Context context) {
        super(competition);

        Objects.requireNonNull(competition);
        this.competition = competition;

        Objects.requireNonNull(boardArea);
        this.boardArea = boardArea;

        Objects.requireNonNull(addArea);
        this.addArea = addArea;

        Objects.requireNonNull(scoreTextView);
        this.scoreTextView = scoreTextView;

        Objects.requireNonNull(verticalGroupsArea);
        this.verticalGroupsArea = verticalGroupsArea;

        Objects.requireNonNull(context);
        this.context = context;
    }

    @Override
    public void boardCreated() {
        Log.i("200497768", "The board has been created.");
        this.createVerticalGroupAreas();
        this.addSlotsAndTokens();
    }

    /**
     * This method clears the vertical groups area by removing every vertical group area that was added to this area.
     */
    private void clearVerticalGroupsArea() {
        Log.i("200497768", "The vertical groups area will be cleared.");
        for (LinearLayout verticalGroupArea : this.verticalGroupAreas) {
            verticalGroupArea.removeAllViews();
        }

//Clear the field for this class.
        this.verticalGroupAreas = new ArrayList<>();
    }

    /**
     * This method creates the vertical group areas, and adds the vertical group areas to the vertical groups area.
     * Every vertical group area includes the option to add a token to this vertical group.
     */
    private void createVerticalGroupAreas() {
        Log.i("200497768", "Vertical group areas are being added.");

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
            verticalGroupsArea.addView(verticalGroupArea);

            //Add this vertical group area to the list, so that this class will be able to access it in the future.
            this.verticalGroupAreas.add(verticalGroupArea);

            //Add a string to the vertical group area showing the number for this vertical group.
            TextView verticalGroupNumberTextView = new TextView(this.context);
            //APA for creating this view is (Yadav, 2019)
            verticalGroupNumberTextView.setText("Vertical " + verticalGroupNumber + " ");
            verticalGroupArea.addView(verticalGroupNumberTextView);

            //Create the option to allow adding a token to this vertical group.
            Button addButton = new Button(this.context);
            addButton.setText("Add");

            Competition competition = this.competition;
            CreatedViewActions viewActions = this;

            //The vertical group number needs to be written this way in order for the method to access it.
            int thisVerticalGroupNumber = verticalGroupNumber;

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Add a token to the model.

                    //The token must be added using a method from the board class, not by accessing a vertical group.
                    //At this time, the vertical group that's retrieved from the board refers to the vertical group in the board.
                    //In the future, when the board has been cleared, the vertical group retrieved by this method might no longer be correct.
                    //The vertical group retrieved by this method at this time might no longer be part of the board, depending on how the method to clear the board was written.

                    board.addToken(competition.getTurnParticipant(), thisVerticalGroupNumber);

                    //Show the token.
                    viewActions.tokenAdded();
                }
            });

            //Add the option to the add area.
            this.addArea.addView(addButton);

            //Tokens and slots for this vertical group have been added.
            //The vertical group number needs to be increased.
            verticalGroupNumber = verticalGroupNumber + 1;
        }
    }

    /**
     * This method adds elements to the vertical group areas.
     * Every slot or token from the model will be added to the vertical group area.
     */
    private void addSlotsAndTokens() {
        Log.i("200497768", "Slots and tokens are being added to the vertical group areas.");

        //The vertical group number will be used to create a string that shows this number.
        int verticalGroupNumber = 0;

        Board board = this.competition.getBoard();

        for (LinearLayout verticalGroupArea : this.verticalGroupAreas) {
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

                    //Retrieve the participant, in order to access name and token color.
                    Participant participant = slot.getParticipant();

                    //Determine the token color that will be use to show this token.
                    tokenTextView.setBackgroundColor(participant.getTokenColor());

                    //Determine text to use to show this token.
                    tokenTextView.setText("Token ");

                    verticalGroupArea.addView(tokenTextView);
                }
            }

            //Tokens and slots for this vertical group have been added.
            //The vertical group number needs to be increased.
            verticalGroupNumber = verticalGroupNumber + 1;
        }
    }

    /**
     * This method must be used when the board has changed.
     * This method shows the board, after it has been changed.
     */
    public void boardChanged() {
        //At this time, the strategy is to clear the board area, and create it again.
        Log.i("200497768", "The board has been changed.");
        this.clearVerticalGroupsArea();
        this.createVerticalGroupAreas();
        this.addSlotsAndTokens();
    }

    @Override
    public void tokenAdded() {
        Log.i("200497768", "A token has been added.");
        this.boardChanged();

        //Complete the turn with the competition.
        this.competition.completeTurn();

        //This class will cause the method from the competition class to happen.
        //The competition class is the model, and will be responsible for changing the board to prepare it for the next round.

        //Show the text board.
        Board board = this.competition.getBoard();
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");
        for (String string : textBoard.getLines()) {
            Log.i("200497768", string);
        }
    }

    public void showNeedsAnotherTurn() {
        Participant nextTurnParticipant = competition.getTurnParticipant();
        this.showScoreMessage("A participant added a token. Now, " + nextTurnParticipant.getName() + " needs to add a token to the board. " + this.getScoreMessage());
    }

    public void showRoundCompleted() {
        //Retrieve the score participant.
        Board board = this.competition.getBoard();
        Participant scoreParticipant = board.getScoreParticipant();

        this.showScoreMessage(scoreParticipant.getName() + " has added " + board.getConsecutiveNumber() + " consecutive tokens. The score for this participant has increased. " + this.getScoreMessage());
    }

    /**
     * This method creates the score message as a string.
     */
    private String getScoreMessage() {
        String message = "";

        List<Participant> participants = this.competition.getParticipants();
        for (Participant participant : participants) {
            message = message + participant.getName() + " " + participant.getScore() + " ";
        }

        return message;
    }

    /**
     * This method changes the score message to the string that's provided.
     *
     * @param message
     */
    private void showScoreMessage(String message) {
        Log.i("200497768", "The score message is being changed to " + message);
        this.scoreTextView.setText(message);
    }

    @Override
    public void participantScoreIncreased() {

    }

    @Override
    public void boardCleared() {

    }
}
