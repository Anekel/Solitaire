package DTO;

import java.io.Serializable;

public class CardDTO implements Serializable {

    private char suit;
    private int value;

    public CardDTO(){ }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
