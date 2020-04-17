package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.scotlandyard.R;
import com.example.scotlandyard.client.MyNetworkThread;

public class NetworkTestActivity extends AppCompatActivity {

    private MyNetworkThread networkThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);
        networkThread = new MyNetworkThread();
    }

    public void connectToServer(View view)
    {
        networkThread.start();
    }
}
