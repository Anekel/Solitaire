package algorithm;

import logic.*;
import java.util.HashMap;

/**
* @author Siff
*/
public class Algorithm {

    public static boolean run(Solitaire game) {

        int size = game.getTowerList().size();
        Card card = game.getCurrentCard();

        // Check if the current card can be moved to a BaseStack
        if(card != null){
            boolean move = baseStackCheck(card, game.getBaseStackMap());
            if(move){
                game.moveToBaseStack(card);
                game.setCurrentCard(null);
                return false;
            }
        }

        // Check if the last card in a tower can be moved to a BaseStack
        for (int i = size-1; i >= 0; i--){
            BuildingTower tower = game.getTowerList().get(i);

            // Check if tower is empty
            if(tower.isEmpty()) continue;
            Card end = tower.getEnd();

            // Check if End can be moved BaseStack
            boolean move = baseStackCheck(end, game.getBaseStackMap());
            if(move){
                game.removeFromTower(tower, end);
                game.moveToBaseStack(end);
                return false;
            }
        }

        // Check if the first card in a tower can be moved to another tower
        for (int i = size-1; i >= 0; i--){
            BuildingTower tower = game.getTowerList().get(i);

            // Check if tower is empty
            if(tower.isEmpty()) continue;
            Card head = tower.getHead();

            // Check if none-movable king
            if(head.isKing() && tower.getFaceDown().isEmpty()) continue;

            // Check if head can be moved to another tower
            for(BuildingTower crossTower : game.getTowerList()){

                boolean move = towerCheck(head, crossTower);
                if (move){
                    game.removeFromTower(tower, head);
                    game.moveToTower(crossTower, head);
                    return false;
                }
            }
        }

        // Check if the current card can be moved to a tower
        if(card != null){

            for(BuildingTower tower : game.getTowerList()){

                boolean move = towerCheck(card, tower);
                if (move){
                    game.moveToTower(tower, card);
                    game.setCurrentCard(null);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean towerCheck(Card card, BuildingTower tower){

        // Check if tower is empty
        if(tower.isEmpty()) return card.isKing();

        // Check if cards match
        boolean valuesMatch = tower.getEnd().getValue() - 1 == card.getValue();
        boolean suitsMatch = tower.getEnd().isRed() != card.isRed();

        return valuesMatch && suitsMatch;
    }

    private static boolean baseStackCheck(Card card, HashMap<Character, BaseStack> baseStackMap){
        // Check if card is ace
        if (card.getValue() == 1) return true;

        // Check if baseStackMap contains the suit
        else if(baseStackMap.containsKey(card.getSuit())){
            Card top = baseStackMap.get(card.getSuit()).peek();
            return top.getValue() + 1 == card.getValue();
        }
        else return false;
    }

}
