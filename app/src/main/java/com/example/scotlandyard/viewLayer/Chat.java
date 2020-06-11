package com.example.scotlandyard.viewLayer;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotlandyard.R;
import com.example.scotlandyard.presenterLayer.Presenter;

/**
 * Class for the Chat function
 */
public class Chat extends AppCompatActivity {
    //Presenter is assigned to variable
    private Presenter presenter = Presenter.getInstance();
    //Chat history display
    private TextView chatVerlaufTextView;
    //Message to send
    private TextView nachrichtenTextView;

    /**
     * onCreate method to set up the Chat Activity
     *
     * @param savedInstanceState savedInstanceState
     */
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

    /**
     * Method for sendin a message to the server
     *
     * @param view View
     */
    public void sendMessage(View view) {
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendMessagetoServer(nachrichtenTextView.getText().toString());
        }).start();
    }
}
