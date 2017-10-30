package com.project.cse5236.habitofgravity;

/**
 * Created by Brent on 10/29/2017.
 */

public class levelLoader {
    public static void LoadLevel(int l)
    {
        levelAssets la = levelAssets.getInstance();
        la.playerObject = new PlayerObject();
        la.blockList.clear();

        switch (l)
        {
            case 1:
                loadLevelOne();
                break;
            case 2:
                loadLevelTwo();
                break;
            case 3:
                loadLevelThree();
                break;
            default:
                loadLevelOne();
                break;
        }
    }

    private static void loadLevelOne() {
        levelAssets la = levelAssets.getInstance();
        la.blockList.add(new blockObject(300,300,50,100));
    }

    private static void loadLevelTwo() {
    }

    private static void loadLevelThree() {
    }
}
