package comp3025.assignment1;

import static android.widget.LinearLayout.VERTICAL;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import comp3025.assignment1.models.actions.BoardAreaActions;

/**
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart method running.");
        Log.i("200497768", "onStart method running.");
    }

    /**
     * This method changes the view.
     */
    public void wishMeLuckButtonChosen(View view) {
        //Retrieve the participant name from the view.
        EditText participantNameEditText = findViewById(R.id.participantNameEditText);
        Editable participantNameEditable = participantNameEditText.getText();

        //Create the participant model.
        Participant participant = new Participant("" + participantNameEditable);

        //Create an explicit intent that refers to GameActivity.
        Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);

        //Add the participant to the intent.
        intent.putExtra(GameActivity.participantIntentName, participant);

        startActivity(intent);
    }
}

//References
//Crimson. https://www.w3schools.com/colors/color_tryit.asp?color=Crimson
//SkyBlue. https://www.w3schools.com/colors/color_tryit.asp?color=SkyBlue
//Yadav, C. (2019). How to Dynamically Add Views into View in Android? https://www.tutorialspoint.com/how-to-dynamically-add-views-into-view-in-android