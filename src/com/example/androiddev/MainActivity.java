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
	 private static final int RECOGNIZER_EXAMPLE = 1001;
	 private TextView tv;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.text_result);

        //setup button listener
        Button startButton = (Button) findViewById(R.id.trigger);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // RecognizerIntent prompts for speech and returns text                   
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); 
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word or phrase\nand it will show as text"); 
                startActivityForResult(intent, RECOGNIZER_EXAMPLE); 
            }
        });
    }

    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
        if (requestCode == RECOGNIZER_EXAMPLE && resultCode == RESULT_OK) { 
            // returned data is a list of matches to the speech input
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); 

            //display on screen
            tv.setText(result.toString());
        } 

        super.onActivityResult(requestCode, resultCode, data); 
    } 
}



