package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scotlandyard.R;
import com.example.scotlandyard.presenterLayer.Presenter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Chat extends AppCompatActivity {

    private Presenter presenter = Presenter.getInstance();
    private TextView chatVerlaufTextView;
    private TextView nachrichtenTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Chatverlauf wird zugewiesen
        chatVerlaufTextView = findViewById(R.id.textView);
        //Name beim Verbinden zum Server wird zugewiesen
        nachrichtenTextView = findViewById(R.id.editText2);
        //MovementMethod für Chat-TextView wird festgelegt
        chatVerlaufTextView.setMovementMethod(new ScrollingMovementMethod());
        //Chat-TextView wird dem Presenter für weiterverarbeitung übergeben
        presenter.setLog(chatVerlaufTextView);
    }

    public void sendMessage(View view) {
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendMessagetoServer(nachrichtenTextView.getText().toString());
        }).start();
    }
}
