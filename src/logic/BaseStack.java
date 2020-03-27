package logic;

import java.util.Stack;

public class BaseStack {
    private char suit;
    private Stack<Card> cards = new Stack<>();

    public BaseStack(Card ace){
        setSuit(ace.getSuit());
        push(ace);
    }

    public void push(Card card){
        cards.push(card);
    }

    public Card pop(){
        return cards.pop();
    }

    // Getters and Setters
    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

}
