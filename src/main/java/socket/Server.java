package socket;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;
import logic.Card;
import logic.Solitaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();


        //Outputting dataObject to the Client
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        SolitaireDTO testData1 = new SolitaireDTO();
        objectOutputStream.writeObject(testData1);

        //reading object input from client
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        SolitaireDTO testData = (SolitaireDTO) input.readObject();
        testData.getTower(0);
        System.out.println(testData.getCurrentCard().getSuit());
        serverSocket.close();
    }
}