package com.example.namebattler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.namebattler.database.CharacterInformation;

public class CharacterDetails extends AppCompatActivity {

    CharacterInformation helper = new CharacterInformation(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        SQLiteDatabase db = helper.getReadableDatabase();

        Intent intent = getIntent();

        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();


        for(int i = 0; i < cursor.getCount(); i++){

            if(intent.getStringExtra("name").equals(cursor.getString(0))){
                TextView text;

                text = findViewById(R.id.characterDetails_name);
                text.setText(cursor.getString(0));

                text = findViewById(R.id.characterDetails_job);
                text.setText(cursor.getString(1));

                text = findViewById(R.id.characterDetails_hp);
                text.setText(cursor.getString(2));

                text = findViewById(R.id.characterDetails_mp);
                text.setText(cursor.getString(3));

                text = findViewById(R.id.characterDetails_str);
                text.setText(cursor.getString(4));

                text = findViewById(R.id.characterDetails_def);
                text.setText(cursor.getString(5));

                text = findViewById(R.id.characterDetails_luck);
                text.setText(cursor.getString(6));

                text = findViewById(R.id.characterDetails_agi);
                text.setText(cursor.getString(7));

                text = findViewById(R.id.characterDetails_MakeDay);
                text.setText("作成日:" + cursor.getString(8));
                System.out.println(cursor.getString(8));
            }
            
            cursor.moveToNext();
        }


    }
}