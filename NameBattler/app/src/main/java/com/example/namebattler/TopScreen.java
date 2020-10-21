package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.namebattler.program__9_7.P_Fighter;
import com.example.namebattler.program__9_7.Party;
import com.example.namebattler.program__9_7.Player;

import java.util.ArrayList;

public class TopScreen extends AppCompatActivity {

    //
    static Party party = new Party("");
    //
    ArrayList<Player> battlePlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_screen);

        Button characterList = findViewById(R.id.top_characterList);

        party.AppendPlayer(new P_Fighter("aaaaaaaaa"));


        characterList.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent intent = new Intent(
                       TopScreen.this, CharacterList.class);

               startActivity(intent);
           }
        });



    }
}