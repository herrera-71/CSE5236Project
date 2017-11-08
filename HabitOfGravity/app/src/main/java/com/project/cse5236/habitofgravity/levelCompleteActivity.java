package com.project.cse5236.habitofgravity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class levelCompleteActivity extends AppCompatActivity {

    private DatabaseReference firebaseRef, userPostRef, highscorePostRef;
    private FirebaseAuth auth;
    private TextView score, highscore;

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

        //Get score
        score = (TextView) findViewById(R.id.Score);
        highscore = (TextView) findViewById(R.id.HighScore);

        //Get current level
        final int level = levelStats.getInstance().CurrentLevel;
        final String currentLevel = "Lvl" + Integer.toString(level) + "Highscore";

        //Firebase ref
        firebaseRef = FirebaseDatabase.getInstance().getReference();

        //Get UID
        auth = FirebaseAuth.getInstance();
        final String uid = auth.getCurrentUser().getUid();

        //Firebase post ref
        userPostRef = firebaseRef.child("Users");

        //User stored data
        //userPostRef.setValue(uid);
        //highscorePostRef = firebaseRef.child("Users");
        Query query = firebaseRef.child("Users");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        UserStoredData userStoredData = ds.getValue(UserStoredData.class);
                        if(userStoredData.ID.equals(uid)){
                            String key = ds.getKey();
                            int currentScore = levelStats.getInstance().Score;
                            int highScore;
                            if(level == 1){
                                highScore = Integer.parseInt(userStoredData.Lvl1Highscore);
                            }
                            else if(level == 2){
                                highScore = Integer.parseInt(userStoredData.Lvl2Highscore);
                            }
                            else{
                                highScore = Integer.parseInt(userStoredData.Lvl3Highscore);
                            }
                            if(currentScore > highScore) {
                                highScore = currentScore;
                                firebaseRef.child("Users").child(key).child(currentLevel).setValue(highScore + "");
                            }
                            highscore.setText("Highscore: " + highScore);


                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //userPostRef.setValue(uid);
        //highscorePostRef.setValue(levelStats.getInstance().Score);

        // Take the 0 off the score, add the new score to it
        score.setText("Score: " + levelStats.getInstance().Score);

        // Ask the database for the high score, set the high score from that
        //TextView highScore = (TextView) findViewById(R.id.HighScore);
        //highScore.setText("Highscore: " + levelStats.getInstance().Score);


    }

    @Override
    public void onBackPressed(){}
}
