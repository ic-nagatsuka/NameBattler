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

    private Cursor cursor;
    private int nowPlayerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        this.cursor = new OperationCharacterData(getApplicationContext()).getAllData();
        this.nowPlayerNum = this.cursor.getCount();

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
        if (this.nowPlayerNum < Option.MAX_MAKE_PLAYER_NUM) {
            for (int i = 0; i < Option.MAX_MAKE_PLAYER_NUM - this.nowPlayerNum; i++) {
                HashMap<String, String> hash = new HashMap<>();
                list.add(hash);
            }
        }
    }

    private void addItem(List<HashMap<String, String>> list) {
        if (this.cursor.moveToFirst()) {
            for (int i = 0; i < this.nowPlayerNum; i++) {
                HashMap<String, String> hash = new HashMap<>();
                hash.put("name", this.cursor.getString(this.cursor.getColumnIndex("NAME")));
                hash.put("job", AllJob.JobData.values()[this.cursor.getInt(this.cursor.getColumnIndex("JOB"))].getJobName());
                hash.put("status",
                        "HP:" + this.cursor.getString(this.cursor.getColumnIndex("HP")) +
                                " MP:" + this.cursor.getString(this.cursor.getColumnIndex("MP")) +
                                " STR:" + this.cursor.getString(this.cursor.getColumnIndex("STR")) +
                                " DEF:" + this.cursor.getString(this.cursor.getColumnIndex("DEF")) +
                                " LUCK:" + this.cursor.getString(this.cursor.getColumnIndex("LUCK")) +
                                " AGI:" + this.cursor.getString(this.cursor.getColumnIndex("AGI"))
                );
                list.add(hash);
                this.cursor.moveToNext();
            }
        }
    }

}