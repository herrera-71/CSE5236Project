package com.project.cse5236.habitofgravity;

import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;


/**
 * Created by Brent on 10/15/2017.
 */

public abstract class levelObject {
    protected int x;
    protected int y;
    protected int heigth;
    protected int width;

    protected float dx;
    protected float dy;

    public void Setlocation(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Rect getRectangle()
    {
        return new Rect(x,y,x+width,y+heigth);
    }

    public void Rotate(int CenterOfRotationX, int CenterOfRotationY)
    {
        //swap height width
        int t = width;
        width = heigth;
        heigth = t;

        int oldX =x;
        int oldY =y;

        x= oldY + 2*(CenterOfRotationY-oldY);
        y= oldX + 2*(CenterOfRotationX-oldX);
    }
}
