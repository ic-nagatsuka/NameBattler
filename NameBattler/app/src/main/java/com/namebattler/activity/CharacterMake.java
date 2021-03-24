package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.battle.player.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.namebattler.battle.GameManager;
import com.namebattler.database.GetCharacterData;
import com.namebattler.fragment.TitleFragment;

import static com.namebattler.option.Option.makePlayerNum;


public class CharacterMake extends AppCompatActivity implements TextWatcher {

    private final int radioButtonSize = 30;
    int nowPlayerNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make);

        nowPlayerNum = new GetCharacterData(getApplicationContext())
                .getAllData()
                .getCount();

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "キャラ作成", CharacterList.class);

        final EditText editText = findViewById(R.id.character_make_editText_name);
        editText.addTextChangedListener(this);


        //選択する職業を表示
        RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
        for (int i = 0; i < AllJob.values().length; i++) {
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(AllJob.values()[i].getName());
            radioBtn.setTextSize(radioButtonSize);
            radioBtn.setId(i);
            radioGroup.addView(radioBtn);
        }

        //作成するボタン
        findViewById(R.id.character_make_makeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
                EditText editName = findViewById(R.id.character_make_editText_name);

                String name = editName.getText().toString();
                RadioButton radio = findViewById(radioGroup.getCheckedRadioButtonId());
                if (nowPlayerNum >= makePlayerNum) {
                    //キャラクター最大数エラー表示
                    Toast.makeText(CharacterMake.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                } else if (!editName.getText().toString().equals("") && radioGroup.getCheckedRadioButtonId() != -1) {
                    Player player = GameManager.makePlayer(name, radio.getText().toString(), GameManager.myParty);

                    if (new GetCharacterData(getApplicationContext())
                            .setCharacter(player, radio.getId(), getDate()) != -1) {
                        //キャラクターデータ追加
                        nowPlayerNum++;
                        Intent intent = new Intent(getApplication(), CharacterMakeConpletion.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    } else {
                        //名前エラー表示
                        Toast.makeText(CharacterMake.this, "この名前はすでに存在しています", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });

    }

    //現在時刻を取得する
    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/dd hh:mm");
        return format.format(new Date());
    }

    /*=================
        TextWatcher
     =================*/
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