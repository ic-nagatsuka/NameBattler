package com.namebattler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.adapter.CharacterSelectListAdapter;
import com.namebattler.battle.GameManager;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.OperationCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;

import java.util.ArrayList;
import java.util.List;

public class CharacterOrganization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_organization);

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "パーティー編成", TopScreen.class);

        if (GameManager.myParty.getAllMenbers().size() != 0) {
            GameManager.myParty = new Party("味方");
        }

        displayCharacterItem();

        findViewById(R.id.character_organization_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameManager.myParty.getAllMenbers().size() == Option.PARTY_PLAYER_NUM) {
                    Intent intent = new Intent(getApplication(), BattleStart.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void displayCharacterItem() {
        Cursor cursor = new OperationCharacterData(getApplicationContext()).getAllData();
        List<Status> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String job = AllJob.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getJobName();
                String status = makeStatusText(cursor);
                list.add(new Status(name, job, status));
                cursor.moveToNext();
            }
        }

        View startButtonView = findViewById(R.id.character_organization_start);
        BaseAdapter adapter = new CharacterSelectListAdapter(
                this,
                startButtonView,
                list
        );
        ListView listView = findViewById(R.id.character_organization_ListView);
        listView.setAdapter(adapter);
    }

    public String makeStatusText(Cursor cursor) {
        String statusText = "HP" + cursor.getString(cursor.getColumnIndex("HP")) +
                " MP" + cursor.getString(cursor.getColumnIndex("MP")) +
                " STR" + cursor.getString(cursor.getColumnIndex("STR")) +
                " DEF" + cursor.getString(cursor.getColumnIndex("DEF")) +
                " LUCK" + cursor.getString(cursor.getColumnIndex("LUCK")) +
                " AGI" + cursor.getString(cursor.getColumnIndex("AGI"));
        return statusText;
    }

    public class Status {

        String name;
        String job;
        String status;
        boolean isClicked;

        Status(String name, String job, String status) {
            this.name = name;
            this.job = job;
            this.status = status;
        }

        public String getName() {
            return this.name;
        }

        public String getJob() {
            return this.job;
        }

        public String getStatus() {
            return this.status;
        }

        public boolean getIsClicked() {
            return this.isClicked;
        }

        public void setIsClicked(boolean isClicked) {
            this.isClicked = isClicked;
        }

    }

}
