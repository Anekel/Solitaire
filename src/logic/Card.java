package logic;

public class Card {
    private char suit;
    private boolean color; //TODO better naming?
    private int value;
    Card nextCard;

    public Card(char suit, int value){
        setSuit(suit);
        setValue(value);
        nextCard = null;
    }

    // Getters and Setters
    public char getSuit() {
        return suit;
    }

    public boolean isColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    private void setSuit(char suit) {
        this.suit = suit;

        if (suit == 'D' || suit == 'H') {
            setColor(true);
        }
        else setColor(false);
    }

    private void setColor(boolean color) {
        this.color = color;
    }

    private void setValue(int value) {
        this.value = value;
    }
}
