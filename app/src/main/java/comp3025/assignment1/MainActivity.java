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

        //Show the text board.
        Log.i("200497768", "Here's the board after I created it.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        Participant participant = new Participant("Hao Tian");

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines()) {
            Log.i("200497768", line);
        }

        //Show the consecutive slot groups using the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getConsecutiveSlotGroups()) {
            Log.i("200497768", line);
        }

        Log.i("200497768", "The winning participant from this board is " + board.getWinningParticipant());

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