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
public class ViewActions extends Actions {

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

    public ViewActions(Competition competition, LinearLayout boardArea, LinearLayout verticalGroupsArea, LinearLayout addArea, TextView scoreTextView, Context context) {
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
        this.createVerticalGroupAreas();
        this.addSlotsAndTokens();
    }

    /**
     * This method clears the vertical groups area by removing every vertical group area that was added to this area.
     */
    private void clearVerticalGroupsArea() {
        for (LinearLayout verticalGroupArea : this.verticalGroupAreas) {
            verticalGroupArea.removeAllViews();
        }

//Clear the field for this class.
        this.verticalGroupAreas = new ArrayList<>();
    }

    private void createVerticalGroupAreas() {
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
            ViewActions viewActions = this;

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

                    board.addToken(competition.getParticipantForTurn(), thisVerticalGroupNumber);

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

    /**
     * This method must be used when the board has changed.
     * This method shows the board, after it has been changed.
     */
    private void boardChanged() {
        //At this time, the strategy is to clear the board area, and create it again.
        this.clearVerticalGroupsArea();
        this.createVerticalGroupAreas();
        this.addSlotsAndTokens();
    }

    @Override
    public void tokenAdded() {
        this.boardChanged();

        //Complete the turn for this participant.
        this.competition.completeTurn();

        //Determine whether this participant has increase the score, or if this board needs another turn.
        Board board = this.competition.getBoard();
        Participant scoreParticipant = board.getScoreParticipant();
        if (scoreParticipant == null) {
            //This board needs another turn.

            //Show the participant for the next turn.
            Participant waitingForParticipant = competition.getParticipantForTurn();
            this.showScoreMessage("A participant added a token. Now, it's time for " + waitingForParticipant.getName() + " to add a token to the board." + this.getScoreMessage());
        } else {
//This board has finished.

            //The board needs to be cleared.
            board.clear();

            this.boardChanged();

            this.showScoreMessage(scoreParticipant.getName() + " has added " + board.getConsecutiveNumber() + " consecutive tokens. The score for this participant has increased. " + this.getScoreMessage());
        }

        //Show the text board.
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");
        for (String string : textBoard.getLines()) {
            Log.i("200497768", string);
        }
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
        this.scoreTextView.setText(message);
    }

    @Override
    public void participantScoreIncreased() {

    }

    @Override
    public void boardCleared() {

    }
}
