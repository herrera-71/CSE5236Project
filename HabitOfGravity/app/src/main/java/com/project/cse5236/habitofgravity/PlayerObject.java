package com.project.cse5236.habitofgravity;

import android.graphics.Canvas;
import android.util.Log;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;
import com.project.cse5236.habitofgravity.BitmapSingletons.playerBitmap;

/**
 * Created by Brent on 10/29/2017.
 */

class PlayerObject extends levelObject {

    boolean moveRight =false;

    float dXLeft=0;
    float dXRight=0;
    float dY=0;



    PlayerObject()
    {
        int screenHeight =   ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().heightPixels;
        int screenWidth =  ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().widthPixels;

        if(screenHeight>screenWidth)
        {
            int t=screenWidth;
            screenWidth=screenHeight;
            screenHeight=t;
        }

        Log.d(this.toString(),"screenHeight: " + screenHeight);
        Log.d(this.toString(), "screenWidth: " + screenWidth);


        width =151;
        heigth =151;

        bitmap = playerBitmap.getInstance().getBitmap();

        this.x=  ((int)((screenWidth-width)*0.5f));
        this.y= ((int)((screenHeight-heigth)*0.5f));

        Log.d(this.toString(), "X: " + x + " Y: " +y);

        levelAssets.getInstance().CenterHeight =(int)(screenHeight *0.5f);
        levelAssets.getInstance().CenterWidth =(int)(screenWidth *0.5f);
    }

    public void update()
    {
        if(!(dXLeft!=0f && dXRight!= 0f))
            x+=dXLeft+dXRight;
        y+=dY;
        moveRight=false;

        if(dXLeft<0)
            dXLeft-=1;
        if(dXRight>0)
            dXRight+=1;
    }

    public void moveRight(boolean right)
    {
        if(dXRight!= 0 & !right)
            Log.d(this.toString(),"Resetting dXRight from:" +dXRight);
        if(right)
            dXRight=1;
        else
            dXRight=0;

    }

    public void moveLeft(boolean left)
    {
        if(dXLeft!= 0 & !left)
         Log.d(this.toString(),"Resetting dXLeft from:" +dXLeft);
        if(left)
            dXLeft=-1;
        else
            dXLeft=0;



    }

    public int getCenterX() {
        return x + ((int)((width+1)*0.5f));
    }

    public int getCenterY() {
        return y + ((int)((heigth+1)*0.5f));
    }


}
