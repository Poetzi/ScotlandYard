package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import com.example.scotlandyard.R;

public class Chat extends AppCompatActivity {

    private Presenter presenter = Presenter.getInstance();

    private TextView textView;
    private TextView editText;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        user = new User("Computer");
        presenter.setUser(user);
        presenter.setLog(textView);
    }


    public void verbindeZuServer(View view) {

            new Thread(() -> {
                // Server wird gestartet
                //Eigene IPv4-Adresse eintragen für einen lokalen Test.
                presenter.connectToServer("192.168.1.148");

            }).start();




    }

    public void sendMessage(View view) {
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendMessagetoServer(editText.getText().toString());

        }).start();
    }


    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
