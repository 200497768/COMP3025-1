package comp3025.assignment1.models;

import android.graphics.Color;

import java.util.Objects;

/**
 * This class is a participant.
 */
public class Participant {
    private String name;

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
     * This method returns the background color for tokens from this participant.
     *
     * @return
     */
    public int getColor() {
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
