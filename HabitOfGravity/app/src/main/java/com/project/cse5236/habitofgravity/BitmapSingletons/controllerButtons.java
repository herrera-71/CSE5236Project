package com.project.cse5236.habitofgravity.BitmapSingletons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.R;
import com.project.cse5236.habitofgravity.levelActivity;

/**
 * Created by Brent on 10/25/2017.
 */

public class controllerButtons {
    Bitmap lr;
    Bitmap rr;
    Bitmap lm;
    Bitmap rm;


    private static volatile controllerButtons instance;

    private controllerButtons() {
        lr= BitmapFactory.decodeResource(levelActivity.getAppContext().getResources(), R.drawable.leftrotate);
        rr= BitmapFactory.decodeResource(levelActivity.getAppContext().getResources(), R.drawable.rightrotate);
        lm= BitmapFactory.decodeResource(levelActivity.getAppContext().getResources(), R.drawable.moveleft);
        rm= BitmapFactory.decodeResource(levelActivity.getAppContext().getResources(), R.drawable.moveright);
    }

    public static controllerButtons getInstance()
    {
        if (instance == null) {
            synchronized(controllerButtons.class) {
                if (instance == null) {
                    instance = new controllerButtons();
                }
            }
        }
        return instance;
    }

    public Bitmap getBitmapLeftRotate()
    {
        return lr;
    }
    public Bitmap getBitmapRightRotate()
    {
        return rr;
    }
    public Bitmap getBitmapLeftMove()
    {
        return lm;
    }
    public Bitmap getBitmapRightMove()
    {
        return rm;
    }
}
