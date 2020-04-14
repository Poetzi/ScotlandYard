package main.java.Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class MyKryoServer {
    private Server server;
    private MyServerListener listener;
    private Kryo kryo;

    public MyKryoServer() {
        server = new Server();
        listener = new MyServerListener();
        kryo = server.getKryo();
        registerMessages();
    }

    public void startServer() throws IOException {
        server.start();
        server.bind(Ports.TCP,Ports.UDP);
        server.addListener(listener);
    }

    private void registerMessages()
    {
        
    }

    public void stopServer()
    {
        server.stop();
    }
}
