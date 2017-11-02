package com.project.cse5236.habitofgravity;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Brent on 11/2/2017.
 */

public class CollisionTester {
    public static void testPlayerBlocks()
    {
        levelAssets la = levelAssets.getInstance();

        if(la.playerObject != null)
        {
            for (blockObject b: la.getInstance().blockList)
            {
                testPlayerBlock(b, la.playerObject);
            }
        }
    }

    //if player touches goal game goes to end level end activity
    public static void testPlayerGoal()
    {
        levelAssets la = levelAssets.getInstance();

        if(la.goalObject != null  && la.playerObject != null)
        {
            if(la.goalObject.getRectangle().intersect(la.playerObject.getRectangle()))
            {
                //save score to singleton
                //move to new activity

                // the new activity can show the levels score
                // and have buttons to replay level, go to next level or exit
            }
        }
    }

    private static void testPlayerBlock(blockObject b, PlayerObject p)
    {
        //checks for top, left, bottom, right collision and shifts the player based off of the
        //overlap in Rect
        if(b.getRectangle().intersect(p.getRectangle()))
        {
            int topInter = b.getRectangle().bottom -p.getRectangle().top;
            int bottomInter = p.getRectangle().bottom-b.getRectangle().top;
            int leftInter = b.getRectangle().right - p.getRectangle().left;
            int rightInter = p.getRectangle().right - b.getRectangle().left;

            Log.d("Collision Top: ",""+topInter);
            Log.d("Collision Bottom: ",""+bottomInter);
            Log.d("Collision Left: ",""+leftInter);
            Log.d("Collision Right: ",""+rightInter);

            int maxYinter = Math.min(topInter,bottomInter);
            boolean topInt = (topInter == maxYinter);
            int maxXinter = Math.min(leftInter,rightInter);
            boolean leftInt = (leftInter == maxXinter);
            if( maxYinter < maxXinter)
            {
                //top or bottom intersection

                if(topInt)
                    p.shiftY(topInter);
                else
                    p.shiftY(-bottomInter);
            }
            else
            {
                //left or right intersection

                if(leftInt)
                    p.shiftX(leftInter);
                else
                    p.shiftX(-rightInter);
            }
        }
    }

    public static boolean onGround()
    {
        boolean output = false;

        levelAssets la = levelAssets.getInstance();
        if(la.playerObject != null)
        {
            Rect playerRect = la.playerObject.getRectangle();
            //rect below player
            Rect underFootRect = new Rect(playerRect.left, playerRect.bottom, playerRect.right, playerRect.bottom +1);

            for (blockObject b: la.getInstance().blockList)
            {
                if( b.getRectangle().intersect(underFootRect))
                    output=true;
            }
        }
        return output;
    }

}
