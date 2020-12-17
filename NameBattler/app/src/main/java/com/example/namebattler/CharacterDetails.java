package com.example.namebattler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;

public class CharacterDetails extends AppCompatActivity {

    CharacterInformation helper = new CharacterInformation(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        final SQLiteDatabase db = helper.getWritableDatabase();

        final Intent intent = getIntent();

        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){

            if(intent.getStringExtra("name").equals(cursor.getString(0))){
                TextView text;

                text = findViewById(R.id.characterDetails_name);
                text.setText(cursor.getString(0));

                text = findViewById(R.id.characterDetails_job);
                text.setText(AllJob.Job.values()[cursor.getInt(1)].getName());

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
                text.setText("作成日 : " + cursor.getString(8));
            }
            cursor.moveToNext();
        }

        findViewById(R.id.characterDetails_DeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(
                        CharacterInformation.TABLE_NAME,
                        "name = ?",
                        new String[] {intent.getStringExtra("name")});
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.characterDetails_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });


    }

        public void onClickBack(View v){
            finish();
        }

}