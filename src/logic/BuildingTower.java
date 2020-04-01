package logic;

import java.util.ArrayList;
import java.util.Stack;

public class BuildingTower {
    private Stack<Card> faceDown = new Stack<>();
    private Card head;
    private Card end;

    public BuildingTower(){    }

    public BuildingTower(Stack<Card> faceDown, Card head){
        setFaceDown(faceDown);
        setHead(head);
    }

    public void addCard(Card card){
        card.prevCard = end;
        card.prevCard.nextCard = card;
        setEnd(card);
    }

    public Card removeCard(Card card){
        if (card.equals(head)){ //TODO what to do!?!?
            setHead(faceDown.pop());
        }
        else {
            card.prevCard.nextCard = null;
            setEnd(card.prevCard);
            card.prevCard = null;
        }
        return card;
    }

    public void pushFaceDown(Card card){
        faceDown.push(card);
    }

    private Boolean isEmpty() {
        return faceDown.isEmpty() && head == null;
    }

    // Getters and Setters
    public int getFaceDown() {
        return faceDown.size();
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
        String str =  "[" + faceDown.size() + "] " + head.toString();
        if (head.nextCard != null) str += end.toString();

        return str;
    }
}

