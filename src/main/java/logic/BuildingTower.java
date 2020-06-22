package logic;

import java.util.ArrayList;
import java.util.Stack;

public class BuildingTower {
    private Stack<Card> faceDown = new Stack<Card>();
    private Card head;
    private Card end;

    public BuildingTower(){    }

    public BuildingTower(Stack<Card> faceDown, Card head){
        setFaceDown(faceDown);
        setHead(head);
    }

    public void addCard(Card card){
        if (head == null) setHead(card);
        else{
            card.prevCard = end;
            card.prevCard.nextCard = card;
            setEnd(card);
        }
    }

    public void removeCard(Card card){
        if (card == head){

            // For hinting
            setHead(null);
            if (!faceDown.isEmpty()) faceDown.pop();
//
//            // For simulation
//            if (faceDown.size() > 0) setHead(faceDown.pop());
//            else setHead(null);
        }
        else {
            card.prevCard.nextCard = null;
            setEnd(card.prevCard);
            card.prevCard = null;
        }
    }

    public void pushFaceDown(Card card){
        faceDown.push(card);
    }

    public Boolean isEmpty() {
        return faceDown.isEmpty() && head == null;
    }

    // Getters and Setters
    public Stack<Card> getFaceDown() {
        return faceDown;
    }

    public Card getHead() {
        return head;
    }

    public Card getEnd() {
        return end;
    }

    public void setFaceDown(Stack<Card> faceDown) {
        this.faceDown = faceDown;
    }

    public void setHead(Card head) {
        this.head = head;
        setEnd(head);
    }

    public void setEnd(Card card) {
        if (card == null) {
            end = null;
            return;
        }
        if (card.nextCard == null) {
            this.end = card;
        }
        else {
            // Iterate forward to last card in list
            Card curr = card;
            while (curr.nextCard != null) {
                curr = curr.nextCard;
            }
            this.end = curr;
        }
    }

    public String toString(){
        String str =  "[" + faceDown.size() + "] ";
        Card curr = head;
        while (curr != null){
            str += curr.toString();
            curr = curr.nextCard;
        }

        return str;
    }
}

