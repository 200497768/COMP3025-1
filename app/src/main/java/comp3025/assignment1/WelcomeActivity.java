package comp3025.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.FirstAvailableComputerParticipant;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.TextBoard;

/**
 * The WelcomeActivity class is responsible for showing an area to input the participant name.
 * This class runs when the code has started running.
 *
 * @author Hao Tian
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.
        setContentView(R.layout.activity_welcome);

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart method running.");
        Log.i("200497768", "onStart method running.");
    }

    /**
     * This method changes the view.
     * APA for this method will be the week 5 class.
     */
    public void wishMeLuckButtonChosen(View view) {
        //Retrieve the participant name from the view.
        EditText participantNameEditText = findViewById(R.id.participantNameEditText);
        Editable participantNameEditable = participantNameEditText.getText();

        //Create a participant.
        Participant participant = new Participant("" + participantNameEditable);

        //Create the board.
        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 30;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);

        TextBoard textBoard = new TextBoard(board, "Token", "Empty");

        //Create the competition.
        int maximumScore = 3;
        Competition competition = new Competition(board, maximumScore);

        //Add the participant to the competition.
        competition.addParticipant(participant);

        //Add the computer participant to the competition.
        Participant computerParticipant = new FirstAvailableComputerParticipant(competition);
        competition.addParticipant(computerParticipant);


        //Create an explicit intent that refers to GameActivity.
        Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);

        //Add the competition to the intent.
        intent.putExtra(GameActivity.competitionIntentName, competition);

        startActivity(intent);
    }
}

//References
//Crimson. https://www.w3schools.com/colors/color_tryit.asp?color=Crimson
//Gold. https://www.w3schools.com/colors/color_tryit.asp?color=Gold
//SkyBlue. https://www.w3schools.com/colors/color_tryit.asp?color=SkyBlue
//Yadav, C. (2019). How to Dynamically Add Views into View in Android? https://www.tutorialspoint.com/how-to-dynamically-add-views-into-view-in-android