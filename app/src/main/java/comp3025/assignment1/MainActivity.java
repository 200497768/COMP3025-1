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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.
        setContentView(R.layout.game);

        Log.i("200497768", "onCreate method running.");

        LinearLayout boardArea = findViewById(R.id.boardArea);
        //This method is able to retrieve the board area that I created.

        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 3;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);
        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        Participant participant = new Participant("Hao Tian");

        //I'll add the first token to vertical group number 0.
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

        //I'll add the first token to vertical group number 0.
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


        //The vertical group number will be used to create a string that shows this number.
        int verticalGroupNumber = 0;

        //The board has been created, but the views still need to be added to the board area.
        for (VerticalGroup verticalGroup : board.getVerticalGroups()) {

            //Create an area for this vertical group, and add it to the board area.
            LinearLayout verticalGroupArea=new LinearLayout(MainActivity.this);
            verticalGroupArea.setOrientation(VERTICAL);
            //APA for creating this is (Yadav, 2019)
            int verticalGroupColor = Color.rgb(135,206,235);
            verticalGroupArea.setBackgroundColor(verticalGroupColor);
            //APA is SkyBlue.
boardArea.addView(verticalGroupArea);

            //Add a string to the vertical group area showing the number for this vertical group.
            TextView verticalGroupNumberTextView = new TextView(MainActivity.this);
            //APA for creating this view is (Yadav, 2019)
            verticalGroupNumberTextView.setText("Vertical " + verticalGroupNumber + " ");
            verticalGroupArea.addView(verticalGroupNumberTextView);

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
                    TextView emptyTextView = new TextView(MainActivity.this);
                    //APA for creating this view is (Yadav, 2019)
                    emptyTextView.setText("Empty ");
                    verticalGroupArea.addView(emptyTextView);
                } else {
                    //A token exists in this slot.
                    TextView tokenTextView = new TextView(MainActivity.this);
                    //APA for creating this view is (Yadav, 2019)
                    tokenTextView.setText("Token ");

                    //Change the background color for this token.
                    int tokenColor = Color.rgb(220, 20, 60);
                    //APA for numbers is Crimson.
                    tokenTextView.setBackgroundColor(tokenColor);

                    verticalGroupArea.addView(tokenTextView);
                }
            }

            //Tokens and slots for this vertical group have been added.
            //The vertical group number needs to be increased.
            verticalGroupNumber = verticalGroupNumber + 1;
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