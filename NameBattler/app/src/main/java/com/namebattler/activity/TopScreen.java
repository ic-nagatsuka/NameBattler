package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.namebattler.R;


public class TopScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_screen);

        //キャラ一覧ボタン
        Button characterList = findViewById(R.id.top_characterList);
        characterList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //キャラクター一覧画面に遷移
                Intent intent = new Intent(
                        TopScreen.this, CharacterList.class);

                startActivity(intent);
            }
        });

        //バトル開始ボタン
        findViewById(R.id.top_Start_Battle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //パーティー編成画面に遷移
                Intent intent = new Intent(getApplication(), CharacterOrganization.class);
                startActivity(intent);
            }
        });


    }
}