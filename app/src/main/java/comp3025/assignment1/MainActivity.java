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
import comp3025.assignment1.models.Token;
import comp3025.assignment1.models.VerticalGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.

        setContentView(R.layout.game);

        System.out.println("onCreate method running.");
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

        TextBoard textBoard = new TextBoard(board);

        //Show the text board.
        Log.i("200497768", "Here's the board after I created it.");
        for (String line : textBoard.getLines("Token", "Empty")) {
            Log.i("200497768", line);
        }

        Participant participant = new Participant("Hao Tian");

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines("Token", "Empty")) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines("Token", "Empty")) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines("Token", "Empty")) {
            Log.i("200497768", line);
        }

        board.addToken(participant, 0);

        //Show the text board.
        Log.i("200497768", "Here's the board after I added a token.");
        for (String line : textBoard.getLines("Token", "Empty")) {
            Log.i("200497768", line);
        }

        List<ConsecutiveSlotGroup> consecutiveSlotGroups = board.getConsecutiveSlotGroups();
        for (ConsecutiveSlotGroup consecutiveSlotGroup : consecutiveSlotGroups) {
            Token startingToken = consecutiveSlotGroup.getStartingToken();

            Log.i("200497768", "This is a consecutive slot group starting with " + startingToken.getInformation());

            List<Token> tokens = consecutiveSlotGroup.getTokens();
            for (Token token : tokens) {
                if (token == null) {
                    Log.i("200497768", "No token in this slot.");
                } else {
                    Log.i("200497768", "A token from this consecutive slot group is " + token.getInformation());
                }
            }
        }

        Log.i("200497768", "Winner is " + board.getWinningParticipant());

//As a student, I want to be able to see both of the views that are needed for this assignment.
        //Both views are created using the method that I learned during the week 4 class.
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