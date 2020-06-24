package logic;

import java.util.Collections;
import java.util.Stack;

/**
 * @author Siff
 */
public class Deck {
    Stack<Card> faceDown = new Stack<>();
    Stack<Card> discard = new Stack<>();

    public Deck(){
        char[] suits = {'D', 'H', 'C', 'S'};

        for(int i = 0; i < suits.length; i++){
            for(int j = 1; j < 14; j++){
                Card card = new Card(suits[i], j);
                faceDown.push(card);
            }
        }
    }

    public Card draw(){
        if (faceDown.isEmpty()) {
            flipDiscard();
        }
        return faceDown.pop();
    }

    public void discard(Card card){
        discard.push(card);
    }

    public boolean isEmpty(){
        return faceDown.isEmpty() && discard.isEmpty();
    }

    public Deck shuffle(){
        if (faceDown.isEmpty()){
            flipDiscard();
        }
        Collections.shuffle(faceDown);
        return this;
    }

    private void flipDiscard(){
        while (!discard.empty()){
            faceDown.push(discard.pop());
        }
    }

    public int size(){
        return faceDown.size() + discard.size();
    }
}
