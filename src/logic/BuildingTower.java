package logic;

import java.util.ArrayList;

public class BuildingTower {
    int faceDown;
    ArrayList<Card> faceUp = new ArrayList<>();

    private Boolean isEmpty() {
        if (faceDown == 0) {
            return true;
        }
        return false;
    }
}

