package com.project.cse5236.habitofgravity;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;

import java.util.ArrayList;

/**
 * Created by Brent on 10/25/2017.
 */

public class levelAssets {

    private static volatile levelAssets instance;
    private levelAssets()
    {

    }
    public static levelAssets getInstance()
    {
        if (instance == null) {
            synchronized(levelAssets.class) {
                if (instance == null) {
                    instance = new levelAssets();
                }
            }
        }
        return instance;
    }

    public levelScreen levelScreen;

    public PlayerObject playerObject;

    public ArrayList<blockObject> blockList = new ArrayList<>();

    public int CenterHeight;
    public int CenterWidth;

    public int xOffset=0;
    public int yOffset=0;

    public void update() {
        //update background;
        levelScreen.update();

        //player;
        playerObject.update();


        //calculate offsets;
        xOffset = CenterWidth - playerObject.getCenterX();
        yOffset = CenterHeight - playerObject.getCenterY();
    }

    public void draw(Canvas canvas) {
        //drawbackground
        levelScreen.draw(canvas);

        //draw blocks
        for (blockObject b: blockList) {
            b.draw(canvas);
        }


        //draw player
        if(playerObject != null) {
            //Log.d(this.toString(),"Starting player Drew player");
            playerObject.draw(canvas);
            //Log.d(this.toString(),"Drew player");
        }
        else
            Log.d(this.toString(),"playerObject is null");

        //draw controllers
        controllers.getInstance().draw(canvas);
    }

    public void RotateLeft()
    {
        for (blockObject b: blockList) {
            b.RotateLeft(playerObject.getRectangle());
        }
    }

    public void RotateRight()
    {
        for (blockObject b: blockList) {
            b.RotateRight(playerObject.getRectangle());
        }
    }
}
