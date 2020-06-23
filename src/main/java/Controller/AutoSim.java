package Controller;

import algorithm.Algorithm;
import logic.*;

import java.util.Arrays;

public class AutoSim {

    private static Deck deck;
    private static Solitaire game;
    static int[] gamesWon;
    static int[] gamesLost;

    public static void main(String[] args) {

        int set = 10;
        int rep = 10000;

        gamesWon = new int[set];
        gamesLost = new int[set];


        for (int i = 0; i < set; i++){
            run(i, rep);
        }

        System.out.println("\n\n************************************************");
        System.out.println("W: " + Arrays.toString(gamesWon));
        System.out.println("L: " + Arrays.toString(gamesLost));
        System.out.println("************************************************");

    }

    private static void run(int set, int rep){

        int win = 0;
        int lose = 0;

        // Num of reps
        for(int i = 0; i < rep; i++){

            deck = new Deck().shuffle();
            game = new Solitaire(deck);
            System.out.println(game.toString());

            while (!isGameOver()){

                boolean noMoves = false;

                while (!noMoves){

                    noMoves = Algorithm.run(game);

                    if(!noMoves){
                        System.out.println(game.toString());
                    }
                    if (isGameWon()) break;

                }
                if (isGameWon()) break;

                if (game.getCurrentCard() != null){
                    deck.discard(game.getCurrentCard());
                    game.incrementNonMovableDraws();
                }
                else {
                    game.resetNonMovableDraws();
                }
                game.setCurrentCard(deck.draw());
                System.out.println(game.toString());
            }
            if (isGameWon()) win++;
            else lose ++;
            System.out.println("----------------------------------------------");
        }

        gamesWon[set] = win;
        gamesLost[set] = lose;

        System.out.println("W: " + win);
        System.out.println("L: " + lose);
    }

    private static boolean isGameOver(){
        return isGameWon() || isGameLost();
    }

    private static boolean isGameWon(){
        for(BuildingTower tower : game.getTowerList()){

            if (!tower.getFaceDown().isEmpty()) return false;
        }
        return true;
    }

    private static boolean isGameLost(){

        return game.getNonMovableDraws() == deck.size();
    }

}
