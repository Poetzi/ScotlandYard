package com.example.scotlandyard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.scotlandyard.presenterLayer.Presenter;
import com.example.scotlandyard.viewLayer.Chat;
import com.example.scotlandyard.viewLayer.gameActivity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class playActivity extends AppCompatActivity {
    private Presenter presenter = Presenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void chat(View view){
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }


    public void goToGameActivity(View view)
    {
        new Thread(() -> {
            //IPv4-Adresse des Geräts wird gesucht.
            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo;
            int ipInt;
            if (wm != null) {
                wifiInfo = wm.getConnectionInfo();
                ipInt = wifiInfo.getIpAddress();
            }else {
                throw new NullPointerException();
            }
            String ip = null;
            try {
                ip = InetAddress.getByAddress(
                        ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array())
                        .getHostAddress();
            } catch (UnknownHostException e) {
                Log.d("playActivity","Something went wrong while trying to determine the IPv4 address",e);
            }
            //Server wird gestartet.
            presenter.connectToServer(ip);

        }).start();

        Intent intent = new Intent(this, gameActivity.class);


        startActivity(intent);
    }
}
