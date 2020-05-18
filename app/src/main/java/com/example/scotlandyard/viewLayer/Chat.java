package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scotlandyard.R;
import com.example.scotlandyard.presenterLayer.Presenter;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Chat extends AppCompatActivity {

    private Presenter presenter = Presenter.getInstance();

    private TextView chatVerlaufTextView;
    private TextView usernameTextView;
    private TextView nachrichtenTextView;
    private User user;
    private Button verbindeButton;
    private Button sendenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //Chatverlauf wird zugewiesen
        chatVerlaufTextView = findViewById(R.id.textView);
        //Nachrichten die gesendet werden sollen werden zugewiesen
        usernameTextView = findViewById(R.id.editText);
        //Name beim Verbinden zum Server wird zugewiesen
        nachrichtenTextView = findViewById(R.id.editText2);
        //Verbinde Button wird zugewiesen
        verbindeButton = findViewById(R.id.buttonConnect);
        //Senden Button wird zugewiesen
        sendenButton = findViewById(R.id.buttonSend);

        //Nachrichten versenden wird disabled bevor eine Verbindung aufgebaut ist
        sendenButton.setEnabled(false);
        nachrichtenTextView.setEnabled(false);
        chatVerlaufTextView.setMovementMethod(new ScrollingMovementMethod());
    }


    public void verbindeZuServer(View view) {


        new Thread(() -> {

            //IPv4-Adresse des Geräts wird gesucht.
            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wm.getConnectionInfo();
            int ipInt = wifiInfo.getIpAddress();
            String ip = null;
            try {
                ip = InetAddress.getByAddress(
                        ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array())
                        .getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            // Server wird gestartet
            presenter.connectToServer(ip);

        }).start();

        //Verbinden Button und TextView werden disabled nachdem eine Verbindung aufgebaut ist
        usernameTextView.setEnabled(false);
        verbindeButton.setEnabled(false);

        //Nachrichten versenden wird enabled nachdem eine Verbindung aufgebaut ist
        sendenButton.setEnabled(true);
        nachrichtenTextView.setEnabled(true);

        user = new User(usernameTextView.getText().toString());
        presenter.setUser(user);
        presenter.setLog(chatVerlaufTextView);
    }

    public void sendMessage(View view) {
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendMessagetoServer(nachrichtenTextView.getText().toString());

        }).start();
    }


    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
