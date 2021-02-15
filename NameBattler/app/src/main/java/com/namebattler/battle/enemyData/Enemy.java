package com.namebattler.battle.enemyData;

import java.util.ArrayList;
import java.util.Random;

public class Enemy{
    Random rand = new Random();

    ArrayList<EnemyData> enemies = new ArrayList<>();

    public Enemy(){
        resetName();
    }

    public String getEnemyName(){

        return enemies.remove(rand.nextInt(enemies.size())).getName();
    }

    public void resetName(){
        enemies.clear();

        for(EnemyData enemy: EnemyData.values()){
            enemies.add(enemy);
        }
    }

    public enum EnemyData {
        name1("ドリアール"),        name2("アーミュー"),
        name3("ジャスカー"),        name4("トバイモン"),
        name5("ジャイシー"),        name6("ベネテリー"),
        name7("ゲイブラッド"),        name8("デーヴィッド"),
        name9("ニコラリー"),        name10("ジョナンド"),
        name11("パトリック"),        name12("ルフレット"),
        name13("クスタント"),        name14("ホレス"),
        name15("フェビアン"),        name16("アーローム"),
        name17("ヴァレッド"),        name18("ルドウエン"),
        name19("エセルイス"),        name20("コーニエル"),
        name21("モイモレク"),        name22("ルコシエル"),
        name23("ルーレプト"),        name24("ラシアダド"),
        name25("ベザル"),        name26("アメルカス"),
        name27("アムニエン"),        name28("オロバリル"),
        name29("ウァサゴー"),        name30("ベリアモン"),
        name31("エギビゴル"),        name32("アドラース"),
        name33("フィステマ"),        name34("ダンタムズ"),
        name35("ウリクサス"),        name36("ベルファス"),
        name37("リバイモン"),        name38("ウェパズズ"),
        name39("アグナック"),        name40("セエレ"),
        name41("ダイアニー"),        name42("シャローナ"),
        name43("ドライーズ"),        name44("リンジャー"),
        name45("カーラ"),        name46("リザベティ"),
        name47("ケイ"),        name48("アントニア"),
        name49("ブリジェマ"),        name50("キャエレン"),
        name51("ローレイン"),        name52("ジョセアラ"),
        name53("ディアリー"),        name54("コール"),
        name55("エルヴィラ"),        name56("アトリエット"),
        name57("アイヴィス"),        name58("ヴィヴェラ"),
        name59("クラリーナ"),        name60("ダーリジット"),
        name61("ヴェランコ"),        name62("ヴェネデット"),
        name63("エラルタコ"),        name64("ターヴィオ"),
        name65("アンニコラ"),        name66("ベネディオ"),
        name67("ティアーノ"),        name68("サラディオ"),
        name69("ルフレート"),        name70("ルメネーア"),
        name71("アンセスト"),        name72("アポリスタ"),
        name73("ミントーレ"),        name74("アンセルモ"),
        name75("バルダンテ"),        name76("アナスパレ"),
        name77("ルクレンゾ"),        name78("ジルベルト"),
        name79("ヴァレーモ"),        name80("ファエーレ");


        String name;
        EnemyData(String name){
            this.name = name;
        }

        String getName(){
            return this.name;
        }

    }
}
