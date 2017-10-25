package com.project.cse5236.habitofgravity;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

/**
 * Created by Kupo on 10/25/2017.
 */

public class DatabaseManager {

    FirebaseDatabase database;
    DatabaseReference myRef;
    static MutableData mutableData;

    private static DatabaseManager dbManager = new DatabaseManager( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private DatabaseManager() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    /* Static 'instance' method */
    public static DatabaseManager getInstance( ) {
        return dbManager;
    }

    /* Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
        System.out.println("demoMethod for singleton");
    }

    /**
    protected static void AddScoreToLeaders(String name,
                                            long score,
                                            DatabaseReference leaderBoardRef) {
        leaderBoardRef.RunTransaction(mutableData => {
                List<object> leaders = mutableData.Value as List<object>

        if (leaders == null) {
            leaders = new List<object>();
        } else if (mutableData.ChildrenCount >= MaxScores) {
            long minScore = long.MaxValue;
            object minVal = null;
            foreach (var child in leaders) {
                if (!(child is Dictionary<string, object>)) continue;
                long childScore = (long)
                        ((Dictionary<string, object>)child)["score"];
                if (childScore < minScore) {
                    minScore = childScore;
                    minVal = child;
                }
            }
            if (minScore > score) {
                // The new score is lower than the existing 5 scores, abort.
                return TransactionResult.Abort();
            }

            // Remove the lowest score.
            leaders.Remove(minVal);
        }

        // Add the new high score.
        Dictionary<string, object> newScoreMap =
                new Dictionary<string, object>();
        newScoreMap["score"] = score;
        newScoreMap["email"] = email;
        leaders.Add(newScoreMap);
        mutableData.Value = leaders;
        return TransactionResult.Success(mutableData);
    });

    }
    **/

}
