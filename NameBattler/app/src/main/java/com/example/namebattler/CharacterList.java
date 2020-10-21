package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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