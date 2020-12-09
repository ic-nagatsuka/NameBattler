package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.name.battler.Strategy.AllStrategy;

public class StrategyChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_change);

        RadioGroup radioGroup = findViewById(R.id.strategyChange_radioGroup);

        for(AllStrategy.Strategies str: AllStrategy.Strategies.values()){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(str.getStrategy().getName());
            radioButton.setTextSize(30);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layoutParams.setMargins(0,40,0,40);

            radioButton.setLayoutParams(layoutParams);

            radioGroup.addView(radioButton);
        }



    }
}