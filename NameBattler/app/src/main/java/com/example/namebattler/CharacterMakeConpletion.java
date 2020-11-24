package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.name.battler.Player.Player;

public class CharacterMakeConpletion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make_conpletion);


        Player player = CharacterMake.player;
        TextView textView ;

        textView = findViewById(R.id.characterMakeConpletion_name);
        textView.setText(player.getName());

        textView = findViewById(R.id.characterMakeConpletion_job);
        textView.setText(player.getJob());

        textView = findViewById(R.id.characterMakeConpletion_set_hp);
        textView.setText(Integer.toString(player.getHP()));

        textView = findViewById(R.id.characterMakeConpletion_set_mp);
        textView.setText(Integer.toString(player.getMP()));

        textView = findViewById(R.id.characterMakeConpletion_set_str);
        textView.setText(Integer.toString(player.getSTR()));

        textView = findViewById(R.id.characterMakeConpletion_set_def);
        textView.setText(Integer.toString(player.getDEF()));

        textView = findViewById(R.id.characterMakeConpletion_set_agi);
        textView.setText(Integer.toString(player.getAGI()));

        textView = findViewById(R.id.characterMakeConpletion_set_luck);
        textView.setText(Integer.toString(player.getLUCK()));



        findViewById(R.id.characterMakeConpletion_continue).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplication(), CharacterMake.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.characterMakeConpletion_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });

    }
}