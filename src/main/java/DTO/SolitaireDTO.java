package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SolitaireDTO implements Serializable {

    private CardDTO currentCard;
    private boolean[] faceDown = new boolean[7];
    private ArrayList<LinkedList<CardDTO>> towers = new ArrayList<>();
    private HashMap<Character, CardDTO> baseStack;

    public SolitaireDTO() {    }

    public CardDTO getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CardDTO currentCard) {
        this.currentCard = currentCard;
    }

    public HashMap<Character, CardDTO> getBaseStack() {
        return baseStack;
    }

    public void setBaseStack(HashMap<Character, CardDTO> baseStack) {
        this.baseStack = baseStack;
    }

    public ArrayList<LinkedList<CardDTO>> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<LinkedList<CardDTO>> towers) {
        this.towers = towers;
    }

    public LinkedList<CardDTO> getTower(int i) {
        return towers.get(i);
    }

    public void setTowers(int i, LinkedList<CardDTO> tower) {
        this.towers.set(i, tower);
    }

    public boolean[] getFaceDown() {
        return faceDown;
    }

    public void setFaceDown(boolean[] faceDown) {
        this.faceDown = faceDown;
    }
}
