package logic;

import java.util.ArrayList;
import java.util.HashMap;

public class Solitaire {
    private ArrayList<BuildingTower> towerList = new ArrayList<>();
    private HashMap<Character, BaseStack> baseStackMap = new HashMap<>();
    private Card currentCard;

    public Solitaire(){
        // TODO receive input data?
    }

    private void removeFromTower(int index, Card card) {
        towerList.get(index).removeCard(card);
    }

    private void moveToTower(int index, Card card) {
        //Move one or more cards from one tower to another.
        towerList.get(index).addCard(card);
    }

    private void moveToBaseStack (Card card) {
        //Move a card to it's base stack
        char suit = card.getSuit();
        if (!baseStackMap.containsKey(suit)) baseStackMap.put(suit, new BaseStack(card));
        else{
            baseStackMap.get(suit).push(card);
        }
    }

    // Getters and Setters
    public ArrayList<BuildingTower> getTowerList() {
        return towerList;
    }

    public HashMap<Character, BaseStack> getBaseStackMap() {
        return baseStackMap;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }
}
