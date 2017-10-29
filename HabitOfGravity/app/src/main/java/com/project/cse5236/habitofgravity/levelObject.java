package com.project.cse5236.habitofgravity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;


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

    protected Bitmap bitmap;

    public void Setlocation(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Rect getRectangle()
    {
        return new Rect(x,y,x+width,y+heigth);
    }

    public void RotateRight(int CenterOfRotationX, int CenterOfRotationY)
    {
        //swap height width
        int t = width;
        width = heigth;
        heigth = t;

        int oldX =x;
        int oldY =y;

        x= oldY + (CenterOfRotationY-oldY);
        y= oldX + (CenterOfRotationX-oldX);

        Log.d(this.toString(), "OldX: " + oldX + " NewX: " + x);
        Log.d(this.toString(), "OldY: " + oldY + " NewY: " +y);
    }

    public void RotateLeft(int CenterOfRotationX, int CenterOfRotationY)
    {
        //swap height width
        int t = width;
        width = heigth;
        heigth = t;

        int oldX =x;
        int oldY =y;

        x= oldY - (CenterOfRotationY-oldY);
        y= oldX - (CenterOfRotationX-oldX);

        Log.d(this.toString(), "OldX: " + oldX + " NewX: " + x);
        Log.d(this.toString(), "OldY: " + oldY + " NewY: " +y);

    }

    public void draw(Canvas canvas)
    {
        //canvas.drawBitmap(null, x, y,null);
        if(bitmap != null)
            canvas.drawBitmap(bitmap, null, new Rect(x,y,x+width,y+heigth), null);
    }
}
