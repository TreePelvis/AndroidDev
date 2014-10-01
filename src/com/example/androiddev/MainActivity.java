package com.example.androiddev;

import java.util.ArrayList;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends ActionBarActivity {
	private static final int PLAY_GAME = 1010;
    private TextView tv; 
    private int meaningOfLife = 42;
    private String userName = "Douglas Adams";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.startscreen_text);
        tv.setText(userName + ":" + meaningOfLife);

        //setup button listener
        Button startButton = (Button) findViewById(R.id.play_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startGame();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
            int resultCode, Intent data) {
        if (requestCode == PLAY_GAME && resultCode == RESULT_OK) {
            meaningOfLife = data.getExtras().getInt("returnInt");
            userName = data.getExtras().getString("returnStr");
            //show it has changed
            tv.setText(userName + ":" + meaningOfLife);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startGame() {
        Intent launchGame = new Intent(this, PlayGame.class);

        //passing information to launched activity
        launchGame.putExtra("meaningOfLife", meaningOfLife);
        launchGame.putExtra("userName", userName);

        startActivityForResult(launchGame, PLAY_GAME);
    }
}