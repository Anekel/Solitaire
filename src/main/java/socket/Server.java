package socket;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;
import com.google.gson.Gson;
import logic.Card;
import logic.Solitaire;
import org.json.JSONObject;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String fromClient;
        ServerSocket server = new ServerSocket(8080);
        System.out.println("wait for connection on port 8080");

        boolean run = true;
        while(run) {
            Socket client = server.accept();
            System.out.println("got connection on port 8080");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("received: " + sb.toString());

            Gson gson = new Gson();
            CardDTO card = gson.fromJson(sb.toString(), CardDTO.class);

//            JSONObject json = new JSONObject(sb.toString());

            System.out.println("received: " + card.getValue());
        }


        System.exit(0);
    }
}