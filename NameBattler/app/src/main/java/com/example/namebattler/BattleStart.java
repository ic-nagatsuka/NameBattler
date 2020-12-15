package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.name.battler.Player.AllJob;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.example.namebattler.CharacterMake.makePlayer;
import static com.name.battler.GameManager.enemyParty;
import static com.name.battler.GameManager.myParty;

public class BattleStart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

        final Random rand = new Random();

        if(enemyParty.getmenbers().size() != 0){
            enemyParty = new Party("敵");
        }

        EnemyNameData nameData = new EnemyNameData();
        for(int i = 0; i < 3; i++){
            enemyParty.appendPlayer(
                    makePlayer(
                            nameData.getEnemyName(),
                            AllJob.allJob[rand.nextInt(AllJob.allJob.length)],
                            enemyParty
                    )
            );
        }

        makeList(R.id.battle_start_listView_top, myParty);
        makeList(R.id.battle_start_listView_bottom, enemyParty);


        findViewById(R.id.battle_start_battleStar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), BattleMain.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.battle_start_reselect).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                enemyParty.getmenbers().clear();

                EnemyNameData enemyName = new EnemyNameData();
                for(int i = 0; i < 3; i++){
                    enemyParty.appendPlayer(
                            makePlayer(
                                    enemyName.getEnemyName(),
                                    AllJob.allJob[rand.nextInt(AllJob.allJob.length)],
                                    enemyParty
                            )
                    );
                }
                makeList(R.id.battle_start_listView_bottom, enemyParty);
            }
        });

        findViewById(R.id.battle_start_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplication(), CharacterOrganization.class);
                startActivity(intent);
            }
        });

    }



    public List<Map<String, String>> makeList(int listviewId, Party party){
        List<Map<String, String>> list = new ArrayList<>();
        for(Player player: party.getmenbers()){
            Map<String, String> map = new HashMap();
            map.put("name", player.getName());
            map.put("job", player.getJob());
            map.put("status", player.getstatus());
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_battle_start_listview,
                new String[]{"name", "job", "status"},
                new int []{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listview = findViewById(listviewId);
        listview.setAdapter(adapter);
        return list;
    }
}

class EnemyNameData{

    static Random rand = new Random();
    static ArrayList<EnemyData> enemies = new ArrayList<>();

    EnemyNameData(){
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

enum a{


//



}
