package logic;

import java.util.ArrayList;

public class BuildingTower {
    int faceDown;
    Card head;
    Card  end;

    private Boolean isEmpty() {
        if (faceDown == 0) {
            return true;
        }
        return false;
    }
}

