package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.CursorTreeAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.GetCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;


public class CharacterList extends AppCompatActivity {

    Cursor cursor;
    int nowPlayerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        cursor = new GetCharacterData(getApplicationContext()).getAllData();
        nowPlayerNum = cursor.getCount();

        //タイトル表示
        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "キャラ一覧(" + nowPlayerNum + "人)", TopScreen.class);

        List<HashMap<String, String>> list = new ArrayList<>();
        addItem(list);

        addBlankItem(list);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_character_list_listview,
                new String[]{
                        "name",
                        "job",
                        "status"},
                new int[]{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status}
        );

        ListView listview = findViewById(R.id.listView_characterList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = view.findViewById(R.id.character_list_name);
                String name = text.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CharacterDetails.class);
                intent.putExtra("name", name);

                if (!name.equals("")) {
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.character_list_MakeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowPlayerNum < Option.makePlayerNum) {
                    Intent intent = new Intent(CharacterList.this, CharacterMake.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CharacterList.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addBlankItem(List<HashMap<String, String>> list) {
        if (nowPlayerNum < Option.makePlayerNum) {
            for (int i = 0; i < Option.makePlayerNum - nowPlayerNum; i++) {
                HashMap<String, String> hash = new HashMap<>();
                list.add(hash);
            }
        }
    }

    private void addItem(List<HashMap<String, String>> list) {
        if (cursor.moveToFirst()) {
            for (int i = 0; i < nowPlayerNum; i++) {
                HashMap<String, String> hash = new HashMap<>();
                hash.put("name", cursor.getString(cursor.getColumnIndex("NAME")));
                hash.put("job", AllJob.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getName());
                hash.put("status",
                        "HP:" + cursor.getString(cursor.getColumnIndex("HP")) +
                                " MP:" + cursor.getString(cursor.getColumnIndex("MP")) +
                                " STR:" + cursor.getString(cursor.getColumnIndex("STR")) +
                                " DEF:" + cursor.getString(cursor.getColumnIndex("DEF")) +
                                " LUCK:" + cursor.getString(cursor.getColumnIndex("LUCK")) +
                                " AGI:" + cursor.getString(cursor.getColumnIndex("AGI"))
                );
                list.add(hash);
                cursor.moveToNext();
            }
        }
    }

}