package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.name.battler.Player.AllJob;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

import static com.example.namebattler.CharacterMake.makePlayer;
import static com.name.battler.GameManager.enemyParty;
import static com.name.battler.GameManager.myParty;

public class BattleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);

        setAdapter(R.id.battleResult_gridView_top, myParty);
        setAdapter(R.id.battleResult_gridView_bottom, enemyParty);




        findViewById(R.id.battleResult_rematch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializePlayer();

                Intent intent = new Intent(getApplication(), BattleMain.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.battleResult_nextBattle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializePlayer();

                Intent intent = new Intent(getApplication(), BattleStart.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.battleResult_battleEnd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplication(), TopScreen.class);
                        startActivity(intent);
                    }
        });

    }

    public void initializePlayer(){

        remakePlayer(myParty);
        remakePlayer(enemyParty);


    }

    public void remakePlayer(Party party){

        for(int i = 0; i < party.getmenbers().size(); i++){
            Player player = party.getmenbers().get(i);
            player.makeCharacter();
        }
    }

    public void setAdapter(int layout, Party party){
        BaseAdapter adapter = new BaseAdapter_BattleMain(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }

}