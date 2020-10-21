package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharacterList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Party party = TopScreen.party;

        List<HashMap<String, String>> playerData = new ArrayList<>();

        if(TopScreen.party.Getmenbers().size() != 0){


            for(Player player : party.Getmenbers()){

                HashMap<String, String> hash = new HashMap<>();
//                Integer.toString()
                hash.put("name", player.GetName());
                hash.put("job", "job");
                hash.put("status", player.getStatus());

                playerData.add(hash);

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


        }
















    }
}