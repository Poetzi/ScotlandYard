package com.example.scotlandyard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scotlandyard.viewLayer.NetworkTestActivity;
import com.example.scotlandyard.viewLayer.gameActivity;

public class playActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void goToNetworkTest(View view)
    {
        Intent intent = new Intent(this, NetworkTestActivity.class);

        startActivity(intent);
    }

    public void goToGameActivity(View view)
    {
        Intent intent = new Intent(this, gameActivity.class);

        startActivity(intent);
    }
}
