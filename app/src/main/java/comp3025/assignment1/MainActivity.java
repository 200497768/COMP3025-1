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

import comp3025.assignment1.models.Board;
import comp3025.assignment1.models.Participant;
import comp3025.assignment1.models.Token;

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

        Board board = new Board(5, 4);

        Participant participant = new Participant("Hao Tian");
        board.addToken(participant, 0);
        board.addToken(participant, 0);
        board.addToken(participant, 0);
        board.addToken(participant, 0);
        Log.i("200497768", "Winner is " + board.getWinningParticipant());

//As a student, I want to be able to see both of the views that are needed for this assignment.
        //Both views are created using the method that I learned during the week 4 class.

        //I changed this code using the library computer.
        //If I'm able to access this line in the repository, this means I'm able to use the library computer for this assignment.
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