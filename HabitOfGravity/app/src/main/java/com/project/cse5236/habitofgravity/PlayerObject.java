package com.project.cse5236.habitofgravity;

import android.graphics.Canvas;
import android.util.Log;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;
import com.project.cse5236.habitofgravity.BitmapSingletons.playerBitmap;

/**
 * Created by Brent on 10/29/2017.
 */

class PlayerObject extends levelObject {

    boolean moveRight =false;

    float dXLeft=0;
    float dXRight=0;
    float dY=0;

    float maxDY = 10f;
    float maxDX = 10f;

	boolean jumpBool = false;
	int jumpCounter = 0;

    PlayerObject(int xPos, int yPos)
    {
        int screenHeight =   ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().heightPixels;
        int screenWidth =  ContextHolder.getInstance().GetContext().getResources().getDisplayMetrics().widthPixels;

        if(screenHeight>screenWidth)
        {
            int t=screenWidth;
            screenWidth=screenHeight;
            screenHeight=t;
        }

        Log.d(this.toString(),"screenHeight: " + screenHeight);
        Log.d(this.toString(), "screenWidth: " + screenWidth);


        width =151;
        heigth =151;

        bitmap = playerBitmap.getInstance().getBitmap();

        //this.x=  ((int)((screenWidth-width)*0.5f));
        //this.y= ((int)((screenHeight-heigth)*0.5f));
        this.x=xPos;
        this.y=yPos;

        Log.d(this.toString(), "X: " + x + " Y: " +y);

        levelAssets.getInstance().CenterHeight =(int)(screenHeight *0.5f);
        levelAssets.getInstance().CenterWidth =(int)(screenWidth *0.5f);
    }

    public void update()
    {
        if(!(dXLeft!=0f && dXRight!= 0f))
            x+=dXLeft+dXRight;
		
		if(jumpBool) {
			dY = jumpCounter - 5;
			jumpCounter--;
			jumpBool = (jumpCounter == 0);
		}
		
        //y+= jumpBool ? dY : 0;
        y+=dY;
        moveRight=false;

        if(dXLeft<0)
            dXLeft-=1;
        if(dXRight>0)
            dXRight+=1;

        if(CollisionTester.onGround())
        {
            dY=0;
        }
        else
        {
            dY=1;
            dY = Math.max(dy,maxDY);
        }

        CollisionTester.testPlayerBlocks();
        CollisionTester.testPlayerGoal();

    }

    public void moveRight(boolean right)
    {
        if(dXRight!= 0 & !right)
            Log.d(this.toString(),"Resetting dXRight from:" +dXRight);
        if(right)
            dXRight=1;
        else
            dXRight=0;

    }

    public void moveLeft(boolean left)
    {
        if(dXLeft!= 0 & !left)
         Log.d(this.toString(),"Resetting dXLeft from:" +dXLeft);
        if(left)
            dXLeft=-1;
        else
            dXLeft=0;

    }
	
	public void jump(boolean jump) {
		if(!jumpBool) { 
			jumpCounter = jump ? 10 : 0;
			jumpBool = !jumpBool;
		}
	}
	
	

    public int getCenterX() {
        return x + ((int)((width+1)*0.5f));
    }

    public int getCenterY() {
        return y + ((int)((heigth+1)*0.5f));
    }


    public void shiftY(int shiftY) {
        dY=0;
        y+=shiftY;

    }

    public void shiftX(int shiftX) {
        dXLeft =0;
        dXRight =0;
        x+=shiftX;

    }
}
