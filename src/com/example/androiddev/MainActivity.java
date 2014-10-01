package com.example.androiddev;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        

        Button startButton = (Button) findViewById(R.id.trigger);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Thread initBkgdThread = new Thread(new Runnable() {
                    public void run() {                      
                        play_music();
                    }     
                });
                initBkgdThread.start();
            }
        });        
    }

    int[] notes = {R.raw.c5, R.raw.b4, R.raw.a4, R.raw.g4};
    int NOTE_DURATION = 400; //millisec
    MediaPlayer m_mediaPlayer;
    private void play_music() {
        for(int i=0; i<12; i++) {
            //check to ensure main activity not paused
            if(!paused) {
                if(m_mediaPlayer != null) {m_mediaPlayer.release();} 
                m_mediaPlayer = MediaPlayer.create(this, notes[i%4]);
                m_mediaPlayer.start();
                try {
                    Thread.sleep(NOTE_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    boolean paused = false;
    @Override
    protected void onPause() {
        paused = true;
        super.onPause();
    } 
    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }    
}