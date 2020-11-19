package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_screen);

        Button characterList = findViewById(R.id.top_characterList);
        characterList.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent intent = new Intent(
                       TopScreen.this, CharacterList.class);

               startActivity(intent);
           }
        });


        findViewById(R.id.top_Start_Battle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CharacterOrganization.class);
                startActivity(intent);
            }
        });


    }
}