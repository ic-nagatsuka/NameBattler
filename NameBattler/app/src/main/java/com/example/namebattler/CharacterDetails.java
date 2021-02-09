package com.example.namebattler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;

import static com.example.namebattler.CharacterList.nowPlayerNum;

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

        //キャラクターデータを表示
        for(int i = 0; i < cursor.getCount(); i++){

            if(intent.getStringExtra("name").equals(cursor.getString(0))){
                TextView text;
                //名前
                text = findViewById(R.id.characterDetails_name);
                text.setText(cursor.getString(0));
                //職業
                text = findViewById(R.id.characterDetails_job);
                text.setText(AllJob.Job.values()[cursor.getInt(1)].getName());
                //HP
                text = findViewById(R.id.characterDetails_hp);
                text.setText(cursor.getString(2));
                //MP
                text = findViewById(R.id.characterDetails_mp);
                text.setText(cursor.getString(3));
                //STR
                text = findViewById(R.id.characterDetails_str);
                text.setText(cursor.getString(4));
                //DEF
                text = findViewById(R.id.characterDetails_def);
                text.setText(cursor.getString(5));
                //LUCK
                text = findViewById(R.id.characterDetails_luck);
                text.setText(cursor.getString(6));
                //AGI
                text = findViewById(R.id.characterDetails_agi);
                text.setText(cursor.getString(7));
                //作成日
                text = findViewById(R.id.characterDetails_MakeDay);
                text.setText("作成日 : " + cursor.getString(8));
            }
            cursor.moveToNext();
        }

        //削除ボタン
        findViewById(R.id.characterDetails_DeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //キャラクターを削除
                db.delete(
                        CharacterInformation.TABLE_NAME,
                        "name = ?",
                        new String[] {intent.getStringExtra("name")});

                nowPlayerNum--;
                //キャラクター一覧画面に遷移
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });

        //戻るボタン
        findViewById(R.id.characterDetails_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //キャラクター一覧画面に遷移
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });


    }

}