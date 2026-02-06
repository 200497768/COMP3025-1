package comp3025.assignment1;

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
        //APA for creating this TextView is (Yadav, 2019)

        createdTextView.setText("This string was created using the onCreate method.");
        int createdTextViewColor = Color.rgb(220, 20, 60);
        //APA for numbers is Crimson.
        createdTextView.setBackgroundColor(createdTextViewColor);

        boardArea.addView(createdTextView);

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        //The board has been created, but the views still need to be added to the board area.
        for (VerticalGroup verticalGroup : board.getVerticalGroups()) {

            //Add a string to the board area showing the number for this vertical group.
            TextView verticalGroupNumberTextView = new TextView(MainActivity.this);
            verticalGroupNumberTextView.setText("Vertical group 0");
            boardArea.addView(verticalGroupNumberTextView);

            //Create an area for this vertical group, and add it to the board area.
            LinearLayout verticalGroupArea=new LinearLayout(MainActivity.this);
            int verticalGroupColor = Color.rgb(135,206,235);
            verticalGroupArea.setBackgroundColor(verticalGroupColor);
            //APA is SkyBlue.
boardArea.addView(verticalGroupArea);

            for (Token token : verticalGroup.getTokens()) {
                //Create an element for this slot, depending on whether it has a token, or if it's empty.

                if (token == null) {
                    //This slot is empty.
                    TextView emptyTextView = new TextView(MainActivity.this);
                    emptyTextView.setText("Empty");
                    boardArea.addView(emptyTextView);
                } else {
                    //A token exists in this slot.
                    TextView tokenTextView = new TextView(MainActivity.this);
                    tokenTextView.setText("Token");

                    //Change the background color for this token.
                    int tokenColor = Color.rgb(220, 20, 60);
                    //APA for numbers is Crimson.
                    tokenTextView.setBackgroundColor(tokenColor);

                    boardArea.addView(tokenTextView);
                }
            }


        }

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