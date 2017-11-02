package com.project.cse5236.habitofgravity.BitmapSingletons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.ContextHolder;
import com.project.cse5236.habitofgravity.R;

/**
 * Created by Brent on 11/1/2017.
 */

public class goalBitmap {
    Bitmap b;

    private static volatile goalBitmap instance;

    private goalBitmap() {
        b= BitmapFactory.decodeResource(ContextHolder.getInstance().GetContext().getResources(), R.drawable.greenblock);
    }

    public static goalBitmap getInstance()
    {
        if (instance == null) {
            synchronized(goalBitmap.class) {
                if (instance == null) {
                    instance = new goalBitmap();
                }
            }
        }
        return instance;
    }

    public Bitmap getBitmap()
    {
        return b;
    }
}
