package logic;

import java.util.ArrayList;

public class BuildingTower {
    private int faceDown;
    private Card head;
    private Card  end;

    public BuildingTower(int index, Card head){
        setFaceDown(index);
        setHead(head);
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
        if (head.nextCard == null) setEnd(head);
    }

    public void setEnd(Card end) {
        this.end = end;
    }
}

