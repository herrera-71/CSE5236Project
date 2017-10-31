package com.project.cse5236.habitofgravity.BitmapSingletons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.ContextHolder;
import com.project.cse5236.habitofgravity.R;

/**
 * Created by Brent on 10/31/2017.
 */

public class playerBitmap {
    Bitmap b;

    private static volatile playerBitmap instance;

    private playerBitmap() {
        b= BitmapFactory.decodeResource(ContextHolder.getInstance().GetContext().getResources(), R.drawable.redblock);
    }

    public static playerBitmap getInstance()
    {
        if (instance == null) {
            synchronized(playerBitmap.class) {
                if (instance == null) {
                    instance = new playerBitmap();
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
