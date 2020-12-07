package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.namebattler.database.CharacterInformation;
import com.name.battler.Player.AllJob;
import com.name.battler.Player.P_Bouncer;
import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.P_Priest;
import com.name.battler.Player.P_Wizard;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Strategy.AllStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CharacterMake extends AppCompatActivity implements TextWatcher {

    CharacterInformation helper = new CharacterInformation(this);

    static Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make);

        final EditText editText= findViewById(R.id.character_make_editText_name);
        editText.addTextChangedListener(this);


        RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup) ;

        for(int i = 0; i < AllJob.allJob.length; i++){
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(AllJob.allJob[i]);
            radioBtn.setTextSize(30);
            radioBtn.setId(i);
            radioGroup.addView(radioBtn);
        }









        findViewById(R.id.character_make_makeButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
                EditText editName = findViewById(R.id.character_make_editText_name);

                String name = editName.getText().toString();
                RadioButton radio = findViewById(radioGroup.getCheckedRadioButtonId());

                if(!editName.getText().toString().equals("") && radioGroup.getCheckedRadioButtonId() != -1){
                    SQLiteDatabase db = helper.getWritableDatabase();
                    player = makePlayer(name, radio.getText().toString(), CharacterOrganization.party);

                    ContentValues values = new ContentValues();
                    values.put("NAME",  player.getName());
                    values.put("JOB",   radioGroup.getCheckedRadioButtonId());
                    values.put("HP",    player.getHP());
                    values.put("MP",    player.getMP());
                    values.put("STR",   player.getSTR());
                    values.put("DEF",   player.getDEF());
                    values.put("LUCK",  player.getLUCK());
                    values.put("AGI",   player.getAGI());
                    values.put("CREATE_AT", getDate());

                    db.insert(CharacterInformation.TABLE_NAME,null, values);


                    Intent intent = new Intent(getApplication(), CharacterMakeConpletion.class);
                    startActivity(intent);
                }
            }


        });


        findViewById(R.id.character_make_backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });


    }

    public String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/dd hh:mm") ;
        return format.format(new Date());
    }


    public static Player makePlayer(String name, String job, Party party){
        Player player = null;
        switch(job){
            case "戦士"   : player = new P_Fighter(name);break;
            case "魔法使い" :player = new P_Wizard(name); break;
            case "僧侶"   : player = new P_Priest(name); break;
            case "ボール"  : player = new P_Bouncer(name); break;
        }

        System.out.println("プレイヤー作成パーティー" + CharacterOrganization.party);

        player.setParty(party);
        player.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());

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