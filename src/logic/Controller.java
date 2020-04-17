package logic;

import algorithm.Algorithm;

import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Solitaire game = new Solitaire(deck);

        game.setCurrentCard(deck.draw());
        System.out.println(game.toString());

        Scanner scan = new Scanner(System.in);

        boolean isFinished = false;
        while (!isFinished){
            isFinished = Algorithm.ALG(game);
            System.out.println(game.toString());
            if(isFinished) continue;

            scan.next();
        }
        System.out.println("\n" + "Draw Card!");
    }
}
