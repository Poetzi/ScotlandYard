package com.example.scotlandyard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.scotlandyard.presenterLayer.Presenter;
import com.example.scotlandyard.viewLayer.Chat;
import com.example.scotlandyard.viewLayer.IPFinder;
import com.example.scotlandyard.viewLayer.gameActivity;


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
            presenter.connectToServer(ipFinder.getIp());

        }).start();

        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);
    }
}
