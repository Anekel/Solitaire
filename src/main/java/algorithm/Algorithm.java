package algorithm;

import logic.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Algorithm {

    public static boolean ALG(Solitaire game) {

        // TODO: Simplify code
        // TODO: Move King if tower is empty!!

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            // check if End can be moved BaseStack
            Card card = tower.getEnd();
            if(baseStackCheck(card, game.getBaseStackMap())){
                game.removeFromTower(tower, card);
                game.moveToBaseStack(card);
                return false;
            }
        }

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            //check if head can be moved to another tower
            Card card = tower.getHead();
            BuildingTower destination = towerCheck(card, game.getTowerList());
            if(destination != null){ //reveres list order?
                game.removeFromTower(tower, card);
                game.moveToTower(destination, card);
                return false;
            }
        }

        Card card = game.getCurrentCard();
        if(card != null){
            for(BuildingTower tower : game.getTowerList()){
                // check if tower is empty
                if(tower.isEmpty()) continue;

                //check if current card can be moved to a tower
                boolean valuesMatch = tower.getEnd().getValue() - 1 == card.getValue();
                boolean suitsMatch = tower.getEnd().isRed() != card.isRed();

                if (valuesMatch && suitsMatch){
                    game.moveToTower(tower, card);
                    game.setCurrentCard(null);
                    return false;
                }
            }
        }

        return true;
    }

    private static BuildingTower towerCheck(Card card, ArrayList<BuildingTower> towerList){

        for(BuildingTower tower : towerList){

            if(tower.isEmpty()) continue;

            boolean valuesMatch = tower.getEnd().getValue() - 1 == card.getValue();
            boolean suitsMatch = tower.getEnd().isRed() != card.isRed();

            if (valuesMatch && suitsMatch) return tower;
        }
        return null;
    }

    private static boolean baseStackCheck(Card card, HashMap<Character, BaseStack> baseStackMap){
        if (card.getValue() == 1) return true;
        else if(baseStackMap.containsKey(card.getSuit())){
            Card top = baseStackMap.get(card.getSuit()).peek();
            return top.getValue() + 1 == card.getValue();
        }
        else return false;
    }

}
