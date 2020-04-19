package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.scotlandyard.R;
import com.example.scotlandyard.client.MyNetworkThread;

public class NetworkTestActivity extends AppCompatActivity {

    private MyNetworkThread networkThread;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);
        textView = findViewById(R.id.textViewMessagesFromServer);
        networkThread = new MyNetworkThread(textView);
    }

    public void connectToServer(View view)
    {
        networkThread.start();
    }
}
