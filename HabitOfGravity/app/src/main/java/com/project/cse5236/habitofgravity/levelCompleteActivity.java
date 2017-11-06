package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class levelCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_complete);

        ContextHolder.getInstance().SetContext(this);

        Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button retryButton = (Button) findViewById(R.id.retryButton);
        retryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                levelLoader.LoadLevel(levelStats.getInstance().CurrentLevel);
                Intent intent = new Intent(view.getContext(), levelActivity.class);
                startActivity(intent);
            }
        });

        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // Can be more EFFICIENT
                if(levelStats.getInstance().NextLevel != 0) {
                    levelLoader.LoadLevel(levelStats.getInstance().NextLevel);
                    Intent intent = new Intent(view.getContext(), levelActivity.class);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        TextView score = (TextView) findViewById(R.id.Score);
        // Take the 0 off the score, add the new score to it
        score.setText("Score: " + levelStats.getInstance().Score);

        // Ask the database for the high score, set the high score from that
        TextView highScore = (TextView) findViewById(R.id.HighScore);


    }

    @Override
    public void onBackPressed() {}
}
