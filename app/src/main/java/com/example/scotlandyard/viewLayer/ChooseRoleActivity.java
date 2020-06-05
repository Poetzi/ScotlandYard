package com.example.scotlandyard.viewLayer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.R;
import com.example.scotlandyard.playActivity;
import com.example.scotlandyard.presenterLayer.Presenter;

public class ChooseRoleActivity extends AppCompatActivity {
    private Presenter presenter = Presenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
    }

    public void chooseMrX(View view)
    {
        Intent intent = new Intent( this, playActivity.class);
        // Todo MrX setzen
        startActivity(intent);
    }

    public void chooseDetektiv(View view)
    {
        Intent intent = new Intent( this, playActivity.class);
        // Todo Detektiv setzen

        startActivity(intent);
    }
}
