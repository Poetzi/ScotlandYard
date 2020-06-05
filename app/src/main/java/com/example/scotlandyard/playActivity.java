package com.example.scotlandyard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.scotlandyard.presenterLayer.Presenter;
import com.example.scotlandyard.viewLayer.Chat;
import com.example.scotlandyard.viewLayer.IPFinder;
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
            IPFinder ipFinder=new IPFinder(getApplicationContext());
            ipFinder.findIP();
            //Server wird gestartet.
            presenter.connectToServer("143.205.186.73");
            presenter.sendUsername();

        }).start();

        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);
    }
}
