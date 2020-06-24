package logic;

import java.util.Stack;

/**
 * @author Siff
 */
public class BaseStack {
    private Stack<Card> cards = new Stack<>();

    public BaseStack(Card ace){
        push(ace);
    }

    public void push(Card card){
        cards.push(card);
    }

    public Card pop(){
        return cards.pop();
    }

    public String toString(){
        Card top = cards.peek();
        return "|" + top.toString() + "|";
    }

    public Card peek() {
        return cards.peek();
    }
}
