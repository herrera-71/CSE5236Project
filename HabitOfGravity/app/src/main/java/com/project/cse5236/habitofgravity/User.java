package com.project.cse5236.habitofgravity;

import java.util.Map;

/**
 * Created by jonathanherrera on 10/30/17.
 */

public class User {
    public String username;
    public String password;
    //public String uniqueId;
    //public Map<Integer, String> highScoreList;

    public User(){

    }

    public User (String username, String password){
            //, Map<Integer, String> highScoreList){
        this.username = username;
        this.password = password;
        //this.uniqueId = uniqueId;
        //this.highScoreList = highScoreList;
    }

}
