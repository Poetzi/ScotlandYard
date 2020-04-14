package Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        main.java.Server.MyKryoServer server = new main.java.Server.MyKryoServer();
        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
