package com.project.cse5236.habitofgravity;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Brent on 10/29/2017.
 */

class PlayerObject extends levelObject {
    PlayerObject()
    {
        int screenHeight =   ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().heightPixels;
        int screenWidth =  ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().widthPixels;
        Log.d(this.toString(),"screenHeight: " + screenHeight);
        Log.d(this.toString(), "screenWidth: " + screenWidth);

        width =1;
        heigth =1;

        this.x=  ((int)((screenWidth+width)*0.5f));
        this.y= ((int)((screenHeight+heigth)*0.5f));

        Log.d(this.toString(), "X: " + x + " Y: " +y);
    }

    public void update()
    {
    }

    public void move()
    {

    }

    public int getCenterX() {
        return x + ((int)((width+1)*0.5f));
    }

    public int getCenterY() {
        return y + ((int)((heigth+1)*0.5f));
    }
}
