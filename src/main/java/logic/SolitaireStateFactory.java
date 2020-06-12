package logic;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SolitaireStateFactory {

    private Solitaire game = null;

    public void updateGameFromDTO(SolitaireDTO DTO){

        Solitaire game = create(DTO);

        if (this.game == null){
            setGame(game);
        }
        else{
            setGame(changes(game));
        }
    }

    public void updateGameFromGame(Solitaire game){
        if (this.game == null){
            setGame(game);
        }
        else{
            setGame(changes(game));
        }
    }

    private Solitaire changes(Solitaire update){
        Solitaire game = getGame();

        if (!cardsEqual(game.getCurrentCard(), update.getCurrentCard())){
            game.setCurrentCard(update.getCurrentCard());
        }

        checkTowers(game.getTowerList(), update.getTowerList());

        checkBaseStacks(game.getBaseStackMap(), update.getBaseStackMap());

        return game;
    }

    private boolean cardsEqual(Card prev, Card update){
        if(prev == null) return false;
        else return prev.equals(update);
    }

    private void checkTowers(ArrayList<BuildingTower> prev, ArrayList<BuildingTower> update){
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

    private void checkBaseStacks(HashMap<Character, BaseStack> prev, HashMap<Character, BaseStack> update){

        for (Map.Entry entry : update.entrySet()){
            BaseStack stack = (BaseStack) entry.getValue();
            char suit = stack.getSuit();

            if(!prev.containsKey(suit)){
                prev.put(suit, new BaseStack(stack.peek()));
            }
            else if (!cardsEqual(prev.get(suit).peek(), stack.peek())){
                prev.get(suit).push(stack.peek());
            }
        }
    }

    public Solitaire getGame() {
        return game;
    }

    public void setGame(Solitaire game) {
        this.game = game;
    }

    private Card dtoToCard(CardDTO cardDTO){
        if(cardDTO == null) return null;
        else{
            return new Card(cardDTO.getSuit(), cardDTO.getValue());
        }
    }

    private Solitaire create(SolitaireDTO solitaireDTO){
        Solitaire game = new Solitaire();
        game.setCurrentCard(dtoToCard(solitaireDTO.getCurrentCard()));
        game.setBaseStackMap(dtoToBaseStackMap(solitaireDTO.getBaseStack()));
        game.setTowerList(dtoToTowerList(solitaireDTO.getTowers()));
        return game;
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

            if (tower.isFaceDownCards()) {
                buildingTower.pushFaceDown(new Card('X', 0));
            }

            for (CardDTO card : tower.getFaceUpCards()) {
                buildingTower.addCard(dtoToCard(card));
            }
            towerList.add(buildingTower);
        }
        return towerList;
    }


}
