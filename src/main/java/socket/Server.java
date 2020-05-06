package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();


        //Outputting dataObject to the Client
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        TestData messageToClient = new TestData("This is a message from the Server! You're connected!");
        objectOutputStream.writeObject(messageToClient);

        //reading object input from client
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        TestData testData = (TestData)input.readObject();
        System.out.println(testData.text);

        serverSocket.close();
    }
}