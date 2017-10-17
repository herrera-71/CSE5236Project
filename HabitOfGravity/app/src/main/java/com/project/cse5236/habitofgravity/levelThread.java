package com.project.cse5236.habitofgravity;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Brent on 10/15/2017.
 */

public class levelThread extends Thread
{
    private final int idealFPS = 30;
    private final float NanoPerMilli =1000000f;
    private final float MilliPerOne =1000f;
    private SurfaceHolder surfaceHolder;
    private levelScreen levelScreen;
    private boolean running;
    public static Canvas canvas;



    public levelThread(SurfaceHolder surfaceHolder, levelScreen levelScreen)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.levelScreen = levelScreen;
    }
    @Override
    public void run()
    {
        long startTime;
        float deltaTime;
        long sleepTime;
        float idealDeltaTime = MilliPerOne/ idealFPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.levelScreen.update();
                    this.levelScreen.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            deltaTime = (System.nanoTime() - startTime) / NanoPerMilli;
            sleepTime = (long)(idealDeltaTime-deltaTime);

            try{
                this.sleep(sleepTime);
            }catch(Exception e){e.printStackTrace();}
        }
    }
    public void setRunning(boolean b)
    {
        running=b;
    }
}
