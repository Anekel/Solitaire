package socket;

import DTO.*;
import algorithm.*;
import logic.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Server {

    private static ArrayList<Card> movedKings = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ClientSocket clientSocket = new ClientSocket(8080);

        boolean run = true;
        while(run) {

            String data = clientSocket.readData();

            Gson gson = new Gson();
            SolitaireDTO gameDTO = gson.fromJson(data, SolitaireDTO.class);

            String str = runALG(gameDTO);

            clientSocket.sendData(str);
        }

        System.exit(0);
    }

    private static String runALG(SolitaireDTO dto) {

        Solitaire game = new Solitaire(dto);

        StringBuilder stringBuilder = new StringBuilder(game.toString());
        stringBuilder.append("\n");

        ReturnData returnData = Algorithm.ALG(game, movedKings);
        movedKings = returnData.getMovedKings();

        if (!returnData.isNoMoves()){
            stringBuilder.append(game.toString());
        }
        else {
            stringBuilder.append("Draw card!");
        }

        System.out.print(stringBuilder.toString());

        return stringBuilder.toString();
    }
}