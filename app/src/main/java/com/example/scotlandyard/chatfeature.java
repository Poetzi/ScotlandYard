package com.example.scotlandyard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scotlandyard.client.MyKryoClient;

public class chatfeature extends AppCompatActivity {
    ChatClient chatClient;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatfeature);
        textView = findViewById(R.id.sendTextClient);
        chatClient = new ChatClient(textView);
        chatClient.start();
    }

    public void sendMessage(View view) {
        String nachricht = textView.getText().toString();
        chatClient.send(nachricht);
    }


}
