package com.namebattler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.GameManager;
import com.namebattler.battle.strategy.AllStrategy;
import com.namebattler.fragment.TitleFragment;

public class StrategyChange extends AppCompatActivity {

    private final int radioSize = 30;
    private final int radioMarginVertical = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_change);

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "作戦", null);

        RadioGroup radioGroup = findViewById(R.id.strategyChange_radioGroup);
        showSelectStrategy(radioGroup);

        //ラジオボタン
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //クリックしたラジオボタンのIDから作戦を選択するように変更する
                GameManager.myParty.setStrategyKey(i);
            }
        });

        //決定ボタン
        findViewById(R.id.strategyChange_dicide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //バトルメイン画面に遷移
                Intent intent = new Intent(getApplication(), BattleMain.class);
                startActivity(intent);
            }
        });

    }

    private void showSelectStrategy(RadioGroup radioGroup) {
        //職業一覧を表示
        for (int i = 0; i < AllStrategy.EStrategy.values().length; i++) {
            AllStrategy.EStrategy strategy = AllStrategy.EStrategy.values()[i];
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(strategy.getName());
            radioButton.setTextSize(this.radioSize);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, this.radioMarginVertical, 0, this.radioMarginVertical);
            radioButton.setLayoutParams(layoutParams);
            radioGroup.addView(radioButton);
        }
    }
}