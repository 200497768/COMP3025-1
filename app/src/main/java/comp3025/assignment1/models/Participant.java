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

    /**
     * This field is the name for this participant.
     */
    private String name;

    /**
     * This field is the score for this participant.
     * The competition class will increase this number when this participant has added 3 consecutive tokens.
     */
    private int score;

    /**
     * This field is the token color for tokens added by this participant.
     * The competition class will change this field, after the participant has been added to a competition.
     * APA for the numbers for this token color is Crimson.
     */
    private int tokenColor = Color.rgb(220, 20, 60);

    /**
     * This method returns the score for this participant.
     * When this participant adds enough consecutive tokens during a round, this number is increased.
     * The score is the number of rounds that this participant has been able to add enough consecutive tokens in.
     *
     * @return
     */
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

    /**
     * Creates a participant with the provided name.
     * The score for this participant will start at 0.
     *
     * @param name
     */
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
        return tokenColor;
    }

    /**
     * This method changes the background color for tokens added by this participant.
     *
     * @return
     */
    public void changeTokenColor(int tokenColor) {
        this.tokenColor = tokenColor;
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
