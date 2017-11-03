package com.project.cse5236.habitofgravity;

import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import java.io.IOException;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

/**
 * Created by Kupo on 10/25/2017.
 */

public class  DatabaseManager {

    // Defines the url for the database
    private static final String DATABASE_URL = "https://habitofgravity.firebaseio.com/";
    // Determines how many scores are listed in the high scores
    private static final int MaxScores = 10;

    private static DatabaseReference database;

    /**
     * Start global listener for all high scores.
     */
    public static void startListeners() {
        database.child("highscores").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
                final String scoreId = dataSnapshot.getKey();
                final HighScore post = dataSnapshot.getValue(HighScore.class);

                // Notify player that a new high score was added?

            }

            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {}

            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {}

            public void onCancelled(DatabaseError databaseError) {
                System.out.println("startListeners: unable to attach listener to scores");
                System.out.println("startListeners: " + databaseError.getMessage());
            }
        });
    }

    protected static void AddScoreToLeaders(final String name,
                                            final long score,
                                            DatabaseReference leaderBoardRef) {
        leaderBoardRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                {
                    List<HighScore> leaders = (List<HighScore>) mutableData.getValue();

                    if (leaders == null) {
                        leaders = new ArrayList<>();
                    } else if (mutableData.getChildrenCount() >= MaxScores) {
                        long minScore = (long) Integer.MAX_VALUE;
                        HighScore minVal = null;
                        for (HighScore child : leaders) {
                            if (!(child instanceof HighScore)) continue;
                            long childScore = Long.parseLong(child.score);
                            if (childScore < minScore) {
                                minScore = childScore;
                                minVal = child;
                            }
                        }
                        if (minScore > score) {
                            // The new score is lower than the existing 5 scores, abort.
                            return Transaction.abort();
                        }

                        // Remove the lowest score.
                        leaders.remove(minVal);
                    }

                    // Add the new high score.
                    HighScore newScore = new HighScore(name, "" + score);
                    leaders.add(newScore);
                    mutableData.setValue(leaders);
                    return Transaction.success(mutableData);
                }

            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("DatabaseManager", "Transaction complete! " + dataSnapshot);
            }


        });
    }

    public static void GetHighScores() {
        database.orderByChild("score").limitToLast(MaxScores).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("startListeners: unable to attach listener to scores");
                System.out.println("startListeners: " + databaseError.getMessage());
            }

        });

    }

    public static void main(String[] args) {
            // Initialize Firebase
            try {
                // [START initialize]
                FileInputStream serviceAccount = new FileInputStream("service-account.json");
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setDatabaseUrl(DATABASE_URL)
                        .build();
                // NEED THE CONTEXT FOR THE FIREBASE DATABASE
                /**
                 * A Context is a handle to the system;
                 * it provides services like resolving resources,
                 * obtaining access to databases and preferences,
                 * and so on. An Android app has activities.
                 * It's like a handle to the environment your application is currently running in.
                 * The activity object inherits the Context object.
                 */
                FirebaseApp.initializeApp(null,options);
                // [END initialize]
            } catch (IOException e) {
                System.out.println("ERROR: invalid service account credentials. See README.");
                System.out.println(e.getMessage());

                System.exit(1);
            }

            // Shared Database reference
            database = FirebaseDatabase.getInstance().getReference();

            // Start listening to the Database
            startListeners();

        }

}
