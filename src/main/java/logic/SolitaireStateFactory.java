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

    public void updateGame(SolitaireDTO DTO){

        Solitaire game = new Solitaire();
        game.setCurrentCard(dtoToCard(DTO.getCurrentCard()));
        game.setBaseStackMap(dtoToBaseStackMap(DTO.getBaseStack()));
        game.setTowerList(dtoToTowerList(DTO.getTowers()));

        if (this.game == null){
            this.game = game;
        }
        else{
            this.game = changes(game);
        }
    }

    private Solitaire create(SolitaireDTO solitaireDTO){
        Solitaire game = new Solitaire();
        game.setCurrentCard(dtoToCard(solitaireDTO.getCurrentCard()));
        game.setBaseStackMap(dtoToBaseStackMap(solitaireDTO.getBaseStack()));
        game.setTowerList(dtoToTowerList(solitaireDTO.getTowers()));
        return game;
    }

    private Solitaire changes(Solitaire update){
        Solitaire game = this.game;

        if (!game.getCurrentCard().equals(update.getCurrentCard())){
            game.setCurrentCard(update.getCurrentCard());
        }

        //https://stackoverflow.com/questions/15985266/how-to-iterate-through-two-arraylists-simultaneously

        Iterator<BuildingTower> gameTowers = game.getTowerList().iterator();
        Iterator<BuildingTower> updateTowers = update.getTowerList().iterator();

        while (gameTowers.hasNext() && updateTowers.hasNext()){
            BuildingTower tower = gameTowers.next();
            BuildingTower tower1 = gameTowers.next();

            if (!tower.getEnd().equals(tower1.getEnd())){
                tower.addCard(tower1.getEnd());
            }
        }

        for (Map.Entry entry : update.getBaseStackMap().entrySet()){
            BaseStack stack = (BaseStack) entry.getValue();

            if(!game.getBaseStackMap().containsKey(stack.getSuit())){
                game.moveToBaseStack(stack.peek());
            }
            else if(game.getBaseStackMap().containsKey(stack.getSuit())){
                if(!game.getBaseStackMap().get(stack.getSuit()).peek().equals(stack.peek())){
                    game.moveToBaseStack(stack.peek());
                }
            }
        }


        return game;
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
