package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Strategy.AllStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        Party party = CharacterOrganization.party;
        Party enemyParty = BattleStart.enemyParty;
        makeGridView(R.id.battle_main_gridView_top, party);
        makeGridView(R.id.battle_main_gridView_bottom, enemyParty);


        TextView strategy = findViewById(R.id.battle_main_strategy_name);
//        AllStrategy.Strategies.values()[
        strategy.setText(
                party.getmenbers().get(0).getStrategy().getName()) ;
        

    }

    public void makeGridView(int layout, Party party){
        List<Map<String, String>> list = new ArrayList<>();
        for(Player player: party.getmenbers()){
            Map<String, String> map = new HashMap<>();

            map.put("name", player.getName());
            map.put("hp", "HP" + player.getMaxHp() + "/" + player.getHP());
            map.put("mp", "MP" + player.getMaxMp() + "/" + player.getMP());
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.gridview_battle_main,
                new String[]{
                        "name",
                        "hp",
                        "mp"
                },
                new int[]{
                        R.id.gridview_name,
                        R.id.gridview_hp,
                        R.id.gridview_mp
                }
        );


        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);
    }


}