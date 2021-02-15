package com.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.namebattler.battle.Player.AllJob;
import com.namebattler.battle.Player.Party;
import com.namebattler.battle.Player.Player;
import com.namebattler.battle.enemyData.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.namebattler.battle.GameManager;

public class BattleStart extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

        final Random rand = new Random();

        if(GameManager.enemyParty.getmenbers().size() != 0){
            GameManager.enemyParty = new Party("敵");
        }

        Enemy nameData = new Enemy();
        for(int i = 0; i < 3; i++){
            GameManager.enemyParty.appendPlayer(
                    GameManager.makePlayer(
                            nameData.getEnemyName(),
                            AllJob.Job.values()[rand.nextInt(AllJob.Job.values().length)].getName(),
                            GameManager.enemyParty
                    )
            );
        }

        makeList(R.id.battle_start_listView_bottom, GameManager.myParty);
        makeList(R.id.battle_start_listView_top, GameManager.enemyParty);


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
                GameManager.enemyParty.getmenbers().clear();

                Enemy enemyName = new Enemy();
                for(int i = 0; i < 3; i++){
                    GameManager.enemyParty.appendPlayer(
                            GameManager.makePlayer(
                                    enemyName.getEnemyName(),
                                    AllJob.Job.values()[rand.nextInt(AllJob.Job.values().length)].getName(),
                                    GameManager.enemyParty
                            )
                    );
                }
                makeList(R.id.battle_start_listView_top, GameManager.enemyParty);
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