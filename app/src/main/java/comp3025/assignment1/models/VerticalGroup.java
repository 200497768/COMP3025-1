package comp3025.assignment1.models;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A vertical group is a vertical line in the board that can include multiple tokens.
 * The vertical group is responsible for determining order, since the tokens fall when added to a vertical line in the board.
 *
 * @author Hao Tian
 */
public class VerticalGroup {

    //This field is the tokens in this ordered token group.
    private List<Token> tokens=new ArrayList<>();

    /**
     * This field is the views that show the slots and tokens for this vertical group.
     * This field is the views that are used to show the vertical group in this board.
     */
    private List<View> views = new ArrayList<>();

    public VerticalGroup(int verticalGroupNumber, int capacity) {
        this.verticalGroupNumber = verticalGroupNumber;
        this.capacity = capacity;
    }

    private int capacity;

    //This field is the vertical group number of this vertical group when it has been added to a board.
    //This field is used by a method that produces the slot numbers for tokens being added to this vertical group.
    //This field isn't needed if this vertical group doesn't exist in a board.
    //If this number isn't correct, the method that produces slot numbers must not be used.
    private int verticalGroupNumber;

    /**
     * This method returns a token from this vertical group.
     * This method only allows accessing a slot with a token.
     * If a token hasn't been added to a slot, this method can't be used to access that slot.
     * This method won't return null if a token hasn't been added to the slot.
     * If null is needed for a slot with no token, use the method from the board class.
     *
     * @return
     */
    public Token getToken(int tokenNumber) {
        if (tokenNumber < 0) {
            throw new IllegalArgumentException();
        }

        if (tokenNumber >= this.tokens.size()) {
            throw new IllegalArgumentException();
        }

        Token token = this.tokens.get(tokenNumber);
        return token;
    }

    /**
     * This method returns the tokens from this vertical group.
     * In other words, this method returns tokens with the same vertical number.
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * This method returns the tokens from this vertical group, including slots.
     */
    public List<Token> getSlots() {
        List<Token> slots = new ArrayList<>();

        //The tokens that exist in this vertical group need to be added.
        for (Token token : this.tokens) {
            slots.add(token);
        }

        //In addition, the slots need to be added until the number of tokens and slots combined is the capacity of this vertical group.
        while (slots.size() < this.capacity) {
            slots.add(null);
        }

        return slots;
    }
    /**
     * This method returns whether a slot is still available in this vertical group.
     * If a slot is available, a token can be added using another method from this class.
     * If a slot isn't available, a token can't be added.
     *
     * @return
     */
    public boolean getSlotAvailable() {
        return this.tokens.size() < this.capacity;
    }

    /**
     * This method adds a token to this vertical group.
     * In other words, this method adds a token to this vertical line.
     * This ordered token group will be responsible for determining the vertical number for the token that was added.
     * The vertical number can't be chosen when using this method, since the token falls in the board.
     *
     * @param token
     */
    public void addToken(Token token) {
        Objects.requireNonNull(token);

        if (!this.getSlotAvailable()) {
            throw new IllegalStateException();
        }

        this.tokens.add(token);
    }

    /**
     * This method returns the number of tokens that have been added to this vertical group.
     *
     * @return
     */
    public int getNumberAdded() {
        return this.tokens.size();
    }

    /**
     * This method returns the slot numbers for the token that will be added to this vertical group.
     *
     * @return
     */
    public SlotNumbers getNextTokenSlotNumbers() {
        //In order to create a token, the slot numbers of the token in the board must be provided to the token class.
        //The slot numbers includes the vertical group number and token number.
        //This method is provided with the vertical group number, but still needs to determine the token number.

        //Create the slot numbers for the token that will be created.
        //This determines the token number of the token that this method will add.
        //The token number can be determined by checking the number of tokens that have been added to this vertical group.
        int tokenNumber = this.getNumberAdded();

        SlotNumbers slotNumbers = new SlotNumbers(this.verticalGroupNumber, tokenNumber);
        return slotNumbers;
    }


    public List<View> getViews() {
        return this.views;
    }

    public void clearViews() {
        this.views = new ArrayList<>();
    }
}
