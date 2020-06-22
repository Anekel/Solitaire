package Controller;

import algorithm.Algorithm;
import logic.Deck;
import logic.Solitaire;
import logic.SolitaireStateFactory;

import java.util.Scanner;

public class SimulationController {

    private static SolitaireStateFactory stateFactory = new SolitaireStateFactory();
    private static Deck deck = new Deck();

    public static void main(String[] args) {

        init();

        Scanner scan = new Scanner(System.in);
        String str = "";

        while (!str.equals("X")){
            boolean noMoves = false;
            while (!noMoves){
                Solitaire game = stateFactory.getGame();

                noMoves = Algorithm.run(stateFactory.getGame());

                System.out.println(game.toString());
                if(noMoves) continue;

                stateFactory.updateGameFromGame(game);

                str = scan.next();
            }

            Solitaire game = stateFactory.getGame();
            if(game.getCurrentCard() != null){
                deck.discard(game.getCurrentCard());
            }
            game.setCurrentCard(deck.draw());
            System.out.println("\n" + "Card drawn!");
            System.out.println(game.toString());
            stateFactory.setGame(game);
            str = scan.next();
        }
    }

    private static void init(){
        deck.shuffle();
        Solitaire game = new Solitaire(deck);
        game.setCurrentCard(deck.draw());
        stateFactory.updateGameFromGame(game);
        System.out.println(game.toString());
    }
}
