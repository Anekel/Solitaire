package DTO;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Siff
 */
public class BuildingTowerDTO implements Serializable {

    private boolean faceDownCards;
    private LinkedList<CardDTO> faceUpCards;

    public BuildingTowerDTO(){ }

    public BuildingTowerDTO(boolean b, LinkedList<CardDTO> faceUp) {
        this.faceDownCards = b;
        this.faceUpCards = faceUp;
    }

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
