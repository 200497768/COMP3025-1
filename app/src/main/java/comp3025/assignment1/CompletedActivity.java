package comp3025.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import comp3025.assignment1.models.Competition;

/**
 * This class shows the score for every participant in this competition.
 * This class runs when the score for any participant becomes 3.
 * This class runs after GameActivity.
 *
 * @author Hao Tian
 */
public class CompletedActivity extends AppCompatActivity {

    /**
     * This field is the name that must be used when adding the competition to the intent.
     */
    public static final String competitionIntentName = "comp3025.assignment1.competition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_completed);

        //Retrieve the participant from the intent that was received from WelcomeActivity.
        Intent intent = getIntent();
        Competition competition = intent.getSerializableExtra(CompletedActivity.competitionIntentName, Competition.class);
        this.competition = competition;
        //This string must match the name that was used to add the competition to the intent.
        //If the string doesn't match, this method returns null, and this method won't be able to retrieve the competition.

    }

    /**
     * This method is for when the option to share the score has been chosen.
     * This method retrieves information, including the score, and uses an implicit intent.
     *
     * @param view
     */
    public void shareScoreChosen(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "I'm " + participant.getName() + ", and my score in Connect " + this.board.getConsecutiveNumber() + " is " + participant.getScore() + ".");
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, "Score");
        startActivity(shareIntent);

        //APA will be the week 5 class.
    }
}