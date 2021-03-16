package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.namebattler.R;
import com.namebattler.battle.player.Player;
import com.namebattler.fragment.TitleFragment;

import static com.namebattler.activity.CharacterList.nowPlayerNum;
import static com.namebattler.option.Option.makePlayerNum;

public class CharacterMakeConpletion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make_conpletion);

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "キャラ作成", false, null);

        Player player = CharacterMake.player;   //作成したキャラクター
        //キャラクターデータを表示する
        TextView textView;
        //名前
        textView = findViewById(R.id.characterMakeConpletion_name);
        textView.setText(player.getName());
        //職業
        textView = findViewById(R.id.characterMakeConpletion_job);
        textView.setText(player.getJob());
        //HP
        textView = findViewById(R.id.characterMakeConpletion_set_hp);
        textView.setText(Integer.toString(player.getHP()));
        //MP
        textView = findViewById(R.id.characterMakeConpletion_set_mp);
        textView.setText(Integer.toString(player.getMP()));
        //STR
        textView = findViewById(R.id.characterMakeConpletion_set_str);
        textView.setText(Integer.toString(player.getSTR()));
        //DEF
        textView = findViewById(R.id.characterMakeConpletion_set_def);
        textView.setText(Integer.toString(player.getDEF()));
        //AGI
        textView = findViewById(R.id.characterMakeConpletion_set_agi);
        textView.setText(Integer.toString(player.getAGI()));
        //LUCK
        textView = findViewById(R.id.characterMakeConpletion_set_luck);
        textView.setText(Integer.toString(player.getLUCK()));

        //続けて作成するボタン
        findViewById(R.id.characterMakeConpletion_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nowPlayerNum < makePlayerNum) {
                    //キャラクター作成画面に遷移
                    Intent intent = new Intent(getApplication(), CharacterMake.class);
                    startActivity(intent);
                } else {
                    //キャラクター最大数エラー表示
                    Toast.makeText(CharacterMakeConpletion.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
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