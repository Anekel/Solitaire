package Controller;

import algorithm.Algorithm;
import algorithm.ReturnData;
import logic.Card;
import logic.Deck;
import logic.Solitaire;

import java.util.ArrayList;
import java.util.Scanner;

public class SimulationController {

    private static ArrayList<Card> movedKings = new ArrayList<>();

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Solitaire game = new Solitaire(deck);

        game.setCurrentCard(deck.draw());
        System.out.println(game.toString());

        Scanner scan = new Scanner(System.in);
        String str = "";

        while (!str.equals("X")){
            boolean noMoves = false;
            while (!noMoves){
                ReturnData returnData = Algorithm.ALG(game, movedKings);
                noMoves = returnData.isNoMoves();
                movedKings = returnData.getMovedKings();
                System.out.println(game.toString());
                if(noMoves) continue;

                str = scan.next();
            }

            if(game.getCurrentCard() != null){
                deck.discard(game.getCurrentCard());
            }
            game.setCurrentCard(deck.draw());
            System.out.println("\n" + "Card drawn!");
            System.out.println(game.toString());
            str = scan.next();
        }
    }
}
