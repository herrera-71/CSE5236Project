package com.project.cse5236.habitofgravity;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Kupo on 10/27/2017.
 */
@IgnoreExtraProperties
public class HighScore {
    public String uid;
    public String score;

    public HighScore() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public HighScore(String uid, String score) {
        this.uid = uid;
        this.score = score;
    }
}
