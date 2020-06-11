package algorithm;

import logic.Card;

import java.util.ArrayList;
import java.util.List;

public class ReturnData {

    boolean noMoves;
    ArrayList<Card> movedKings;

    public ReturnData(boolean noMoves, ArrayList<Card> movedKings){
        this.noMoves = noMoves;
        this.movedKings = movedKings;
    }

    public boolean isNoMoves() {
        return noMoves;
    }

    public void setNoMoves(boolean noMoves) {
        this.noMoves = noMoves;
    }

    public ArrayList<Card> getMovedKings() {
        return movedKings;
    }

    public void setMovedKings(ArrayList<Card> movedKings) {
        this.movedKings = movedKings;
    }
}
