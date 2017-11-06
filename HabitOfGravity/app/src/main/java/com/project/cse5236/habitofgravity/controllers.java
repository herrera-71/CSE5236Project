package com.project.cse5236.habitofgravity;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.project.cse5236.habitofgravity.BitmapSingletons.controllerButtons;

/**
 * Created by Brent on 10/25/2017.
 */

public class controllers {

    controllerButtons cb;
    int screenWidth = 1920;
    int screenHeight = 1080;

    Rect rrRect;
    Rect lrRect;
    Rect rmRect;
    Rect lmRect;

    private static volatile controllers instance;

    private controllers()
    {
        cb = controllerButtons.getInstance();

        screenHeight =   levelActivity.getAppContext().getResources().getDisplayMetrics().heightPixels;
        screenWidth =  levelActivity.getAppContext().getResources().getDisplayMetrics().widthPixels;

        int buttonSize=200;

        int rightBound = screenWidth - buttonSize;
        int bottomBound = screenHeight - buttonSize;
        lrRect = new Rect(0,0,buttonSize,buttonSize);
        rrRect = new Rect( rightBound,0,rightBound + buttonSize, buttonSize);
        lmRect = new Rect( 0, bottomBound, buttonSize,bottomBound+buttonSize);
        rmRect = new Rect( rightBound,bottomBound,rightBound + buttonSize, bottomBound+buttonSize);
    }

    public static controllers getInstance()
    {
        if (instance == null) {
            synchronized(controllers.class) {
                if (instance == null) {
                    instance = new controllers();
                }
            }
        }
        return instance;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(cb.getBitmapLeftRotate(), null, lrRect, null);
        canvas.drawBitmap(cb.getBitmapRightRotate(), null, rrRect, null);
        canvas.drawBitmap(cb.getBitmapLeftMove(),null, lmRect, null);
        canvas.drawBitmap(cb.getBitmapRightMove(),null, rmRect,null);
    }

    public boolean touchRotateButton(int x, int y){
        if(lrRect.contains(x,y)) {
            levelAssets.getInstance().RotateLeft();
            return true;
        }
        if(rrRect.contains(x,y)) {
            levelAssets.getInstance().RotateRight();
            return true;
        }
        return false;
    }

    public boolean touchedMoveButton(int x, int y, boolean started)
    {

        if(started && lmRect.contains(x,y)) {
            //moveRight player left
            levelAssets.getInstance().playerObject.moveLeft(true);
            return true;
        }
        else
            levelAssets.getInstance().playerObject.moveLeft(false);
        if(started && rmRect.contains(x,y)) {
            //moveRight player right
            levelAssets.getInstance().playerObject.moveRight(true);
            return true;
        }
        else
            levelAssets.getInstance().playerObject.moveRight(false);
        return false;
    }
}
