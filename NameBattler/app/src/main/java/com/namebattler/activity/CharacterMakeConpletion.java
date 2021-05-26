package com.namebattler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.OperationCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;


public class CharacterMakeConpletion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make_conpletion);

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "キャラ作成", null);

        String name = getIntent().getStringExtra("name");

        Cursor cursor = new OperationCharacterData(getApplicationContext())
                .getCharacter(name);

        if (cursor.moveToFirst()) {
            //名前
            TextView textView = findViewById(R.id.characterMakeConpletion_name);
            textView.setText(cursor.getString(cursor.getColumnIndex("NAME")));
            //職業
            textView = findViewById(R.id.characterMakeConpletion_job);
            textView.setText(
                    AllJob.JobData.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getJobName());
            //HP
            textView = findViewById(R.id.characterMakeConpletion_set_hp);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("HP"))));
            //MP
            textView = findViewById(R.id.characterMakeConpletion_set_mp);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("MP"))));
            //STR
            textView = findViewById(R.id.characterMakeConpletion_set_str);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("STR"))));
            //DEF
            textView = findViewById(R.id.characterMakeConpletion_set_def);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("DEF"))));
            //LUCK
            textView = findViewById(R.id.characterMakeConpletion_set_luck);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("LUCK"))));
            //AGI
            textView = findViewById(R.id.characterMakeConpletion_set_agi);
            textView.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("AGI"))));
        }


        //続けて作成するボタン
        findViewById(R.id.characterMakeConpletion_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nowPlayerNum = new OperationCharacterData(getApplicationContext())
                        .getCharacterCount();
                if (nowPlayerNum < Option.MAX_MAKE_PLAYER_NUM) {
                    //キャラクター作成画面に遷移
                    Intent intent = new Intent(getApplication(), CharacterMake.class);
                    startActivity(intent);
                } else {
                    //キャラクター最大数エラー表示
                    Toast.makeText(
                            CharacterMakeConpletion.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //作成を終了するボタン
        findViewById(R.id.characterMakeConpletion_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //キャラクター一覧画面に遷移
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });

    }
}