package socket;

import DTO.*;
import algorithm.*;
import logic.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Server {

    private static SolitaireStateFactory stateFactory = new SolitaireStateFactory();

    public static void main(String[] args) throws IOException {

        ClientSocket clientSocket = new ClientSocket(8080);

        boolean run = true;
        while(run) {

            String data = clientSocket.readData();

            Gson gson = new Gson();
            SolitaireDTO gameDTO = gson.fromJson(data, SolitaireDTO.class);

            stateFactory.updateGameFromDTO(gameDTO);

            String str = runALG(stateFactory.getGame(), stateFactory.getMovedKings());

            clientSocket.sendData(str);
        }

        System.exit(0);
    }

    private static String runALG(Solitaire game, ArrayList<Card> movedKings) {

        StringBuilder stringBuilder = new StringBuilder(game.toString());
        stringBuilder.append("\n");

        if (!Algorithm.ALG(game, movedKings)){
            stringBuilder.append(game.toString());
        }
        else {
            stringBuilder.append("Draw card!");
        }

        System.out.print(stringBuilder.toString());

        return stringBuilder.toString();
    }
}