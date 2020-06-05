package logic;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solitaire {
    private ArrayList<BuildingTower> towerList = new ArrayList<>();
    private HashMap<Character, BaseStack> baseStackMap = new HashMap<>();
    private Card currentCard;

    public Solitaire(Deck deck){
        int towers = 7;
        for(int i = 0; i < towers; i++){
            towerList.add(new BuildingTower());
        }
        int n = 0;
        for(int i = n; i < towers; i++){
            towerList.get(i).setHead(deck.draw());
            n++;
            for(int j = n; j < towers; j++){
                towerList.get(j).pushFaceDown(deck.draw());
            }
        }
        int length = towerList.size();

        for (int i = 1 ; i < length; i++){
            BuildingTower tower = towerList.remove(i);
            towerList.add(0, tower);
        }
    }

    public Solitaire(){    }

    public Solitaire(SolitaireDTO solitaireDTO){
        setCurrentCard(dtoToCard(solitaireDTO.getCurrentCard()));
        setBaseStackMap(dtoToBaseStackMap(solitaireDTO.getBaseStack()));
        setTowerList(dtoToTowerList(solitaireDTO.getTowers()));
    }

    private Card dtoToCard(CardDTO cardDTO){
        return new Card(cardDTO.getSuit(), cardDTO.getValue());
    }

    private HashMap<Character, BaseStack> dtoToBaseStackMap(HashMap<Character, CardDTO> baseStackDTO){
        HashMap<Character, BaseStack> baseStackMap = new HashMap<>();

        for (CardDTO cardDTO : baseStackDTO.values()){
            baseStackMap.put(cardDTO.getSuit(), new BaseStack(dtoToCard(cardDTO)));
        }
        return baseStackMap;
    }

    private ArrayList<BuildingTower> dtoToTowerList(ArrayList<BuildingTowerDTO> towers){
        ArrayList<BuildingTower> towerList = new ArrayList<>();

        for (BuildingTowerDTO tower: towers) {
            BuildingTower buildingTower = new BuildingTower();

            //TODO face down?

            for (CardDTO card : tower.getFaceUpCards()) {
                buildingTower.addCard(dtoToCard(card));
            }
            towerList.add(buildingTower);
        }
        return towerList;
    }

   public void removeFromTower(BuildingTower tower, Card card) {
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

    public String toString(){
        String str = "";
        //Current card
        if (currentCard != null){
            str += "Current card: " + currentCard.toString() + "\n";
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
