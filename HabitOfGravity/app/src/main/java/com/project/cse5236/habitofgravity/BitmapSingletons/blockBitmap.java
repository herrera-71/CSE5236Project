package com.project.cse5236.habitofgravity.BitmapSingletons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.ContextHolder;
import com.project.cse5236.habitofgravity.R;
import com.project.cse5236.habitofgravity.levelActivity;

/**
 * Created by Brent on 10/25/2017.
 */

public class blockBitmap {

    Bitmap b;

    private static volatile blockBitmap instance;

    private blockBitmap() {
        b=BitmapFactory.decodeResource(ContextHolder.getInstance().GetContext().getResources(), R.drawable.blueblock);
    }

    public static blockBitmap getInstance()
    {
        if (instance == null) {
            synchronized(blockBitmap.class) {
                if (instance == null) {
                    instance = new blockBitmap();
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
