package com.example.scotlandyard.viewLayer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.R;
import com.example.scotlandyard.playActivity;
import com.example.scotlandyard.presenterLayer.Presenter;

/**
 * Class for choosing the Player-Role
 */
public class ChooseRoleActivity extends AppCompatActivity {
    //Presenter is assigned
    private Presenter presenter = Presenter.getInstance();

    /**
     * onCreate method for setting up the ChooseRoleActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
    }

    /**
     * Method for choosing MrX
     *
     * @param view View
     */
    public void chooseMrX(View view) {
        //Intent is set up to go to the next View
        Intent intent = new Intent(this, playActivity.class);
        // MrX setzen
        presenter.setRole("MISTERX");
        new Thread(() -> {

            // die Rolle wird dem Server übergeben
            presenter.sendRole();


        }).start();
        //Go to the next View
        startActivity(intent);
    }

    /**
     * Method for choosing the detective role
     *
     * @param view View
     */
    public void chooseDetektiv(View view) {
        //Intent is set up to go to the next View
        Intent intent = new Intent(this, playActivity.class);
        // Detektiv setzen
        presenter.setRole("DETEKTIV");
        new Thread(() -> {

            // die Rolle wird dem Server übergeben
            presenter.sendRole();

        }).start();
        //Go to the next View
        startActivity(intent);
    }
}
