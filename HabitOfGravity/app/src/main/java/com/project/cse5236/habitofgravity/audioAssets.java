package com.project.cse5236.habitofgravity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

/**
 * Created by Brent on 10/5/2017.
 */

public class audioAssets {

    public boolean Music = true;
    public boolean Sound = true;
    private MediaPlayer mp;

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

        //music from https://www.youtube.com/watch?v=HaZzgw9aWc8

        if(mp==null)
        {
            mp = MediaPlayer.create(context, R.raw.basic_metal_4);
            mp.setLooping(true);
            if (Music)
                mp.start();
        }
    }

    public void ToggleAudio()
    {
        if(Music)
            if(mp.isPlaying())
                mp.pause();
            else
                mp.start();
        else
            mp.pause();
    }
}
