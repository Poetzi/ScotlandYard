package Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class MyServerListener extends Listener {
    //Kommentar
    public void connected(Connection connection)
    {
        System.out.println("Server: Jemand ist dem Server beigetreten");
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("Server: Jemand hat den Server verlassen");
    }

    public void received(Connection connection, Object object)
    {

    }
}
