package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class SolitaireDTO implements Serializable {

    private CardDTO currentCard;
    private ArrayList<BuildingTowerDTO> towers = new ArrayList<>();
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

    public ArrayList<BuildingTowerDTO> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<BuildingTowerDTO> towers) {
        this.towers = towers;
    }

    public BuildingTowerDTO getTower(int i) {
        return towers.get(i);
    }

    public void setTowers(int i, BuildingTowerDTO tower) {
        this.towers.set(i, tower);
    }
}
