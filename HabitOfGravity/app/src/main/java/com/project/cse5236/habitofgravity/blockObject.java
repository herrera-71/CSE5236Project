package com.project.cse5236.habitofgravity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;

/**
 * Created by Brent on 10/25/2017.
 */

public class blockObject extends levelObject {

    public blockObject(int x, int y, int heigth, int width)
    {
        bitmap = blockBitmap.getInstance().getBitmap();
        this.x = x;
        this.y = y;
        this.heigth = heigth;
        this.width =width;
    }
}
