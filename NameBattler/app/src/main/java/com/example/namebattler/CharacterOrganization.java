package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.namebattler.database.CharacterInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CharacterOrganization extends AppCompatActivity {

    CharacterInformation helper = new CharacterInformation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_organization);

        SQLiteDatabase db = helper.getReadableDatabase();


        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        List<HashMap<String, String>> list = new ArrayList<>();

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            HashMap<String, String> map = new HashMap<>();

            map.put("name", cursor.getString(0));
            map.put("job", cursor.getString(1));
            map.put("status", makeStatusText(cursor));

            list.add(map);
            cursor.moveToNext();
        }


        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.listview_character_organization,
                new String[]{"name", "job", "status"},
                new int[]{
                        R.id.character_organization_listView_status_name,
                        R.id.character_organization_listView_status_job,
                        R.id.character_organization_listView_status
                }
        );

        ListView listView = findViewById(R.id.character_organization_ListView);
        listView.setAdapter(adapter);


        findViewById(R.id.character_organization_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }


        });




    }

    public String makeStatusText(Cursor cursor){

        String statusText = "HP" + cursor.getString(2) +
                "MP" + cursor.getString(3) +
                "STR" + cursor.getString(4) +
                "DEF" + cursor.getString(5) +
                "LUCK" + cursor.getString(6) +
                "AGI" + cursor.getString(7);
        return statusText;
    }


}