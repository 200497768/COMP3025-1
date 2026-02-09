package comp3025.assignment1.models;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is a participant that has been added to a competition.
 * A participant includes a name, and a score.
 * After creating a participant, the participant needs to be added to a competition.
 * The score for a participant can be increased during a competition when the participant has added enough consecutive tokens.
 *
 * @author Hao Tian
 */
public class Participant implements Serializable {
    private String name;
    private int score;

    public int getScore() {
        return score;
    }

    /**
     * This method increases the score for this participant by 1.
     */
    public void increaseScore() {
        this.score = this.score + 1;
    }

    /**
     * This field is needed for Serializable.
     */
    private static final long serialVersionUID = 1;

    public Participant(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * This method returns the name of this participant.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the background color for tokens added by this participant.
     *
     * @return
     */
    public int getTokenColor() {
        int tokenColor = Color.rgb(220, 20, 60);
        //APA for numbers is Crimson.

        return tokenColor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
