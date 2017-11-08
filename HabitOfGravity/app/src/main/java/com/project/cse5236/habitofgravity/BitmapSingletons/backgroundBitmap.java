package com.project.cse5236.habitofgravity.BitmapSingletons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.ContextHolder;
import com.project.cse5236.habitofgravity.R;

/**
 * Created by jonathanherrera on 11/6/17.
 */

public class backgroundBitmap {
    Bitmap b;

    private static volatile backgroundBitmap instance;

    private backgroundBitmap() {
        b= BitmapFactory.decodeResource(ContextHolder.getInstance().GetContext().getResources(), R.drawable.whitecararaslab01);
    }

    public static backgroundBitmap getInstance()
    {
        if (instance == null) {
            synchronized(backgroundBitmap.class) {
                if (instance == null) {
                    instance = new backgroundBitmap();
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
