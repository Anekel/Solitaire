package logic;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class SolitaireStateFactory {

    private Solitaire game = null;

    public void updateGame(SolitaireDTO DTO){
        if (game == null){
            game = create(DTO);
        }
        else{
            game = changes(DTO);
        }
    }

    private Solitaire create(SolitaireDTO solitaireDTO){
        Solitaire game = new Solitaire();
        game.setCurrentCard(dtoToCard(solitaireDTO.getCurrentCard()));
        game.setBaseStackMap(dtoToBaseStackMap(solitaireDTO.getBaseStack()));
        game.setTowerList(dtoToTowerList(solitaireDTO.getTowers()));
        return game;
    }
    private Solitaire changes(SolitaireDTO DTO){
        return null;
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
