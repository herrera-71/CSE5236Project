package com.project.cse5236.habitofgravity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Brent on 10/15/2017.
 */

public class levelScreen extends SurfaceView implements SurfaceHolder.Callback{

    public static int screenWidth = 1920;
    public static int screenHeight = 1080;

    private levelBackground background;
    private levelThread levelThread;

    public levelScreen(Context context)
    {
        super(context);

        screenHeight =  getResources().getDisplayMetrics().heightPixels;
        screenWidth =  getResources().getDisplayMetrics().widthPixels;

        getHolder().addCallback(this);

        levelThread = new levelThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{
                levelThread.setRunning(false);
                levelThread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        background = new levelBackground(BitmapFactory.decodeResource(getResources(), R.drawable.whitecararaslab01));

        levelThread.setRunning(true);
        levelThread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }
    public void update()
    {
        background.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(screenWidth *1f);
        final float scaleFactorY = getHeight()/(screenHeight *1f);
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
