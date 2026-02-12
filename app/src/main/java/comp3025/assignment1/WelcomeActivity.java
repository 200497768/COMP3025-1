package comp3025.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.participants.ComputerParticipant;
import comp3025.assignment1.models.participants.Participant;

/**
 * The WelcomeActivity class is responsible for showing an area to input the participant name.
 * This class runs when the code has started running.
 *
 * @author Hao Tian
 */
public class WelcomeActivity extends AppCompatActivity {

    /**
     * This field is the competition that's being created.
     */
    private Competition competition;

    /**
     * This field is all of the participants that have been added.
     * When creating the wish me luck option has been chosen, the competition will be created.
     * At that time, all of the participants from this field will be added to the competition.
     */
    private List<Participant> participants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method causes the code to run the welcome view.
        setContentView(R.layout.activity_welcome);

        //The remaining code needs the competition in order to run.

        //Create the board.
        int numberOfVerticalGroups = 5;
        int verticalGroupCapacity = 4;
        int consecutiveNumber = 30;
        Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);

        this.competition = new Competition(board, 3);

        //Change the number of consecutive tokens needed.
        TextView welcomeTextView=findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome to Connect 3.");
    }

    /**
     * This method refers to multiple methods that must run when the participants have changed.
     */
    private void participantsChanged() {
        //Create the participants area again.
        this.createParticipantsArea();

        //Change the text to wish the participants luck.
        this.changeWishLuck();
    }

    /**
     * This method creates the participant areas.
     * This method uses all of the participants from the field.
     */
    private void createParticipantsArea() {
        //Retrieve the participants area.
        LinearLayout participantsArea = findViewById(R.id.participantsArea);

        //Clear the participants area, in order to remove any existing participant areas.
        participantsArea.removeAllViews();

        //Go through the field, and create a participant area for every participant.
        for (Participant participant : this.participants) {
            //Create a participant area.
            //The participant area will be added to the participants area.
            LinearLayout participantArea = new LinearLayout(WelcomeActivity.this);

            //Add the participant area to the participants area.
            participantsArea.addView(participantArea);

            //Create the name.
            TextView nameTextView = new TextView(WelcomeActivity.this);
            nameTextView.setText(participant.getName());

            //Add the name to the participant area.
            participantArea.addView(nameTextView);

            //Create the remove option.
            Button removeButton = new Button(WelcomeActivity.this);
            removeButton.setText("Remove participant");
            List<Participant> participants = this.participants;
            WelcomeActivity welcomeActivity = this;
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Remove the participant.
                    participants.remove(participant);

                    //The participants have changed.
                    welcomeActivity.participantsChanged();
                }
            });

            //Add the remove option to the participant area.
            participantArea.addView(removeButton);
        }
    }

    /**
     * This method changes the text for the option to start the competition.
     * The text for this option depends on the number of participants.
     * If only single participant has been added, not including the computer participant, the text will wish only the participant luck.
     * If multiple participants have been added, the text will wish them luck.
     */
    private void changeWishLuck() {
        Button wishMeLuckButton = findViewById(R.id.wishMeLuckButton);

        //Determine the number of participants that have been added that aren't the computer participant.
        int numberOfPersonChoosingParticipants = 0;
        for (Participant participant : this.participants) {
            if (participant.getPersonChoosing()) {
                //This participant isn't a computer participant.
                numberOfPersonChoosingParticipants = numberOfPersonChoosingParticipants + 1;
            }
        }

        //Check the number of participants that aren't the computer participant.
        if (numberOfPersonChoosingParticipants == 1) {
            //Only a single participant has been added, not including the computer participant.
            wishMeLuckButton.setText("Wish me luck");
        } else {
            wishMeLuckButton.setText("Wish us luck");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart method running.");
        Log.i("200497768", "onStart method running.");
    }

    /**
     * This method adds a participant to the list.
     * This method runs when the option to add a participant has been chosen.
     * This method needs to retrieve the name for this participant, create a new participant, and add the participant to the list.
     * The list is a field from this class.
     *
     * @param view
     */
    public void addParticipantChosen(View view) {
        //Retrieve the participant name from the view.
        EditText participantNameEditText = findViewById(R.id.participantNameEditText);
        Editable participantNameEditable = participantNameEditText.getText();

        //Create a participant.
        Participant participant = new Participant("" + participantNameEditable);

        //Add the participant to the list.
        this.participants.add(participant);

        //The participants have changed.
        this.participantsChanged();
    }

    /**
     * This method changes the view.
     * APA for this method will be the week 5 class.
     */
    public void wishMeLuckButtonChosen(View view) {
        //Check whether a participant has been added before starting the competition.
        //The competition can only start if a participant has been added, not including the computer participant.
        //If only a single participant exists, the computer participant will be added.
        //If multiple participants exist, the computer participant won't be added.
        if (this.participants.size() > 0) {
            //Create the board.
            int numberOfVerticalGroups = 5;
            int verticalGroupCapacity = 4;
            int consecutiveNumber = 3;
            Board board = new Board(numberOfVerticalGroups, verticalGroupCapacity, consecutiveNumber);

            //Create the competition.
            Competition competition = new Competition(board, 3);

            //Add the participants that were created by this class to the competition.
            for (Participant participant : this.participants) {
                competition.addParticipant(participant);
            }

            //Add the computer participant to the competition, if only 1 participant was added.
            if (this.participants.size() == 1) {
                Participant computerParticipant = new ComputerParticipant(competition);
                competition.addParticipant(computerParticipant);
            }

            //Create an explicit intent that refers to GameActivity.
            Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);

            //Add the competition to the intent.
            intent.putExtra(GameActivity.competitionIntentName, competition);

            startActivity(intent);
        }
    }

}

//References
//Crimson. https://www.w3schools.com/colors/color_tryit.asp?color=Crimson
//Gold. https://www.w3schools.com/colors/color_tryit.asp?color=Gold
//SkyBlue. https://www.w3schools.com/colors/color_tryit.asp?color=SkyBlue
//Yadav, C. (2019). How to Dynamically Add Views into View in Android? https://www.tutorialspoint.com/how-to-dynamically-add-views-into-view-in-android