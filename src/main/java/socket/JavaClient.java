package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JavaClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientsocket = new Socket("192.168.8.115", 8080); //local IP adress

        //Outputting dataObject to the server
        ObjectOutputStream outputStream = new ObjectOutputStream(clientsocket.getOutputStream());
        TestData testData = new TestData("Well Hello Server");
        outputStream.writeObject(testData);


        //Reading data object from Server
        ObjectInputStream objectInputStream = new ObjectInputStream(clientsocket.getInputStream());
        TestData data = (TestData) objectInputStream.readObject();
        System.out.println(data.text);
        clientsocket.close();

    }
}

