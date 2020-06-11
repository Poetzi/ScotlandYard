package com.example.scotlandyard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.presenterLayer.Presenter;
import com.example.scotlandyard.viewLayer.Chat;
import com.example.scotlandyard.viewLayer.ReadyCheck;
import com.example.scotlandyard.viewLayer.gameActivity;

public class playActivity extends AppCompatActivity {
    private Presenter presenter = Presenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void chat(View view) {
        Intent intent = new Intent(this, Chat.class);
        startActivity(intent);
    }


    public void goToGameActivity(View view) {


        //Intent wird gestartet um durch Knopfdruck auf Chat-Seite zu gelangen
        Intent intent = new Intent(this, ReadyCheck.class);
        startActivity(intent);


    }
}
