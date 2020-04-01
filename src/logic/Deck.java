package logic;

import java.util.Collections;
import java.util.Stack;

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
        Collections.shuffle(faceDown);
        Collections.shuffle(faceDown);
    }

    public Card drawCard(){
        if (faceDown.isEmpty()) {
            flipDeck();
        }
        return faceDown.pop();
    }

    public void discardCard(Card card){
        discard.push(card);
    }

    public boolean isDeckEmpty(){
        return faceDown.isEmpty() && discard.isEmpty();
    }

    private void flipDeck(){
        while (!discard.empty()){
            faceDown.push(discard.pop());
        }
    }

    public int size(){
        return faceDown.size() + discard.size();
    }
}
