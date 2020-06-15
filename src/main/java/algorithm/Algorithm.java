package algorithm;

import logic.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Algorithm {

    static ArrayList<Card> movedKings;

    public static boolean ALG(Solitaire game, ArrayList<Card> kings) {

        movedKings = kings;

        Card card = game.getCurrentCard();
        if(card != null){

            boolean move = baseStackCheck(card, game.getBaseStackMap());
            if(move){
                game.moveToBaseStack(card);
                game.setCurrentCard(null);
                return false;
            }
        }

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            // check if End can be moved BaseStack
            Card end = tower.getEnd();

            boolean move = baseStackCheck(end, game.getBaseStackMap());

            if(move){
                game.removeFromTower(tower, end);
                game.moveToBaseStack(end);
                return false;
            }
        }

        for(BuildingTower tower : game.getTowerList()){
            // check if tower is empty
            if(tower.isEmpty()) continue;

            //check if head can be moved to another tower
            Card head = tower.getHead();

            for(BuildingTower crossTower : game.getTowerList()){

                boolean move = towerCheck(head, crossTower);
                if (move){
                    game.removeFromTower(tower, head);
                    game.moveToTower(crossTower, head);
                    return false;
                }
            }
        }

        card = game.getCurrentCard();
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

        // check if tower is empty
        if(tower.isEmpty()){
            if (card.isKing() && !movedKings.contains(card)) {
                movedKings.add(card);
                return true;
            }
            else return false;
        }

        //check if card can be moved to tower
        boolean valuesMatch = tower.getEnd().getValue() - 1 == card.getValue();
        boolean suitsMatch = tower.getEnd().isRed() != card.isRed();

        return valuesMatch && suitsMatch;
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
