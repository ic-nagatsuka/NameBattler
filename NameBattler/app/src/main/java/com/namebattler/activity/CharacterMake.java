package com.namebattler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.GameManager;
import com.namebattler.battle.player.AllJob;
import com.namebattler.battle.player.Player;
import com.namebattler.database.OperationCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CharacterMake extends AppCompatActivity {

    private final int radioButtonSize = 30;
    private int nowPlayerNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_make);

        this.nowPlayerNum = new OperationCharacterData(getApplicationContext())
                .getCharacterCount();

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "キャラ作成", CharacterList.class);

        showSelectjobItem();

        //作成するボタン
        findViewById(R.id.character_make_makeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
                EditText editName = findViewById(R.id.character_make_editText_name);
                String name = editName.getText().toString();
                if (nowPlayerNum >= Option.MAX_MAKE_PLAYER_NUM) {
                    Toast.makeText(
                            CharacterMake.this, "作成したキャラクターが最大数に達しました", Toast.LENGTH_SHORT).show();
                } else if (!name.isEmpty() && radioGroup.getCheckedRadioButtonId() != -1) {
                    RadioButton radio = findViewById(radioGroup.getCheckedRadioButtonId());
                    Player player = AllJob.makePlayer(name, AllJob.JobData.values()[radio.getId()]);
                    if (new OperationCharacterData(getApplicationContext())
                            .setCharacter(player, radio.getId(), getDate()) != -1) {
                        //キャラクター作成完了の場合
                        nowPlayerNum++;
                        Intent intent = new Intent(getApplication(), CharacterMakeConpletion.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    } else {
                        //名前エラー表示
                        Toast.makeText(
                                CharacterMake.this, "この名前はすでに存在しています", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showSelectjobItem() {
        RadioGroup radioGroup = findViewById(R.id.character_make_job_RadioGroup);
        for (int i = 0; i < AllJob.JobData.values().length; i++) {
            RadioButton radioBtn = new RadioButton(this);
            radioBtn.setText(AllJob.JobData.values()[i].getJobName());
            radioBtn.setTextSize(this.radioButtonSize);
            radioBtn.setId(i);
            radioGroup.addView(radioBtn);
        }
    }

    //現在時刻を取得する
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/dd hh:mm");
        return format.format(new Date());
    }

}