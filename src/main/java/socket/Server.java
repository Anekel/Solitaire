package socket;

import DTO.SolitaireDTO;
import algorithm.Algorithm;
import com.google.gson.Gson;
import logic.Solitaire;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server started");

        boolean run = true;
        while(run) {
            Socket client = server.accept();
            System.out.println("Client accepted");

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            SolitaireDTO dto = readData(in);

            String str = runALG(dto);

            client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            sendData(out, str);
            client.close();
        }

        System.exit(0);
    }

    private static SolitaireDTO readData(BufferedReader in) throws IOException {

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("received: " + sb.toString());

        Gson gson = new Gson();
        SolitaireDTO game = gson.fromJson(sb.toString(), SolitaireDTO.class);

        return game;
    }

    private static void sendData(PrintWriter out, String string){
        out.println(string);
    }

    private static String runALG(SolitaireDTO dto) {
        Solitaire game = new Solitaire(dto);

        StringBuilder stringBuilder = new StringBuilder(game.toString());
        stringBuilder.append("\n\n");

        Algorithm.ALG(game);

        stringBuilder.append(game.toString());

        System.out.print(stringBuilder.toString());

        return stringBuilder.toString();
    }
}