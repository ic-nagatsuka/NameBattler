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
import com.name.battler.Player.Player;
import com.name.battler.Strategy.AllStrategy;

import java.util.ArrayList;
import java.util.List;

import static com.name.battler.BattleLog.BattleLog.logList;
import static com.name.battler.GameManager.enemyParty;
import static com.name.battler.GameManager.myParty;

public class BattleMain extends AppCompatActivity {

    GameManager gm = new GameManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);
        System.out.println("パーティー"+myParty);


        myParty.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());
        enemyParty.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());

//        addAllPlayer();
//        highSpeedSort(allPlayer);
//
        displayUpdateStates();



        TextView strategy = findViewById(R.id.battle_main_strategy_name);
        strategy.setText("作戦 ： " +
                myParty.getStrategy().getName()
        );

        final TextView text = findViewById(R.id.textView2);
        text.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.battle_main_nextTurn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(!gm.battle()){
                    System.out.println("しゅうりょう");

                    Intent intent = new Intent(getApplication(), BattleResult.class);
                    startActivity(intent);
                }
                //1ターン分終了後ステータス更新
                displayUpdateStates();


                text.setText(BattleLog.getLogText());

                logList = new ArrayList<>();
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

//    public boolean isEnd(){//falseのカウントが１だったらtrueを返す
//        boolean isEnd = true;
//        for(Party party: allParty){
//            isEnd = true;
//            for(Player player: party.getmenbers()){
//                if(player.getHP() != 0){
//                    isEnd = false;
//                }
//            }
//            if(isEnd == true){
//                return isEnd;
//            }
//        }
//
//        return isEnd;
//    }

    public void displayUpdateStates(){
        makeAdapter(R.id.battle_main_gridView_top, myParty);
        makeAdapter(R.id.battle_main_gridView_bottom, enemyParty);
    }

    public void makeAdapter(int layout, Party party){
        BaseAdapter adapter = new BaseAdapter_BattleMain(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }



    private void highSpeedSort(List<Player> playerList){

        for(int i = 0; i < playerList.size() - 1; i++){
            for(int j = 0; j < playerList.size() - 1; j++){
                if(playerList.get(j).getAGI() > playerList.get(j+1).getAGI()){
                    //場所を入れ替える
                    Player saveValue = playerList.get(j);
                    playerList.set(j, playerList.get(j + 1));
                    playerList.set(j + 1, saveValue);
                }
            }
        }
    }


    public Party selectParty(Player attacker){

        if(attacker.getParty() == myParty){
            return enemyParty;
        }else{
            return myParty;
        }

    }

//    public void removeEmptyParty(){
//        for(int i = 0; i < allParty.size(); i++){
//            if(allParty.get(i).getmenbers().size() == 0){
//                System.out.println("しゅうりょう" + allParty.size());
//
//                allParty.remove(myParty);
//                System.out.println("しゅうりょう" + allParty.size());
//
//
//            }
//        }
//    }

}