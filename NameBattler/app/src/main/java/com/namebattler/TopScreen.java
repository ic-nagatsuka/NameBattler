package com.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



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