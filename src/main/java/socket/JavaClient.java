package socket;

import DTO.BuildingTowerDTO;
import DTO.CardDTO;
import DTO.SolitaireDTO;
import logic.Solitaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class JavaClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientsocket = new Socket("192.168.8.115", 8080); //local IP adress

        //Outputting dataObject to the server
        ObjectOutputStream outputStream = new ObjectOutputStream(clientsocket.getOutputStream());
        SolitaireDTO testData = new SolitaireDTO();
        CardDTO newCard = new CardDTO('C',4);
        testData.setCurrentCard(newCard);
        LinkedList<CardDTO> faceUp = new LinkedList<>();
        faceUp.add(new CardDTO('D', 6 ));
        faceUp.add(new CardDTO('H', 10 ));
        faceUp.add(new CardDTO('S', 3 ));
        testData.setTowers(0, new BuildingTowerDTO(true, faceUp));
        outputStream.writeObject(testData);


        //Reading data object from Server
        ObjectInputStream objectInputStream = new ObjectInputStream(clientsocket.getInputStream());
        SolitaireDTO data = (SolitaireDTO) objectInputStream.readObject();
        clientsocket.close();

    }
}

