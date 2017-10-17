package com.project.cse5236.habitofgravity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class levelBackground {

    private Bitmap backGroundImage;
    private int x=0, y=0, dx = 10;

    public levelBackground(Bitmap img)
    {
        backGroundImage = img;
    }
    public void update()
    {
        //updates background location
        x+=dx;
        //resets if completely off screen
        if(x<-backGroundImage.getWidth()){
            Log.d(getClass().toString(),"Screen Reset to x=0");
            x=0;
        }
        if(x> backGroundImage.getWidth()){
            Log.d(getClass().toString(),"Screen Reset to x=0");
            x=0;
        }
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(backGroundImage, x, y,null);
        //this causes the tileable effect
        if(x<0)
        {
            canvas.drawBitmap(backGroundImage, x+ backGroundImage.getWidth(), y, null);
        }
        if(x>0)
        {
            canvas.drawBitmap(backGroundImage, x- backGroundImage.getWidth(), y, null);
        }
    }
}