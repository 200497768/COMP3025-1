package comp3025.assignment1.models;

import java.util.Objects;

/**
 * This class is a participant.
 */
public class Participant {
    private String name;

    /**
     * This method returns the name of this participant.
     *
     * @return
     */
    public String getName() {
        return name;
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
