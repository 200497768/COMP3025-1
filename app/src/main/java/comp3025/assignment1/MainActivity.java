package comp3025.assignment1;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.
        setContentView(R.layout.game);

        Log.i("200497768", "onCreate method running.");

        LinearLayout boardArea = findViewById(R.id.boardArea);
        //This method is able to retrieve the board area that I created.

        TextView createdTextView = new TextView(MainActivity.this);
        //(Yadav, 2019)

        createdTextView.setText("This string was created using the onCreate method.");

        boardArea.addView(createdTextView);

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;

        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);

        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        Log.i("200497768", "Here's the board after I created it.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        Participant participant = new Participant("Hao Tian");

        //I'll add a token to vertical group number 0.
        board.addToken(participant, 0);

        Log.i("200497768", "Here's the board after I added a token to vertical group number 0.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        if (board.getWinningParticipant() == null) {
            Log.i("200497768", "No participant has won at this time.");
        } else {
            Log.i("200497768", "A participant has won. The winning participant is " + board.getWinningParticipant());
        }

        //I'll add a token to vertical group number 0.
        board.addToken(participant, 0);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 0.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        if (board.getWinningParticipant() == null) {
            Log.i("200497768", "No participant has won at this time.");
        } else {
            Log.i("200497768", "A participant has won. The winning participant is " + board.getWinningParticipant());
        }

        //I'll add a token to vertical group number 0.
        board.addToken(participant, 0);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 0.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        if (board.getWinningParticipant() == null) {
            Log.i("200497768", "No participant has won at this time.");
        } else {
            Log.i("200497768", "A participant has won. The winning participant is " + board.getWinningParticipant());
        }

        //I'll add a token to vertical group number 1.
        board.addToken(participant, 1);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 1.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        if (board.getWinningParticipant() == null) {
            Log.i("200497768", "No participant has won at this time.");
        } else {
            Log.i("200497768", "A participant has won. The winning participant is " + board.getWinningParticipant());
        }

        //I'll add a token to vertical group number 0.
        board.addToken(participant, 0);
        Log.i("200497768", "Here's the board after I added a token to vertical group number 0.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        if (board.getWinningParticipant() == null) {
            Log.i("200497768", "No participant has won at this time.");
        } else {
            Log.i("200497768", "A participant has won. The winning participant is " + board.getWinningParticipant());
        }

        //Show the consecutive slot groups using the text board.
        Log.i("200497768", "Here's the consecutive slot groups.");
        for (String line : textBoard.getConsecutiveSlotGroups()) {
            Log.i("200497768", line);
        }

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

//Yadav, C. (2019). How to Dynamically Add Views into View in Android? https://www.tutorialspoint.com/how-to-dynamically-add-views-into-view-in-android