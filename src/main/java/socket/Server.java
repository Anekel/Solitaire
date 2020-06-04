package socket;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;
import logic.Card;
import logic.Solitaire;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String fromClient;
        String toClient;

        ServerSocket server = new ServerSocket(8080);
        System.out.println("wait for connection on port 8080");

        boolean run = true;
        while(run) {
            Socket client = server.accept();
            System.out.println("got connection on port 8080");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);

            fromClient = in.readLine();
            System.out.println("received: " + fromClient);

            if(fromClient.equals("Hello")) {
                toClient = "olleH";
                System.out.println("send olleH");
                out.println(toClient);
                fromClient = in.readLine();
                System.out.println("received: " + fromClient);

                if(fromClient.equals("Bye")) {
                    toClient = "eyB";
                    System.out.println("send eyB");
                    out.println(toClient);
                    client.close();
                    run = false;
                    System.out.println("socket closed");
                }
            }
        }
        System.exit(0);
    }
}