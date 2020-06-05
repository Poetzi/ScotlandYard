package com.example.scotlandyard.viewLayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.R;
import com.example.scotlandyard.playActivity;
import com.example.scotlandyard.presenterLayer.Presenter;

public class UsernameActivity extends AppCompatActivity {

    private Presenter presenter = Presenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    public void MainMenu(View view){
        TextView username = findViewById(R.id.editText3);
        presenter.setUsername(username.getText().toString());
        Intent intent = new Intent( this, playActivity.class);
    }



}
