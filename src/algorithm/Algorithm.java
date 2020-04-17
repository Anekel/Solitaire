package algorithm;

import logic.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Algorithm {

    public static boolean ALG(Solitaire game) {

        // TODO: Simplify code
        // TODO: Move King if tower is empty!!
        // TODO: Logic for no more moves? vs draw card ??

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
            Card head = tower.getHead();

            for(BuildingTower crossTower : game.getTowerList()){

                if(crossTower.isEmpty()) continue;

                boolean valuesMatch = crossTower.getEnd().getValue() - 1 == head.getValue();
                boolean suitsMatch = crossTower.getEnd().isRed() != head.isRed();

                if (valuesMatch && suitsMatch) {
                    game.removeFromTower(tower, head);
                    game.moveToTower(crossTower, head);
                    return false;
                }
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
