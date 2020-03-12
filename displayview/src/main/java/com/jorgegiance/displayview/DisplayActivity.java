package com.jorgegiance.displayview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    String joke = "";
    TextView displayView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        Intent jokeIntent = getIntent();

        if (jokeIntent.hasExtra("JOKE_KEY")){
            joke = jokeIntent.getStringExtra("JOKE_KEY");
        }


        displayView = findViewById(R.id.display_view);

        if (!joke.isEmpty()){
            displayView.setText(joke);
        }else{
            displayView.setText("The joke is on its way");
        }

    }
}
