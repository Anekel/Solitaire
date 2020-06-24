package DTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Siff
 */
public class SolitaireDTO implements Serializable {

    private CardDTO currentCard;
    private ArrayList<BuildingTowerDTO> towers = new ArrayList<>();
    private ArrayList<CardDTO> baseStack;

    public SolitaireDTO() {
        for (int i = 0; i < 7; i++){
            towers.add(new BuildingTowerDTO());
        }
    }

    public CardDTO getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CardDTO currentCard) {
        this.currentCard = currentCard;
    }

    public ArrayList<CardDTO> getBaseStack() {
        return baseStack;
    }

    public void setBaseStack(ArrayList<CardDTO> baseStack) {
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
