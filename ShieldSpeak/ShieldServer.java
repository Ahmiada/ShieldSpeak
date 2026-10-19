import java.net.ServerSocket;

public class ShieldServer {
    private ServerSocket serverSocket;

    //Two constructors: A constructor with no parameters that defaults to port 8080, and a constructor that takes a port number as a parameter.

    public ShieldServer() throws Exception {
        this(8080); 
    }

    public ShieldServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("ShieldServer started on port " + port);
    }

    public void start() {
        while (true) {
            try {
                // Accept incoming connections
                var clientSocket = serverSocket.accept();
                System.out.println("New connection from " + clientSocket.getInetAddress());

                // Handle the connection in a new thread
                new Thread(new ShieldClientHandler(clientSocket)).start();
            } catch (Exception e) {
                System.err.println("Error accepting connection: " + e.getMessage());
            }
        }
    }

    public void close() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("ShieldServer closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error closing ShieldServer: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 8080; // Default port
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            ShieldServer server = new ShieldServer(port);
            server.start();
        } catch (Exception e) {
            System.err.println("Failed to start ShieldServer: " + e.getMessage());
        }
    }
}