package DTO;

import java.util.LinkedList;

public class BuildingTowerDTO {

    private boolean faceDownCards;
    private LinkedList<CardDTO> faceUpCards;

    public boolean isFaceDownCards() {
        return faceDownCards;
    }

    public void setFaceDownCards(boolean faceDownCards) {
        this.faceDownCards = faceDownCards;
    }

    public LinkedList<CardDTO> getFaceUpCards() {
        return faceUpCards;
    }

    public void setFaceUpCards(LinkedList<CardDTO> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }
}
