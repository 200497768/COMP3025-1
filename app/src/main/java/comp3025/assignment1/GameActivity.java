package comp3025.assignment1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.ComputerParticipant;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.TextBoard;
import comp3025.assignment1.models.actions.CreatedViewActions;
import comp3025.assignment1.models.actions.SuppliedMethod;

/**
 * This class runs after WelcomeActivity.
 *
 * @author Hao Tian
 */
public class GameActivity extends AppCompatActivity {

    private Competition competition;
    private Board board;
    private Participant participant;

    /**
     * This field is the name that must be used when adding the participant to the intent.
     */
    public static final String participantIntentName = "comp3025.assignment1.participant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Retrieve the participant from the intent that was received from WelcomeActivity.
        Intent intent = getIntent();
        Participant participant = intent.getSerializableExtra(GameActivity.participantIntentName, Participant.class);
        this.participant = participant;
        //This string must match the name that was used to add the participant to the intent.
        //If the string doesn't match, this method returns null, and this method won't be able to retrieve the participant.

        //Change the view to show the participant name.
        TextView goodLuckTextView = findViewById(R.id.goodLuckTextView);
        goodLuckTextView.setText("Good luck, "+participant.getName()+"!");

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);
        this.board = board;

        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        int maximumScore = 3;
        Competition competition = new Competition(board, maximumScore);
        this.competition = competition;

        //Add the participant to the competition.
        competition.addParticipant(participant);

        //Add the computer participant to the competition.
        Participant computerParticipant = new ComputerParticipant();
        competition.addParticipant(computerParticipant);

        LinearLayout boardArea = findViewById(R.id.boardArea);
        LinearLayout addArea = findViewById(R.id.addArea);
        LinearLayout verticalGroupsArea = findViewById(R.id.verticalGroupsArea);
        TextView scoreTextView = findViewById(R.id.scoreTextView);

        //Create the view actions.
        GameActivity gameActivity = this;
        SuppliedMethod completedMethod = new SuppliedMethod() {
            @Override
            public void runSuppliedMethod() {
                //This method runs when the competition has been completed.
                //When the competition has completed, run the competitionCompleted method from the GameActivity class.
                gameActivity.competitionCompleted();
            }
        };
        CreatedViewActions viewActions = new CreatedViewActions(competition, boardArea, addArea, verticalGroupsArea, scoreTextView, GameActivity.this, completedMethod);

        //Provide the view actions to the competition.
        competition.changeViewActions(viewActions);

        //Add elements to the board area.
        viewActions.boardCreated();
        //The board is supposed to cause actions to happen, but since the board has been created, I'll write it from the onCreate method.

        //Show whether a participant has won.
        LinearLayout winningParticipantArea = findViewById(R.id.winningParticipantArea);
        TextView winningParticipantTextView = new TextView(GameActivity.this);
        //APA for creating this view is (Yadav, 2019)

        Participant winningParticipant = board.getScoreParticipant();
        if (winningParticipant == null) {
            winningParticipantTextView.setText("No score participant at this time.");
        } else {
            winningParticipantTextView.setText("Score participant is " + winningParticipant.getName());
        }
        winningParticipantArea.addView(winningParticipantTextView);

        int boardColor = Color.rgb(135, 206, 235);
        boardArea.setBackgroundColor(boardColor);
        //APA is SkyBlue.
    }

    /**
     * This method is for when the option to share the score has been chosen.
     * This method retrieves information, including the score, and uses an implicit intent.
     *
     * @param view
     */
    public void shareScoreChosen(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "I'm " + participant.getName() + ", and my score in Connect " + this.board.getConsecutiveNumber() + " is " + participant.getScore() + ".");
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, "Score");
        startActivity(shareIntent);

        //APA will be the week 5 class.
    }

    /**
     * This method runs when the competition has finished.
     * When this happens, this method needs to show all of the scores for this competition using CompletedActivity.
     * APA for this method will be the week 5 class.
     */
    public void competitionCompleted() {
        //Create an explicit intent that refers to CompletedActivity.
        Intent intent = new Intent(GameActivity.this, CompletedActivity.class);

        //Add the competition to the intent.
        Objects.requireNonNull(this.competition);
        intent.putExtra(CompletedActivity.competitionIntentName, this.competition);

        startActivity(intent);
    }
}