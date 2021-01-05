package com.name.battler.BattleLog;

import java.util.ArrayList;

public class BattleLog {

    public static ArrayList<String> logList = new ArrayList<>();

    public BattleLog(){

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
