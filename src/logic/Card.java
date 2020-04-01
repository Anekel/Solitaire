package logic;

public class Card {
    private char suit;
    private boolean red;
    private int value;
    Card prevCard;
    Card nextCard;

    public Card(char suit, int value){
        setSuit(suit);
        setValue(value);
        prevCard = null;
        nextCard = null;
    }

    // Getters and Setters
    public char getSuit() {
        return suit;
    }

    public boolean isRed() {
        return red;
    }

    public int getValue() {
        return value;
    }

    private void setSuit(char suit) {
        this.suit = suit;

        if (suit == 'D' || suit == 'H') {
            setRed(true);
        }
        else setRed(false);
    }

    private void setRed(boolean red) {
        this.red = red;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        if (red) return "" + suit + value;
        else return "(" + suit + value + ")";

    }
}
