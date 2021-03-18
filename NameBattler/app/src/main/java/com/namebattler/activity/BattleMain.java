package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.namebattler.adapter.BattleStatusAdapter;
import com.namebattler.R;
import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.GameManager;
import com.namebattler.battle.party.Party;
import com.namebattler.fragment.TitleFragment;

import static com.namebattler.battle.battlelog.BattleLog.getLogText;

public class BattleMain extends AppCompatActivity {

    GameManager gm = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "バトル", null);

        displayUpdateStates();

        TextView strategy = findViewById(R.id.battle_main_strategy_name);
        strategy.setText("作戦 ： " +
                GameManager.myParty.getStrategy().getName()
        );

        final TextView battleLog = findViewById(R.id.battle_main_battleLog);
        battleLog.setText(getLogText());
        battleLog.setMovementMethod(new ScrollingMovementMethod());

        //次のターン
        findViewById(R.id.battle_main_nextTurn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleLog.clear();

                gm.battle();
                //ステータス更新
                displayUpdateStates();
                //バトルログ更新
                battleLog.setText(BattleLog.getLogText());
                //バトル終了確認
                if (gm.battleEnd()) {
                    //バトルログの内容を削除
                    BattleLog.clear();
                    //バトル結果画面に遷移
                    Intent intent = new Intent(getApplication(), BattleResult.class);
                    startActivity(intent);
                }

            }
        });

        findViewById(R.id.battle_main_stratygy_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //作戦変更画面に遷移
                Intent intent = new Intent(getApplication(), StrategyChange.class);
                startActivity(intent);
            }
        });

    }

    //各パーティーデータの画面表示
    public void displayUpdateStates() {
        makeAdapter(R.id.battle_main_gridView_bottom, GameManager.myParty);
        makeAdapter(R.id.battle_main_gridView_top, GameManager.enemyParty);
    }

    //アダプターをセット
    public void makeAdapter(int layout, Party party){
        BaseAdapter adapter = new BattleStatusAdapter(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }

}