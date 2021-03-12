package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.namebattler.adapter.BaseAdapter_BattleMain;
import com.namebattler.R;
import com.namebattler.battle.player.Party;
import com.namebattler.battle.player.Player;

import com.namebattler.battle.GameManager;

public class BattleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);

        setAdapter(R.id.battleResult_gridView_bottom, GameManager.myParty);
        setAdapter(R.id.battleResult_gridView_top, GameManager.enemyParty);

        ImageView image = findViewById(R.id.battleResult_Image_result);

        if (GameManager.win == GameManager.myParty) {
            image.setImageResource(R.drawable.pose_win_boy);
        } else {
            image.setImageResource(R.drawable.pose_lose_boy);
        }


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

    public void initializePlayer() {

        remakePlayer(GameManager.myParty);
        remakePlayer(GameManager.enemyParty);


    }

    public void remakePlayer(Party party) {
        int partyNum = party.getmenbers().size();
        for (int i = 0; i < partyNum; i++) {
            Player player = party.getmenbers().get(0);

            party.appendPlayer(
                    GameManager.makePlayer(player.getName(), player.getJob(), party)
            );

            party.removePlayer(player);

        }
    }

    public void setAdapter(int layout, Party party) {
        BaseAdapter adapter = new BaseAdapter_BattleMain(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);

    }

}