package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Strategy.AllStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleMain extends AppCompatActivity {

    Party party = CharacterOrganization.party;
    Party enemyParty = BattleStart.enemyParty;
    ArrayList<Party>allParty = new ArrayList<>();
    ArrayList<Player>allPlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);
        System.out.println("パーティー"+party);

        allParty.add(party);
        allParty.add(enemyParty);

        party.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());
        enemyParty.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());

        addAllPlayer();
        highSpeedSort(allPlayer);

        displayUpdateStates();



        TextView strategy = findViewById(R.id.battle_main_strategy_name);
        strategy.setText("作戦 ： " +
                party.getStrategy().getName()
        );


        findViewById(R.id.battle_main_nextTurn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                for(int i = allPlayer.size() -1; i >= 0; i--){
                    Player attacker = allPlayer.get(i);
                    Party defenseParty = selectParty(attacker);

                    if(attacker.getHP() != 0){

                        attacker.abnormalEffect(attacker);

                        System.out.println(attacker.getHP());
                        if(attacker.getHP() != 0){
                            attacker.getStrategy().action(attacker, defenseParty);
                        }

                    }


                    removeEmptyParty();

                    System.out.println("パーティー" + allParty.size());
//                    ゲームバトル終了判定
                    if(allParty.size() == 1){
                        break;
                    }
                }

                //バトル結果画面に移動
                if(allParty.size() == 1){
                    System.out.println("ゲーム終了" + allParty.size());
//                    Intent intent = new Intent(getApplication(), );
//                    startActivity(intent);
                }

            //1ターン分終了後ステータス更新
            displayUpdateStates();

            }
        });




    }

    public void displayUpdateStates(){
        makeAdapter(R.id.battle_main_gridView_top, party);
        makeAdapter(R.id.battle_main_gridView_bottom, enemyParty);
    }

    public void makeAdapter(int layout, Party party){
        BaseAdapter adapter = new BaseAdapter_BattleMain(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }
    

    public void addAllPlayer(){
        for(Party party: allParty){
            for(Player player: party.getmenbers()){
                allPlayer.add(player);
            }
        }

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

        //リストのパーティーからアタッカーにパーティーを除いて乱数で決めたい
    public Party selectParty(Player attacker){

        if(attacker.getParty() == party){
            return enemyParty;
        }else{
            return party;
        }

    }

    public void removeEmptyParty(){
        for(int i = 0; i < allParty.size(); i++){
            if(allParty.get(i).getmenbers().size() == 0){
                System.out.println("しゅうりょう" + allParty.size());

                allParty.remove(party);
                System.out.println("しゅうりょう" + allParty.size());


            }
        }
    }

}