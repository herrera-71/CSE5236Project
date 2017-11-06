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

    public void RotateRight(Rect focus)
    {
        //bottom left
        int BLx = x;
        int BLy = y+heigth;

        int oldX =x;
        int oldY =y;

        int difX = focus.centerX() - BLx;
        int difY = focus.centerY() - BLy;
        Log.d(this.toString(), "DifX: " + difX);
        Log.d(this.toString(), "DifY: " +difY);
        Log.d(this.toString(), "CenterX: " +focus.centerX() + " CenterY: " + focus.centerY());
        y = focus.centerY() -difX;
        x = focus.centerX() +difY;
        //x= oldY + (CenterOfRotationY-oldY);
        //y= oldX + (CenterOfRotationX-oldX);

        Log.d(this.toString(), "OldX: " + oldX + " NewX: " + x);
        Log.d(this.toString(), "OldY: " + oldY + " NewY: " +y);

        //swap height width
        int t = width;
        width = heigth;
        heigth = t;
    }

    public void RotateLeft(Rect focus)
    {
        //top right
        int TRx = x+width;
        int TRy = y;

        int oldX =x;
        int oldY =y;

        int difX = TRx - focus.centerX();
        int difY = TRy -focus.centerY();
        Log.d(this.toString(), "DifX: " + difX);
        Log.d(this.toString(), "DifY: " +difY);
        Log.d(this.toString(), "CenterX: " +focus.centerX() + " CenterY: " + focus.centerY());
        y = focus.centerY() -difX;
        x = focus.centerX() +difY;
        //x= oldY + (CenterOfRotationY-oldY);
        //y= oldX + (CenterOfRotationX-oldX);

        Log.d(this.toString(), "OldX: " + oldX + " NewX: " + x);
        Log.d(this.toString(), "OldY: " + oldY + " NewY: " +y);

        //swap height width
        int t = width;
        width = heigth;
        heigth = t;

    }

    public void draw(Canvas canvas)
    {
        //canvas.drawBitmap(null, x, y,null);
        int xOffset = levelAssets.getInstance().xOffset;
        int yOffset = levelAssets.getInstance().yOffset;
        if(bitmap != null)
            canvas.drawBitmap(bitmap, null, new Rect(x+xOffset,y+yOffset,x+width+xOffset,y+heigth+yOffset), null);
    }
}
