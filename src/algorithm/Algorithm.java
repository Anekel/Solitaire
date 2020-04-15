package algorithm;

import logic.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Algorithm {

    public static void ALG(Solitaire game){

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            // check if End can be moved BaseStack
            Card card = tower.getEnd();
            if(baseStackCheck(card, game.getBaseStackMap())){
                game.removeFromTower(tower, card);
                game.moveToBaseStack(card);
            }

            //check if head can be moved to another tower
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
            }
        }
    }

    private static BuildingTower towerCheck(Card card, ArrayList<BuildingTower> towerList){

        for(BuildingTower tower : towerList){

            if(tower.isEmpty()) continue;

            boolean valuesMatch = tower.getEnd().getValue() - 1 == card.getValue();
            boolean suitsMatch = tower.getEnd().getSuit() != card.getSuit();

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
