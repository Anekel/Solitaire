package logic;

import java.util.ArrayList;

public class Solitaire {
    ArrayList<BuildingTower> buildingList = new ArrayList<>();
    ArrayList<BaseStack> baseStacks = new ArrayList<>();
    Card currentcard;

    private  void removeFromTower(int index, Card card) {

    }

    private void moveToTower(Card card) {
        //Move one or more cards from one tower to another.
    }

    private void moveToBaseStack (Card card) {
        //Move a card to it's base stack
    }

    private Card drawCard() {
        return new Card();
    }
}
