package comp3025.assignment1.views;

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
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;
import comp3025.assignment1.models.participants.Participant;

/**
 * This class is responsible for changing board area elements when the board model changes.
 */
public class ViewActions {

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

    /**
     * This field is a class with a method that needs to run when the competition has been completed.
     * If this field is null, no method needs to run when the competition has completed.
     */
    private SuppliedMethod suppliedMethod;

    public ViewActions(Competition competition, LinearLayout boardArea, LinearLayout verticalGroupsArea, LinearLayout addArea, TextView scoreTextView, Context context, SuppliedMethod suppliedMethod) {
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

        //The supplied method field can be null if no method needs to run when the competition has completed.
        this.suppliedMethod = suppliedMethod;
    }

    /**
     * This method happens when the board has been created.
     * The class that extends this class is responsible for writing this method in order to show the board.
     */
    public void boardCreated() {
        Log.i("200497768", "The board has been created.");
        this.createVerticalGroupAreas();
        this.addSlotsAndTokens();
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
     * This method creates and adds vertical group areas to the vertical groups area.
     * An add option will also be created, allowing tokens to be added to this vertical group.
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

            //The vertical group number needs to be written this way in order for the method to access it.
            int thisVerticalGroupNumber = verticalGroupNumber;

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Add a token to the model.
                    //This method happens when a token has been added to the board.

                    //This class will cause the method from the competition class to happen.
                    //The competition class is the model, and will be responsible for changing the board to prepare it for the next round.

                    //Use the competition to add a token to the board.
                    //The competition might not add the token, depending on whether a slot is available in the vertical group.
                    competition.addToken(thisVerticalGroupNumber);

                    //Show the text board.
                    Board board = competition.getBoard();
                    TextBoard textBoard = new TextBoard(board, "Token", "Empty");
                    for (String string : textBoard.getLines()) {
                        Log.i("200497768", string);
                    }
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

                    //Determine the token color that will be used to show this token.
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
     * This method happens after a participant has added a token to the board, but not enough consecutive tokens have been added to increase the score.
     * This method shows a message explaining that another turn is needed, and that the next participant needs to add a token.
     */
    public void showNeedsAnotherTurn() {
        Participant nextTurnParticipant = competition.getTurnParticipant();
        this.showScoreMessage("A participant added a token. Now, " + nextTurnParticipant.getName() + " needs to add a token to the board. " + this.getScoreMessage());
    }

    /**
     * This method shows a message explaining that a round has been completed.
     * This method happens when a participant has increased the score.
     * A participant has added a token to the board, causing enough consecutive tokens for the round to finish.
     * This is the score participant.
     * The score of the score participant has been increased.
     * This method can retrieve the score participant from the board.
     */
    public void showRoundCompleted() {
        //Retrieve the score participant.
        //The score participant is needed to show information like name and score.
        Board board = this.competition.getBoard();
        Participant scoreParticipant = board.getScoreParticipant();

        //A score participant must exist.
        if (scoreParticipant == null) {
            throw new IllegalStateException();
        }

        //Add an option to start the next round.
        Button nextRoundButton = new Button(this.context);
        nextRoundButton.setText("Next round");
        Competition competition = this.competition;
        nextRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Complete the round by using the method from the competition class.
                //This needs to happen when the view actions has finished showing the fact that the round has completed.
                //This method will show a message, clear the board, and start the next round.
                competition.completeRound();
            }
        });

        //Add the next round option to the board area.
        this.boardArea.addView(nextRoundButton);

        this.showScoreMessage(scoreParticipant.getName() + " has added " + board.getConsecutiveNumber() + " consecutive tokens. The score for this participant has increased. " + this.getScoreMessage());
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
     * This method runs when the competition has finished.
     * This method causes a similar method from the GameActivity class to run.
     * This is needed because I'm not able to access the startActivity method from this class.
     * The startActivity is needed in order to use the intent.
     */
    public void competitionCompleted() {
        //Retrieve the completed method field, and run the completed method.
        this.suppliedMethod.runSuppliedMethod();
    }
}
