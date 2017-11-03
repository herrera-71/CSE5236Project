package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                levelLoader.LoadLevel(levelStats.getInstance().NextLevel);
                Intent intent = new Intent(view.getContext(), levelActivity.class);
                startActivity(intent);
            }
        });
    }
}
