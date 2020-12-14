package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.name.battler.Strategy.AllStrategy;

import static com.name.battler.GameManager.myParty;

public class StrategyChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_change);

        RadioGroup radioGroup = findViewById(R.id.strategyChange_radioGroup);

        for(int i = 0; i < AllStrategy.Strategies.values().length; i++){
            AllStrategy.Strategies strategy = AllStrategy.Strategies.values()[i];
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(strategy.getStrategy().getName());
            radioButton.setTextSize(30);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layoutParams.setMargins(0,40,0,40);

            radioButton.setLayoutParams(layoutParams);

            radioGroup.addView(radioButton);
        }
        System.out.println(radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               //クリックしたラジオボタンのIDから作戦を選択するように変更する
                myParty.setStrategy(AllStrategy.Strategies.values()[i].getStrategy());
            }
        });

        findViewById(R.id.strategyChange_dicid).setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               Intent intent = new Intent(getApplication(), BattleMain.class);
               startActivity(intent);
           }
        });

    }
}