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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;

import static com.name.battler.Option.Option.makePlayerNum;

public class CharacterList extends AppCompatActivity {

    public static int nowPlayerNum;

    CharacterInformation helper = new CharacterInformation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        findViewById(R.id.character_list_MakeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nowPlayerNum < makePlayerNum){
                    Intent intent = new Intent(CharacterList.this, CharacterMake.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(CharacterList.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();;
                }
            }
        });

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                CharacterInformation.TABLE_NAME,
                new String[]{
                        "NAME",
                        "JOB",
                        "HP",
                        "MP",
                        "STR",
                        "DEF",
                        "LUCK",
                        "AGI",
                        "CREATE_AT"
                },
                null,
                null,
                null,
                null,
                null
                );

        TextView text = findViewById(R.id.character_list_title);
        text.setText("キャラ一覧("+ cursor.getCount() + "人)");


        nowPlayerNum = cursor.getCount();

        List<HashMap<String, String>> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            for(int i = 0; i < cursor.getCount(); i++){
                HashMap<String, String> hash = new HashMap<>();
                hash.put("name", cursor.getString(cursor.getColumnIndex("NAME")));
                hash.put("job", AllJob.Job.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getName());
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

        if(cursor.getCount() < makePlayerNum){
            for(int i = 0; i < makePlayerNum - cursor.getCount(); i++){
                HashMap<String, String> hash = new HashMap<>();
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
                TextView text = view.findViewById( R.id.character_list_name);
                String name = text.getText().toString();

                if(!name.equals("")){
                    Intent intent = new Intent(getApplicationContext(), CharacterDetails.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.character_list_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TopScreen.class);
                startActivity(intent);
            }
        });


    }
}