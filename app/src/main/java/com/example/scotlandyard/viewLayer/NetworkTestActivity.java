package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import com.example.scotlandyard.R;
import com.example.scotlandyard.client.Message;
import com.example.scotlandyard.client.MyNetworkThread;

public class NetworkTestActivity extends AppCompatActivity {

    private MyNetworkThread networkThread;
    private TextView textView;
    private TextView sendenTextView;

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

    public void sendMessage(View view){
        sendenTextView = findViewById(R.id.sendenTextFeld);
        String buffer = "" + sendenTextView.getText().toString();
        Message message = new Message();
        message.message = buffer;

        //enableStrictMode();
        networkThread.sendenMethode(message);
    }

    public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
