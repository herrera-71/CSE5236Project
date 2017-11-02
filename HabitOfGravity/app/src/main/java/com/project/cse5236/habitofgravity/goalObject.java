package com.project.cse5236.habitofgravity;

import com.project.cse5236.habitofgravity.BitmapSingletons.goalBitmap;

/**
 * Created by Brent on 11/1/2017.
 */

public class goalObject extends levelObject {

    public goalObject(int x, int y, int heigth, int width)
    {
        bitmap = goalBitmap.getInstance().getBitmap();
        this.x = x;
        this.y = y;
        this.heigth = heigth;
        this.width =width;
    }
}
