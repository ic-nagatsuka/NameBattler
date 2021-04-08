package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.namebattler.battle.GameManager;
import com.namebattler.battle.player.AllJob;
import com.namebattler.option.Option;
import com.namebattler.R;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.enemydata.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class BattleStart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "バトル開始", CharacterOrganization.class);

        if (GameManager.enemyParty.getmenbers().size() != 0) {
            GameManager.enemyParty = new Party("敵");
        }
        //敵パーティー作成
        makeEnemyParty();

        //自パーティー情報を表示
        displayParty(R.id.battle_start_listView_bottom, GameManager.myParty);
        //敵パーティー情報を表示
        displayParty(R.id.battle_start_listView_top, GameManager.enemyParty);


        findViewById(R.id.battle_start_battleStar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //バトルメイン画面に遷移
                Intent intent = new Intent(getApplication(), BattleMain.class);
                startActivity(intent);
            }
        });

        //敵パーティーの再作成
        findViewById(R.id.battle_start_reselect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //敵パーティーを初期化
                GameManager.enemyParty.getmenbers().clear();
                //敵パーティー作成
                makeEnemyParty();
                //敵パーティーの表示を更新
                displayParty(R.id.battle_start_listView_top, GameManager.enemyParty);
            }
        });

    }


    //敵パーティー作成
    private void makeEnemyParty() {
        Random rand = new Random();
        Enemy nameData = new Enemy();
        for (int i = 0; i < Option.partyPlayerNum; i++) {
            GameManager.enemyParty.appendPlayer(
                    GameManager.makePlayer(
                            nameData.getEnemyName(),
                            AllJob.values()[rand.nextInt(AllJob.values().length)].getName(),
                            GameManager.enemyParty
                    )
            );
        }
    }

    public void displayParty(int listviewId, Party party) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Player player : party.getmenbers()) {
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
                new int[]{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listview = findViewById(listviewId);
        listview.setAdapter(adapter);
    }
}