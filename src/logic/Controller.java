package logic;

import algorithm.Algorithm;

public class Controller {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Solitaire game = new Solitaire(deck);
        System.out.println(game.toString());

        Algorithm.ALG(game);

        System.out.println(game.toString());
    }
}
