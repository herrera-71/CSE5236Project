package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button levelSelectButton = (Button) findViewById(R.id.button);
        levelSelectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), levelSelectActivity.class);
                startActivity(intent);
            }
        });

        Button settingsButton = (Button) findViewById(R.id.mainMenuSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), settingsActivity.class);
                startActivity(intent);
            }
        });

//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference();
//
//        Map<String,String> users = new HashMap<>();
//        users.put("Bob", "5");
//        users.put("Alice", "4");
//        usersRef.setValue(users);



        //DatabaseManager.AddScoreToLeaders("Bob", 0, usersRef);

        //load audio settings
        audioAssets.getInstance().loadAssets(this);
        ContextHolder.getInstance().SetContext(this);
    }

    /* Called when the user taps the Level Select Button */
    public void goToLevelSelect(View view){
        Intent intent = new Intent(this, levelSelectActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
