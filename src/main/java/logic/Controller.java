package logic;

import algorithm.Algorithm;
import algorithm.ReturnData;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private static ArrayList<Card> movedKings = new ArrayList<>();

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Solitaire game = new Solitaire(deck);

        game.setCurrentCard(deck.draw());
        System.out.println(game.toString());

        Scanner scan = new Scanner(System.in);

        boolean noMoves = false;
        while (!noMoves){
            ReturnData returnData = Algorithm.ALG(game, movedKings);
            noMoves = returnData.isNoMoves();
            movedKings = returnData.getMovedKings();
            System.out.println(game.toString());
            if(noMoves) continue;

            scan.next();
        }
        System.out.println("\n" + "Draw Card!");
    }
}
