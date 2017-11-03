package com.project.cse5236.habitofgravity;

/**
 * Created by Brent on 11/2/2017.
 */

public class levelStats {
    private static volatile levelStats instance;

    private levelStats() {

    }

    public static levelStats getInstance()
    {
        if (instance == null) {
            synchronized(levelStats.class) {
                if (instance == null) {
                    instance = new levelStats();
                }
            }
        }
        return instance;
    }

    public int CurrentLevel;
    public int NextLevel;
    public int Score;
}
