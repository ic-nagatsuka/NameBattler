package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.P_Priest;
import com.name.battler.Player.P_Wizard;
import com.name.battler.Player.Player;


public class CharacterMake extends AppCompatActivity implements TextWatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make);

        EditText editText= findViewById(R.id.character_make_editText_name);
        editText.addTextChangedListener(this);


        findViewById(R.id.character_make_makeButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
                EditText editName = findViewById(R.id.character_make_editText_name);

                String name = editName.getText().toString();

                if(!editName.getText().toString().equals("") && radioGroup.getCheckedRadioButtonId() != -1){
                    Player player = makePlayer(name, radioGroup.getCheckedRadioButtonId());
                    TopScreen.party.AppendPlayer(player);

                    Intent intent = new Intent(getApplication(), CharacterMakeSuccesScreen.class);
                    startActivity(intent);
                }
            }


        });

        findViewById(R.id.character_make_backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public Player makePlayer(String name, int job){
        Player player = null;
        switch(job){
            case R.id.character_make_radio_fighter: player = new P_Fighter(name);   break;
            case R.id.character_make_radio_wizard: player = new P_Wizard(name);    break;
            case R.id.character_make_radio_priest: player = new P_Priest(name);    break;
        }
        return player;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}