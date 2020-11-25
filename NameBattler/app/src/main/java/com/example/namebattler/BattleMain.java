package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.name.battler.Player.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        Party party = null;



        makeGridView(R.id.battle_main_gridView_top, party);
        makeGridView(R.id.battle_main_gridView_bottom, party);

    }

    public void makeGridView(int layout, Party party){
        List<Map<String, String>> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Map<String, String> map = new HashMap<>();

//            map.put();
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.gridview_battle_main,
                new String[]{},
                new int[]{}
        );


        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);
    }


}