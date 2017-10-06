package com.project.cse5236.habitofgravity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Brent on 10/5/2017.
 */

public class audioAssets {

    public boolean Music = true;
    public boolean Sound = true;

    private static volatile audioAssets instance;

    private audioAssets(){}

    public static audioAssets getInstance()
    {
        if (instance == null) {
            synchronized(audioAssets.class) {
                if (instance == null) {
                    instance = new audioAssets();
                }
            }
        }
        return instance;
    }

    public void loadAssets(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("HabitOfGravitySettings", 0);
        Sound = settings.getBoolean("sound", true);
        Music = settings.getBoolean("music", true);
    }
}
