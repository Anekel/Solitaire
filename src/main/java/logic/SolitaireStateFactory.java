package logic;

import DTO.SolitaireDTO;

public class SolitaireStateFactory {

    private Solitaire game = null;

    public void updateGame(SolitaireDTO DTO){
        if (game == null){
            // TODO transfer code from SolitaireConstructor?
            game = new Solitaire(DTO);
        }
        else{
            game = changes(DTO);
        }
    }

    private Solitaire changes(SolitaireDTO DTO){
        return null;
    }

    public Solitaire getGame() {
        return game;
    }

    public void setGame(Solitaire game) {
        this.game = game;
    }
}
