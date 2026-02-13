package comp3025.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import comp3025.assignment1.models.Competition;
import comp3025.assignment1.models.participants.Participant;

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

    /**
     * This field is the competition that was retrieved from the intent.
     * This class is responsible for showing the scores for every participant in this competition.
     */
    private Competition competition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_completed);

        //Retrieve the participant from the intent that was received from WelcomeActivity.
        Intent intent = getIntent();
        Competition competition = intent.getSerializableExtra(CompletedActivity.competitionIntentName, Competition.class);
        Objects.requireNonNull(competition);
        this.competition = competition;
        //This string must match the name that was used to add the competition to the intent.
        //If the string doesn't match, this method returns null, and this method won't be able to retrieve the competition.

        //Retrieve the winning participant.
        Participant winningParticipant = this.competition.getWinningParticipant();

        //Show the winning participant.
        TextView winningParticipantTextView = findViewById(R.id.winningParticipantTextView);
        winningParticipantTextView.setText("Congratulations, " + winningParticipant.getName() + ". You've won the competition.");

        //Add the participants, scores, and share score options.
        LinearLayout scoresArea = findViewById(R.id.scoresArea);
        for (Participant participant : competition.getParticipants()) {
            //Create a participant area.
            //The name, score, and share score option for this participant will be added to this participant area.
            //The participant area will be added to the scores area.
            LinearLayout participantArea = new LinearLayout(CompletedActivity.this);

            //Add the participant area to the scores area.
            scoresArea.addView(participantArea);

            //Create the name.
            TextView nameTextView = new TextView(CompletedActivity.this);
            nameTextView.setText(participant.getName());

            //Add the name to the participant area.
            participantArea.addView(nameTextView);

            //Create the score.
            TextView scoreTextView = new TextView(CompletedActivity.this);
            scoreTextView.setText("Score " + participant.getScore());

            //Add the score to the participant area.
            participantArea.addView(scoreTextView);

            //Add the share score option.
            Button shareScoreButton = new Button(CompletedActivity.this);
            shareScoreButton.setText("Share score");
            CompletedActivity completedActivity = this;
            shareScoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Share the score for this participant.
                    //The method to share the score needs the participant that was chosen.
                    //The competition doesn't need to be provided because it has been written as a field for the CompletedActivity class.
                    completedActivity.shareScoreChosen(participant);
                }
            });

            //Add the share score option to the participant area.
            participantArea.addView(shareScoreButton);
        }
    }

    /**
     * This method is for when the option to share the score has been chosen.
     * This method retrieves information, including the score, and uses an implicit intent.
     * APA will be the week 5 class.
     */
    public void shareScoreChosen(Participant participant) {
        String text = "I'm " + participant.getName() + ", and I've completed this competition with a score of " + participant.getScore() + ".";

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(intent, "Score");
        startActivity(shareIntent);
    }
}