package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class CharacterList extends AppCompatActivity {

    int listNum = 7;

//    SQLiteDatabase db = new CharacterInformation(this).getReadableDatabase();

    CharacterInformation helper = new CharacterInformation(this);

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

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                CharacterInformation.TABLE_NAME,
                new String[]{"name"},
                null,
                null,
                null,
                null,
                null
                );
        cursor.moveToFirst();

        List<HashMap<String, String>> list = new ArrayList<>();
        for(int i = 0; i < cursor.getCount(); i++){
            HashMap<String, String> hash = new HashMap<>();
            hash.put("name", cursor.getString(cursor.getColumnIndex("NAME")));
//            hash.put("job", AllJob.allJob[1]. );
//            hash.put("status", cursor.getString(cursor.getColumnIndex("hp"))
////                    cursor.getString(cursor.getColumnIndex(""))
//            );
            list.add(hash);
            cursor.moveToNext();
        }

        if(cursor.getCount() < listNum){
            for(int i = 0; i < 5; i++){
                HashMap<String, String> hash = new HashMap<>();
                hash.put("name", "");
                hash.put("job", "");
                hash.put("status", "");
                list.add(hash);
            }

        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_character_list_listview,
                new String[]{
                        "name",
                        "job",
                        "status"
                },
                new int[]{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listview = findViewById(R.id.listView_characterList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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