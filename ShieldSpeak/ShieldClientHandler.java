package ShieldSpeak;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ShieldClientHandler implements Runnable {

    public static ArrayList<ShieldClientHandler> connectedClients = new ArrayList<>();
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ShieldClientHandler(Socket socket) {
        try {
            this.clientSocket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferedReader.readLine();
            connectedClients.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has joined the chat!");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        // Implementation for handling client connections goes here
    }
}
