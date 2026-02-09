package comp3025.assignment1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.TextBoard;
import comp3025.assignment1.models.actions.ViewActions;

/**
 * This class runs after WelcomeActivity.
 *
 * @author Hao Tian
 */
public class GameActivity extends AppCompatActivity {

    //This field is the name that must be used when adding the participant to the intent.
    public static final String participantIntentName = "comp3025.assignment1.participant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Retrieve the participant from the intent that was received from WelcomeActivity.
        Intent intent = getIntent();
        Participant participant = intent.getSerializableExtra(GameActivity.participantIntentName, Participant.class);
        //This string must match the name that was used to add the participant to the intent.
        //If the string doesn't match, this method returns null, and this method won't be able to retrieve the participant.

        //Change the view to show the participant name.
        TextView goodLuckTextView = findViewById(R.id.goodLuckTextView);
        goodLuckTextView.setText("Good luck, "+participant.getName()+"!");

        LinearLayout boardArea = findViewById(R.id.boardArea);
        //This method is able to retrieve the board area that I created.

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        Competition competition = new Competition(board);
        competition.addParticipant(participant);

        ViewActions viewActions = new ViewActions(competition, boardArea, GameActivity.this);

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

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(participant.getName() + " 0, computer 0");
    }
}