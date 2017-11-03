package com.project.cse5236.habitofgravity;

/**
 * Created by Brent on 10/29/2017.
 */

public class levelLoader {
    public static void LoadLevel(int l)
    {
        levelAssets la = levelAssets.getInstance();
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
        la.playerObject = new PlayerObject(821,646);
        la.blockList.add(new blockObject(0,0,1500,100));
        la.blockList.add(new blockObject(0,0,100,1500));
        la.blockList.add(new blockObject(1400,0,1500,100));
        la.blockList.add(new blockObject(0,1400,100,1500));
        la.goalObject = new goalObject(1200,200,200,200);

        la.currentLevel=1;
        la.nextLevel=2;
    }

    private static void loadLevelTwo() {
        levelAssets la = levelAssets.getInstance();
        la.playerObject = new PlayerObject(821,646);
        la.blockList.add(new blockObject(0,0,1500,100));
        la.blockList.add(new blockObject(0,0,100,1500));
        la.blockList.add(new blockObject(1400,0,1500,100));
        la.blockList.add(new blockObject(0,1400,100,1500));
        la.goalObject = new goalObject(1200,200,200,200);

        la.currentLevel=2;
        la.nextLevel=3;
    }

    private static void loadLevelThree() {
        levelAssets la = levelAssets.getInstance();
        la.playerObject = new PlayerObject(821,646);
        la.blockList.add(new blockObject(0,0,1500,100));
        la.blockList.add(new blockObject(0,0,100,1500));
        la.blockList.add(new blockObject(1400,0,1500,100));
        la.blockList.add(new blockObject(0,1400,100,1500));
        la.goalObject = new goalObject(1200,200,200,200);

        la.currentLevel=3;
        la.nextLevel=1;
    }
}
