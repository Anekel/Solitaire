package logic;

import java.util.ArrayList;

public class BuildingTower {
    private int faceDown;
    private Card head;
    private Card end;

    public BuildingTower(int index, Card head){
        setFaceDown(index);
        setHead(head);
    }

    public void addCard(Card card){
        card.prevCard = end;
        card.prevCard.nextCard = card;
        setEnd(card);
    }

    public Card removeCard(Card card){
        if (card.equals(head)){ //TODO what to do!?!?
            faceDown--;
            head = null;
            end = null;
        }
        else {
            card.prevCard.nextCard = null;
            setEnd(card.prevCard);
            card.prevCard = null;
        }
        return card;
    }

    private Boolean isEmpty() {
        return faceDown == 0 && head == null;
    }

    // Getters and Setters
    public int getFaceDown() {
        return faceDown;
    }

    public Card getHead() {
        return head;
    }

    public Card getEnd() {
        return end;
    }

    public void setFaceDown(int faceDown) {
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
}

