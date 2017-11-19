package com.project.cse5236.habitofgravity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.BitmapSingletons.playerBitmap;

/**
 * Created by Brent on 11/19/2017.
 */

public class LoggedInOnline {
    public boolean LoggedIn;

    private static volatile LoggedInOnline instance;

    private LoggedInOnline() {
        LoggedIn=true;
    }

    public static LoggedInOnline getInstance()
    {
        if (instance == null) {
            synchronized(LoggedInOnline.class) {
                if (instance == null) {
                    instance = new LoggedInOnline();
                }
            }
        }
        return instance;
    }
}
