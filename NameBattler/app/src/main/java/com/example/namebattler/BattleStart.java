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
import com.name.battler.enemyData.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.name.battler.GameManager.enemyParty;
import static com.name.battler.GameManager.makePlayer;
import static com.name.battler.GameManager.myParty;

public class BattleStart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

        if(enemyParty.getmenbers().size() != 0){
            enemyParty = new Party("敵");
        }
        //敵パーティー作成
        makeEnemyParty();

        //自パーティー情報を表示
        displayParty(R.id.battle_start_listView_bottom, myParty);
        //敵パーティー情報を表示
        displayParty(R.id.battle_start_listView_top, enemyParty);


        findViewById(R.id.battle_start_battleStar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //バトルメイン画面に遷移
                Intent intent = new Intent(getApplication(), BattleMain.class);
                startActivity(intent);
            }
        });

        //敵パーティーの再作成
        findViewById(R.id.battle_start_reselect).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //敵パーティーを初期化
                enemyParty.getmenbers().clear();
                //敵パーティー作成
                makeEnemyParty();
                //敵パーティーの表示を更新
                displayParty(R.id.battle_start_listView_top, enemyParty);
            }
        });

        //戻るボタン
        findViewById(R.id.battle_start_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //パーティー編成画面に遷移
                Intent intent = new Intent(getApplication(), CharacterOrganization.class);
                startActivity(intent);
            }
        });

    }


    //敵パーティー作成
    private void makeEnemyParty() {
        Random rand = new Random();
        Enemy nameData = new Enemy();
        for (int i = 0; i < 3; i++) {
            enemyParty.appendPlayer(
                    makePlayer(
                            nameData.getEnemyName(),
                            AllJob.Job.values()[rand.nextInt(AllJob.Job.values().length)].getName(),
                            enemyParty
                    )
            );
        }
    }

    public void displayParty(int listviewId, Party party){
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
    }
}