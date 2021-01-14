package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.name.battler.BattleLog.BattleLog;
import com.name.battler.GameManager;
import com.name.battler.Player.Party;

import java.util.ArrayList;

import static com.name.battler.BattleLog.BattleLog.getLogText;
import static com.name.battler.BattleLog.BattleLog.logList;
import static com.name.battler.GameManager.enemyParty;
import static com.name.battler.GameManager.myParty;

public class BattleMain extends AppCompatActivity {

    GameManager gm = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        displayUpdateStates();

        TextView strategy = findViewById(R.id.battle_main_strategy_name);
        strategy.setText("作戦 ： " +
                myParty.getStrategy().getName()
        );

        final TextView battleLog = findViewById(R.id.battle_main_battleLog);
        battleLog.setText(getLogText());
        battleLog.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.battle_main_nextTurn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BattleLog.clear();

                gm.battle();
                //1ターン分終了後ステータス更新
                displayUpdateStates();

                battleLog.setText(BattleLog.getLogText());

                if(gm.battleEnd()){
                    BattleLog.clear();
                    Intent intent = new Intent(getApplication(), BattleResult.class);
                    startActivity(intent);
                }

            }
        });

        findViewById(R.id.battle_main_stratygy_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), StrategyChange.class);
                startActivity(intent);
            }
        });

    }

    public void displayUpdateStates(){
        makeAdapter(R.id.battle_main_gridView_bottom, myParty);
        makeAdapter(R.id.battle_main_gridView_top, enemyParty);
    }

    public void makeAdapter(int layout, Party party){
        BaseAdapter adapter = new BaseAdapter_BattleMain(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }

}