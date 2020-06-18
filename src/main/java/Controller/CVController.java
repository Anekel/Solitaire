package Controller;

import DTO.*;
import algorithm.*;
import logic.*;
import com.google.gson.Gson;
import socket.ClientSocket;

import java.io.*;
import java.util.ArrayList;

public class CVController {

    private static SolitaireStateFactory stateFactory = new SolitaireStateFactory();

    public static void main(String[] args) throws IOException {

        ClientSocket clientSocket = new ClientSocket(8080);

        boolean run = true;
        while(run) {

            String data = clientSocket.readData();

            if (!data.equals("Skip")){
                Gson gson = new Gson();
                SolitaireDTO gameDTO = gson.fromJson(data, SolitaireDTO.class);
                stateFactory.updateGameFromDTO(gameDTO);
            }

            String str = runALG(stateFactory.getGame());

            clientSocket.sendData(str);
        }

        System.exit(0);
    }

    private static String runALG(Solitaire game) {

        StringBuilder stringBuilder = new StringBuilder(game.toString());
        stringBuilder.append("\n");

        if (!Algorithm.ALG(game)){
            stringBuilder.append(game.toString());
        }
        else {
            stringBuilder.append("Draw card!\n");
        }

        System.out.println(stringBuilder.toString());

        return stringBuilder.toString();
    }
}