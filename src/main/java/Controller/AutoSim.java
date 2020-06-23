package Controller;

import algorithm.Algorithm;
import logic.*;

public class AutoSim {

    private static Deck deck;
    private static Solitaire game;
    int[][] gamesWon;
    int[][] gamesLost;

    public static void main(String[] args) {

        int set = 1;
        int rep = 10;


        for (int i = 0; i < set; i++){
            run(rep);
        }


    }

    private static void run(int x){

        int gamesWon = 0;
        int gamesLost = 0;

        // Num of reps
        for(int i = 0; i < x; i++){

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
            if (isGameWon()) gamesWon++;
            else gamesLost ++;
            System.out.println("----------------------------------------------");
        }

        System.out.println("W: " + gamesWon);
        System.out.println("L: " + gamesLost);
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
