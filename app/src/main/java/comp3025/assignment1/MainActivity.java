package comp3025.assignment1;

import static android.widget.LinearLayout.VERTICAL;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.ConsecutiveSlotGroup;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.SlotNumbers;
import comp3025.assignment1.models.TextBoard;
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;
import comp3025.assignment1.models.actions.BoardAreaActions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.
        setContentView(R.layout.welcome);

        Log.i("200497768", "onCreate method running.");

        LinearLayout boardArea = findViewById(R.id.boardArea);
        //This method is able to retrieve the board area that I created.

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        Participant participant = new Participant("Hao Tian");

        BoardAreaActions boardAreaActions = new BoardAreaActions(board, boardArea, MainActivity.this);

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
        TextView winningParticipantTextView = new TextView(MainActivity.this);
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

        int boardColor = Color.rgb(135,206,235);
        boardArea.setBackgroundColor(boardColor);
        //APA is SkyBlue.

//As a student, I want to be able to see both of the views that are needed for this assignment.
        //Both views are created using the method that I learned during the week 4 class.
        Log.i("200497768", "onCreate method completed.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart method running.");
        Log.i("200497768", "onStart method running.");
    }
}

//References
//Crimson https://www.w3schools.com/colors/color_tryit.asp?color=Crimson
//SkyBlue https://www.w3schools.com/colors/color_tryit.asp?color=SkyBlue

//Yadav, C. (2019). How to Dynamically Add Views into View in Android? https://www.tutorialspoint.com/how-to-dynamically-add-views-into-view-in-android