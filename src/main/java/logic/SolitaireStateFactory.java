package logic;

import DTO.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Siff
 */
public class SolitaireStateFactory {

    private Solitaire game = null;

    public void updateGameFromDTO(SolitaireDTO DTO){
        if(this.game == null) game = new Solitaire(7);
        Solitaire game = dtoToSolitaire(DTO);
        setGame(updateChanges(game));
    }

    public void updateGameFromGame(Solitaire game){
        if (this.game == null){
            setGame(game);
        }
        else{
            setGame(updateChanges(game));
        }
    }

    // Methods to apply changes to game
    private Solitaire updateChanges(Solitaire update){
        Solitaire game = getGame();

        if (!cardsEqual(game.getCurrentCard(), update.getCurrentCard())){
            game.setCurrentCard(update.getCurrentCard());
        }

        updateTowers(game.getTowerList(), update.getTowerList());

        updateBaseStacks(game.getBaseStackMap(), update.getBaseStackMap());

        return game;
    }

    private boolean cardsEqual(Card prev, Card update){
        if(prev == null) return false;
        return prev.equals(update);
    }

    private void updateTowers(ArrayList<BuildingTower> prev, ArrayList<BuildingTower> update){
        //https://stackoverflow.com/questions/15985266/how-to-iterate-through-two-arraylists-simultaneously
        Iterator<BuildingTower> prevIt = prev.iterator();
        Iterator<BuildingTower> updateIt = update.iterator();

        while (prevIt.hasNext() && updateIt.hasNext()){
            BuildingTower prevTower = prevIt.next();
            BuildingTower updateTower = updateIt.next();

            if (!cardsEqual(prevTower.getEnd(), updateTower.getEnd())){
                prevTower.addCard(updateTower.getEnd());
            }
        }
    }

    private void updateBaseStacks(HashMap<Character, BaseStack> prev, HashMap<Character, BaseStack> update){

        for (Map.Entry entry : update.entrySet()){
            BaseStack stack = (BaseStack) entry.getValue();
            char suit = (Character) entry.getKey();

            if(!prev.containsKey(suit)){
                prev.put(suit, new BaseStack(stack.peek()));
            }
            else if (!cardsEqual(prev.get(suit).peek(), stack.peek())){
                prev.get(suit).push(stack.peek());
            }
        }
    }

    // Getters and Setters
    public Solitaire getGame() {
        return game;
    }

    public void setGame(Solitaire game) {
        this.game = game;
    }

    // Method to initialize Solitaire form DTOs
    private Solitaire dtoToSolitaire(SolitaireDTO solitaireDTO){
        Solitaire game = new Solitaire();
        game.setCurrentCard(dtoToCard(solitaireDTO.getCurrentCard()));
        game.setBaseStackMap(dtoToBaseStackMap(solitaireDTO.getBaseStack()));
        game.setTowerList(dtoToTowerList(solitaireDTO.getTowers()));
        return game;
    }

    private Card dtoToCard(CardDTO cardDTO){
        if(cardDTO == null) return null;
        else{
            return new Card(cardDTO.getSuit(), cardDTO.getValue());
        }
    }

    private HashMap<Character, BaseStack> dtoToBaseStackMap(ArrayList<CardDTO> baseStackDTO){
        HashMap<Character, BaseStack> baseStackMap = new HashMap<>();

        for (CardDTO cardDTO : baseStackDTO){
            baseStackMap.put(cardDTO.getSuit(), new BaseStack(dtoToCard(cardDTO)));
        }
        return baseStackMap;
    }

    private ArrayList<BuildingTower> dtoToTowerList(ArrayList<BuildingTowerDTO> towers){
        ArrayList<BuildingTower> towerList = new ArrayList<>();

        for (BuildingTowerDTO tower: towers) {
            BuildingTower buildingTower = new BuildingTower();

            for (CardDTO card : tower.getFaceUpCards()) {
                buildingTower.addCard(dtoToCard(card));
            }
            towerList.add(buildingTower);
        }

        return towerList;
    }


}
