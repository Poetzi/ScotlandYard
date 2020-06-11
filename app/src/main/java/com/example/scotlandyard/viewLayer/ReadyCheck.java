package com.example.scotlandyard.viewLayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.R;
import com.example.scotlandyard.presenterLayer.Presenter;

public class ReadyCheck extends AppCompatActivity {

    private Presenter presenter = Presenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
    }

    public void goToGameActivity(View view) {
        new Thread(() -> {

            // die Rolle wird dem Server übergeben
            presenter.sendReady();


        }).start();

        //Intent wird gestartet um durch Knopfdruck auf Chat-Seite zu gelangen
        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);


    }
}
