package com.project.cse5236.habitofgravity;

/**
 * Created by jonathanherrera on 11/6/17.
 */

public class UserStoredData {
    public String ID;
    public String Lvl1Highscore;
    public String Lvl2Highscore;
    public String Lvl3Highscore;

    public UserStoredData(){

    }

    public UserStoredData(String uid, String lvl1Score, String lvl2Score, String lvl3Score){
        ID =uid;
        Lvl1Highscore = lvl1Score;
        Lvl2Highscore = lvl2Score;
        Lvl3Highscore = lvl3Score;
    }

}
