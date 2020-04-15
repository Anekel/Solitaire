package algorithm;

import logic.*;

import java.util.HashMap;

public class Algorithm {

    public static void ALG(Solitaire game){

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            // check if End can be moved BaseStack
            Card card = tower.getEnd();
            if(baseStackCheck(card, game.getBaseStackMap())){
                game.moveToBaseStack(card);
                return;
            }

            //check if head can be moved to another tower
        }
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
