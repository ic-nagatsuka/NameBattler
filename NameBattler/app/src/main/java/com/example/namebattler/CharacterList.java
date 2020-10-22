package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class CharacterList extends AppCompatActivity {

    int listNum = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Button charaMake = findViewById(R.id.character_list_MakeButton);
        charaMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CharacterList.this, CharacterMake.class);

                startActivity(intent);
            }
        });

        Party party = TopScreen.party;

        List<HashMap<String, String>> playerData = new ArrayList<>();

        for(Player player : party.Getmenbers()){
            HashMap<String, String> hash = new HashMap<>();
            hash.put("name", player.GetName());
            hash.put("job", "job");
            hash.put("status", player.getStatus());

            playerData.add(hash);
        }

        if(party.Getmenbers().size() < listNum){

            for(int i = 0; i < listNum - party.Getmenbers().size(); i++){
                HashMap<String, String> hash = new HashMap<>();

                hash.put("name", "");
                hash.put("job", "");
                hash.put("status", "");

                playerData.add(hash);
            }

        }


        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                playerData,
                R.layout.activity_character_list_listview,
                new String[]{
                        "name",
                        "job",
                        "status"},
                new int[]{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listView = findViewById(R.id.listView_characterList);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplication(), CharacterDetails.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.character_list_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}