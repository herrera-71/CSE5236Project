package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class levelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);


        View.OnClickListener vocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button2:
                        Log.d(getClass().toString(), "button level 1");
                        //set next level to level 1
                        break;
                    case R.id.button4:
                        Log.d(getClass().toString(), "button level 2");
                        //set next level to level 2
                        break;
                    case R.id.button5:
                        Log.d(getClass().toString(), "button level 3");
                        //set next level to level 3
                        break;
                    default:
                        break;
                }
                //launch next level
                Intent intent = new Intent(view.getContext(), levelActivity.class);
                startActivity(intent);
            }
        };

        ((Button)findViewById(R.id.button2)).setOnClickListener(vocl);
        ((Button)findViewById(R.id.button4)).setOnClickListener(vocl);
        ((Button)findViewById(R.id.button5)).setOnClickListener(vocl);


    }

    Intent intent = getIntent();
}
