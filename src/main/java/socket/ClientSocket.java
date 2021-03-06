package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author Siff & Hella
 */
public class ClientSocket {

    ServerSocket server;

    public ClientSocket(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server started\n");
    }

    public String readData() throws IOException {
        Socket client = server.accept();
        System.out.println("Client accepted");

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        StringBuilder sb = new StringBuilder();
        System.out.println("Reading data");
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }

        System.out.println("Received: " + sb.toString() + "\n");
        client.close();

        return sb.toString();
    }

    public void sendData(String string) throws IOException {
        Socket client = server.accept();
        System.out.println("\nClient accepted");
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        out.println(string);
        System.out.println("Data sent\n\n");
        client.close();
    }
}
