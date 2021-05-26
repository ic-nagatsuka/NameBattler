package com.namebattler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.OperationCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CharacterList extends AppCompatActivity {

    Cursor cursor;
    int nowPlayerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        cursor = new OperationCharacterData(getApplicationContext()).getAllData();
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
                R.layout.listview_character_list,
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
                TextView textView = view.findViewById(R.id.character_list_name);
                String name = textView.getText().toString();
                if (!name.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), CharacterDetails.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.character_list_MakeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowPlayerNum < Option.MAX_MAKE_PLAYER_NUM) {
                    Intent intent = new Intent(CharacterList.this, CharacterMake.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            CharacterList.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addBlankItem(List<HashMap<String, String>> list) {
        if (nowPlayerNum < Option.MAX_MAKE_PLAYER_NUM) {
            for (int i = 0; i < Option.MAX_MAKE_PLAYER_NUM - nowPlayerNum; i++) {
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
                hash.put("job", AllJob.JobData.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getJobName());
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