package com.project.cse5236.habitofgravity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


public class levelActivity extends Activity {

    private static Context context;

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        levelAssets.getInstance().levelScreen=new levelScreen(this);
        setContentView( levelAssets.getInstance().levelScreen);

        levelActivity.context = getApplicationContext();

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        ContextHolder.getInstance().SetContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
         **/
        return super.onOptionsItemSelected(item);
    }

    public static Context getAppContext() {
        return levelActivity.context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x =event.getX();
        float y = event.getY();

        boolean swipe = this.mDetector.onTouchEvent(event);
        if(!swipe && event.getAction() == MotionEvent.ACTION_UP)
        {
            boolean bt = controllers.getInstance().touchRotateButton((int)x,(int)y);
            Log.d(this.toString(), "Location: "+ x + ", " + y + " Rotate ButtonTouched: " + bt);
        }
        else if(!swipe)
        {
            boolean bt = controllers.getInstance().touchedMoveButton((int)x,(int)y);
            Log.d(this.toString(), "Location: "+ x + ", " + y + " Move ButtonTouched: " + bt);
        }
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            //Log.d(DEBUG_TAG, "onFling: " + event1.toString());
            //Log.d(DEBUG_TAG, "onFling2: " + event2.toString());

            float dX = event1.getX() - event2.getX();
            if(Math.abs(dX)>300)
            {
                if(dX>0) {
                    levelAssets.getInstance().RotateLeft();
                    Log.d(DEBUG_TAG, "Left: " + dX);
                }
                else{
                    levelAssets.getInstance().RotateRight();
                    Log.d(DEBUG_TAG, "Right: " + dX);
                }
            }
            return true;
        }
    }
}
