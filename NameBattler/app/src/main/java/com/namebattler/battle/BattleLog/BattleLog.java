package com.namebattler.battle.BattleLog;

import java.util.ArrayList;

public class BattleLog {

    public static ArrayList<String> logList = new ArrayList<>();

    public BattleLog(){

    }

    public static void clear(){
        logList = new ArrayList<>();
    }
    public static void addLog(String log){
        logList.add(log + "\n");
    }

    public static String getLogText(){
        String logText = "";
        for(String s: logList){
            logText += s;
        }


        return logText;
    }

}
