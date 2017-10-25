package com.project.cse5236.habitofgravity;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;

/**
 * Created by Brent on 10/25/2017.
 */

public class levelAssets {

    private static volatile levelAssets instance;
    private levelAssets()
    {

    }
    public static levelAssets getInstance()
    {
        if (instance == null) {
            synchronized(levelAssets.class) {
                if (instance == null) {
                    instance = new levelAssets();
                }
            }
        }
        return instance;
    }

    public levelScreen levelScreen;

    public void update() {
        levelScreen.update();
    }

    public void draw(Canvas canvas) {
        //drawbackground
        levelScreen.draw(canvas);



        //draw controllers
        controllers.getInstance().draw(canvas);
    }
}
