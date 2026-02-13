package comp3025.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.participants.Participant;
import comp3025.assignment1.views.SuppliedMethod;
import comp3025.assignment1.views.ViewActions;


/**
 * The GameActivity class shows the board, and allows tokens to be added.
 * This class runs after WelcomeActivity.
 * The onCreate method needs a competition, including a board and participants.
 *
 * @author Hao Tian
 */
public class GameActivity extends AppCompatActivity {

    /**
     * This field is the competition that was created by WelcomeActivity, and received using the intent.
     */
    private Competition competition;

    /**
     * This field is the name that must be used when adding the competition to the intent.
     */
    public static final String competitionIntentName = "comp3025.assignment1.competition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Retrieve the competition from the intent that was received from WelcomeActivity.
        Intent intent = getIntent();
        Competition competition = intent.getSerializableExtra(GameActivity.competitionIntentName, Competition.class);
        Objects.requireNonNull(competition);
        this.competition = competition;
        //This string must match the name that was used to add the participant to the intent.
        //If the string doesn't match, this method returns null, and this method won't be able to retrieve the participant.

        //Change the good luck string to show the names of every participant.
        TextView goodLuckTextView = findViewById(R.id.goodLuckTextView);
        goodLuckTextView.setText(this.getGoodLuckMessage());

        //Retrieve the areas that are needed for the view actions.
        LinearLayout boardArea = findViewById(R.id.boardArea);
        LinearLayout addArea = findViewById(R.id.addArea);
        LinearLayout verticalGroupsArea = findViewById(R.id.verticalGroupsArea);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        GameActivity gameActivity = this;
        SuppliedMethod completedMethod = new SuppliedMethod() {
            @Override
            public void runSuppliedMethod() {
                //This method runs when the competition has been completed.
                //When the competition has completed, run the competitionCompleted method from the GameActivity class.
                gameActivity.competitionCompleted();
            }
        };

        //Create the view actions.
        ViewActions viewActions = new ViewActions(competition, boardArea, verticalGroupsArea, addArea, scoreTextView, GameActivity.this, completedMethod);

        //Provide the view actions to the competition.
        competition.changeViewActions(viewActions);

        //Add elements to the board area.
        viewActions.boardCreated();
        //The board is supposed to cause actions to happen, but since the board has been created, I'll write it from the onCreate method.
    }

    /**
     * This method returns the good luck message as a string.
     * The good luck message includes the name for every participant.
     *
     * @return
     */
    private String getGoodLuckMessage() {
        //Retrieve the participants for this competition.
        List<Participant> participants = this.competition.getParticipants();

        //Create the good luck message by starting with this string.
        String message = "Good luck, ";

        //The message needs to be different, depending on the number of participants.
        //If 2 participants exist, the message will combine the names using a string.
        //If 3 or more participants exist, the message will go through all of the participants.
        if (participants.size() == 2) {
            //Retrieve both participants.
            Participant participant = participants.get(0);
            Participant anotherParticipant = participants.get(1);

            //Create the rest of the message using the names of both participants.
            message = message + participant.getName() + " and " + anotherParticipant.getName();
        } else {
            //Go through every participant, not including the last participant.
            for (int number = 0; number < participants.size() - 1; number = number + 1) {
                //Add the name for this participant to the message.
                Participant participant = participants.get(number);
                message = message + participant.getName() + ", ";
            }

            //Add the name for the last participant.
            Participant lastParticipant = participants.get(participants.size() - 1);
            message = message + "and " + lastParticipant.getName();
        }

        message = message + "!";

        return message;
    }


    /**
     * This method is for when the option to share the score has been chosen.
     * This method retrieves information, including the score, and uses an implicit intent.
     * APA will be the week 5 class.
     *
     * @param view
     */
    public void shareScoreChosen(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);

        String text = this.getShareScoreText();

        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, "Score");
        startActivity(shareIntent);
    }

    /**
     * This method produces the share score text for the turn participant.
     *
     * @return
     */
    private String getShareScoreText() {
        //Retrieve the turn participant.
        //The turn participant will be used to retrieve the name and score.
        Participant turnParticipant = this.competition.getTurnParticipant();

        //Retrieve the board.
        //The board will be used to determine the number of consecutive tokens needed to increase the score.
        Board board = this.competition.getBoard();

        //Retrieve the number of participants in this competition.
        List<Participant> participants = this.competition.getParticipants();
        int numberOfParticipants = participants.size();

        //Determine the number of other participants in this competition, not including the turn participant.
        int numberOfOtherParticipants = numberOfParticipants - 1;

        //Create the string.
        String nameString = "I'm " + turnParticipant.getName() + ", and I'm participating in a Connect " + board.getConsecutiveNumber() + " competition ";
        String otherParticipantsString = "with " + numberOfOtherParticipants + " other participants. ";

        if (numberOfOtherParticipants == 1) {
            otherParticipantsString = "with 1 other participant. ";
        }

        String scoreString = "At this time, my score is " + turnParticipant.getScore() + ".";

        String text = nameString + otherParticipantsString + scoreString;
        return text;
    }

    /**
     * This method runs when the competition has finished.
     * When this happens, this method needs to show all of the scores for this competition using CompletedActivity.
     * APA for this method will be the week 5 class.
     */
    public void competitionCompleted() {
        //Create an explicit intent that refers to CompletedActivity.
        Intent intent = new Intent(GameActivity.this, CompletedActivity.class);

        //Add the competition to the intent.
        Objects.requireNonNull(this.competition);
        intent.putExtra(CompletedActivity.competitionIntentName, this.competition);

        startActivity(intent);
    }
}