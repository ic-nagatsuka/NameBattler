package com.namebattler;

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
import android.widget.Toast;

import com.namebattler.database.CharacterInformation;
import com.namebattler.battle.Player.AllJob;
import com.namebattler.battle.Player.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.namebattler.battle.GameManager;

import static com.namebattler.CharacterList.nowPlayerNum;
import static com.namebattler.battle.Option.Option.makePlayerNum;


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

        for(int i = 0; i < AllJob.Job.values().length; i++){
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(AllJob.Job.values()[i].getName());
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
                if(nowPlayerNum >= makePlayerNum ){
                    Toast.makeText(CharacterMake.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                }else if(!editName.getText().toString().equals("") && radioGroup.getCheckedRadioButtonId() != -1){
                    SQLiteDatabase db = helper.getWritableDatabase();
                    player = GameManager.makePlayer(name, radio.getText().toString(), GameManager.myParty);

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

                    if(db.insert(CharacterInformation.TABLE_NAME,null, values) != -1){
                        nowPlayerNum++;
                        Intent intent = new Intent(getApplication(), CharacterMakeConpletion.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(CharacterMake.this, "この名前はすでに存在しています", Toast.LENGTH_SHORT).show();
                    }

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