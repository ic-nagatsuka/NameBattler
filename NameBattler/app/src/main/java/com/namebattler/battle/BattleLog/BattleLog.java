package com.namebattler.battle.BattleLog;

import java.util.ArrayList;

public class BattleLog {

    //バトルテキストを入れる
    public static ArrayList<String> logList = new ArrayList<>();

    //コンストラクタ
    public BattleLog(){

    }

    //初期化する
    public static void clear(){
        logList = new ArrayList<>();
    }
    //テキストを追加する
    public static void addLog(String log){
        logList.add(log + "\n");
    }
    //文字列をまとめて返す
    public static String getLogText(){
        String logText = "";
        for(String s: logList){
            logText += s;
        }


        return logText;
    }

}
