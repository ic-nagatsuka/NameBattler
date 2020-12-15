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

        Enemy nameData = new Enemy();
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

                Enemy enemyName = new Enemy();
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