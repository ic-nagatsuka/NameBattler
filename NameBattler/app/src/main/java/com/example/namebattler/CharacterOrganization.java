package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;
import com.name.battler.Player.Party;

import java.util.ArrayList;
import java.util.List;

import static com.name.battler.GameManager.myParty;

public class CharacterOrganization extends AppCompatActivity {

    CharacterInformation helper = new CharacterInformation(this);

    int charaCount = 0;
    int layout = R.layout.activity_character_organization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        SQLiteDatabase db = helper.getReadableDatabase();

        View startButtonView = findViewById(R.id.character_organization_start);

        if(myParty.getmenbers().size() != 0){
            myParty = new Party("味方");
        }

        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        List<Status> list = new ArrayList<>();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String name = cursor.getString(0);
            String job = AllJob.allJob[cursor.getInt(1)];
            String status = makeStatusText(cursor);
            list.add(new Status(name, job, status));
            cursor.moveToNext();
        }


        BaseAdapter adapter = new BaseAdapter_CharacterOrganization(
                this,
                startButtonView,
                list

        );

        ListView listView = findViewById(R.id.character_organization_ListView);
        listView.setAdapter(adapter);


        findViewById(R.id.character_organization_start).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(myParty.getmenbers().size() == 3){
                    Intent intent = new Intent(getApplication(), BattleStart.class);
                    startActivity(intent);
                }
            }
        });


        findViewById(R.id.character_organization_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplication(), TopScreen.class);
                startActivity(intent);
            }


        });

    }

    public String makeStatusText(Cursor cursor){

        String statusText = "HP" + cursor.getString(2) +
                " MP" + cursor.getString(3) +
                " STR" + cursor.getString(4) +
                " DEF" + cursor.getString(5) +
                " LUCK" + cursor.getString(6) +
                " AGI" + cursor.getString(7);
        return statusText;
    }


/*
テキストを変更してもレイアウトが変わらない
onCreateの外はレイアウト変更にViewが必要


 */

}

class Status{

    String name;
    String job;
    String status;

    Status(String name, String job, String status){
        this.name = name;
        this.job = job;
        this.status = status;

    }

    public String getName(){
        return this.name;
    }

    public String getJob(){
        return this.job;
    }

    public String getStatus(){
        return this.status;
    }



}