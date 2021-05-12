package com.namebattler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.adapter.BattleStatusAdapter;
import com.namebattler.battle.GameManager;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.fragment.TitleFragment;

public class BattleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "結果", null);

        setAdapter(R.id.battleResult_gridView_bottom, GameManager.myParty);
        setAdapter(R.id.battleResult_gridView_top, GameManager.enemyParty);

        ImageView resultImage = findViewById(R.id.battleResult_Image_result);

        if (GameManager.win == GameManager.myParty) {
            resultImage.setImageResource(R.drawable.pose_win_boy);
        } else {
            resultImage.setImageResource(R.drawable.pose_lose_boy);
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
        int partyNum = party.getAllMenbers().size();
        for (int i = 0; i < partyNum; i++) {
            Player player = party.getAllMenbers().get(0);

            party.appendPlayer(
                    GameManager.makePlayer(player.getName(), player.getJob(), party)
            );
            party.removePlayer(player);
        }
    }

    public void setAdapter(int layout, Party party) {
        BaseAdapter adapter = new BattleStatusAdapter(this, party);

        GridView gridView = findViewById(layout);
        gridView.setAdapter(adapter);
    }

}