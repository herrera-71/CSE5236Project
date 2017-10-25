package com.project.cse5236.habitofgravity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Brent on 10/25/2017.
 */

public class blockObject extends levelObject {

    Bitmap blockBitmap;

    public blockObject()
    {
    }


    @Override
    public void draw(Canvas canvas)
    {
        //canvas.drawBitmap(null, x, y,null);

        //void drawBitmap (Bitmap bitmap,
         //   Rect src,
          //  Rect dst,
           // Paint paint)

        canvas.drawBitmap(blockBitmap, null, new Rect(x,y,heigth,width), null);

    }
}
