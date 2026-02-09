package comp3025.assignment1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.TextBoard;
import comp3025.assignment1.models.actions.BoardAreaActions;

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

        BoardAreaActions boardAreaActions = new BoardAreaActions(board, boardArea, GameActivity.this);

        //Add elements to the board area.
        boardAreaActions.boardCreated();
        //The board is supposed to cause actions to happen, but since the board has been created, I'll write it from the onCreate method.

        //I'll add a token to vertical group number 0, and show the text board.
        board.addToken(participant, 0);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 0.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //I'll add a token to vertical group number 1, and show the text board.
        board.addToken(participant, 1);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 1.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //I'll add a token to vertical group number 1, and show the text board.
        board.addToken(participant, 1);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 1.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //I'll add a token to vertical group number 1, and show the text board.
        board.addToken(participant, 1);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 1.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //Show whether a participant has won.
        LinearLayout winningParticipantArea = findViewById(R.id.winningParticipantArea);
        TextView winningParticipantTextView = new TextView(GameActivity.this);
        //APA for creating this view is (Yadav, 2019)

        Participant winningParticipant = board.getWinningParticipant();
        if (winningParticipant == null) {
            winningParticipantTextView.setText("No winning participant at this time.");
        } else {
            winningParticipantTextView.setText("Winning participant is " + winningParticipant.getName());
        }
        winningParticipantArea.addView(winningParticipantTextView);

        Log.i("200497768", "Here's the board after I created it.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //Show the consecutive slot groups using the text board.
        Log.i("200497768", "Here's the consecutive slot groups.");
        for (String line : textBoard.getConsecutiveSlotGroups()) {
            Log.i("200497768", line);
        }

        int boardColor = Color.rgb(135, 206, 235);
        boardArea.setBackgroundColor(boardColor);
        //APA is SkyBlue.

    }
}