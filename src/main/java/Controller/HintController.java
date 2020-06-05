package Controller;

import DTO.SolitaireDTO;
import algorithm.Algorithm;
import logic.Solitaire;

public class HintController {

    public static void main(String[] args) {

        // TODO get data from socket
        SolitaireDTO solitaireDTO = new SolitaireDTO();
        Solitaire game = new Solitaire(solitaireDTO);

        StringBuilder stringBuilder = new StringBuilder(game.toString());
        stringBuilder.append("\n\n");

        // TODO run ALG
        Algorithm.ALG(game);

        stringBuilder.append(game.toString());

        // TODO send result back to client

    }
}
