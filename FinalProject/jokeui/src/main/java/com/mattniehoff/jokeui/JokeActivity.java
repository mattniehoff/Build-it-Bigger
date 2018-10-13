package com.mattniehoff.jokeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        String jokeText = intent.getStringExtra(JOKE_EXTRA);
        TextView jokeTextView = findViewById(R.id.joke_textView);
        if (jokeText != null && jokeText.length() > 0){
            jokeTextView.setText(jokeText);
        }
    }
}
