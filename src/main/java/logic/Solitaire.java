package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Siff
 */
public class Solitaire {

    private ArrayList<BuildingTower> towerList = new ArrayList<>();
    private HashMap<Character, BaseStack> baseStackMap = new HashMap<>();
    private Card currentCard;
    private int nonMovableDraws = 0;

    public Solitaire(Deck deck){
        // Instantiate BuildingTowers
        int towers = 7;
        for(int i = 0; i < towers; i++){
            towerList.add(new BuildingTower());
        }

        // Setup cards
        int n = 0;
        for(int i = n; i < towers; i++){
            towerList.get(i).setHead(deck.draw());
            n++;
            for(int j = n; j < towers; j++){
                towerList.get(j).pushFaceDown(deck.draw());
            }
        }
    }

    public Solitaire(){}

    public Solitaire(int towers){
        // Instantiate BuildingTowers
        for(int i = 0; i < towers; i++){
            BuildingTower bt = new BuildingTower();

            // Generate dummy Cards
            for (int j = i; j > 0; j--){
                bt.pushFaceDown(new Card('0', 0));
            }

            towerList.add(bt);
        }
    }

   public void removeFromTower(BuildingTower tower, Card card) {
       //Remove one or more cards from the tower.
        tower.removeCard(card);
    }

    public void moveToTower(BuildingTower tower, Card card) {
        //Move one or more cards from one tower to another.
        tower.addCard(card);
    }

    public void moveToBaseStack (Card card) {
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

    public void setTowerList(ArrayList<BuildingTower> towerList) {
        this.towerList = towerList;
    }

    public HashMap<Character, BaseStack> getBaseStackMap() {
        return baseStackMap;
    }

    public void setBaseStackMap(HashMap<Character, BaseStack> baseStackMap) {
        this.baseStackMap = baseStackMap;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public int getNonMovableDraws() {
        return nonMovableDraws;
    }

    public void incrementNonMovableDraws() {
        nonMovableDraws++;
    }

    public void resetNonMovableDraws() {
        nonMovableDraws = 0;
    }

    public String toString(){
        String str = "";
        //Current card
        if (currentCard != null){
            str += "Current card: " + currentCard.toString() + "\n";
        }
        else{
            str += "Current card: NULL\n";
        }
        //Base Stacks
        for (Map.Entry entry : baseStackMap.entrySet()){
            BaseStack stack = (BaseStack) entry.getValue();
            str += stack.toString();
        }
        str += "\n";
        //Building towers
        for (BuildingTower tower : towerList) {
            str += tower.toString() + "\n";
        }
        return str;
    }
}
