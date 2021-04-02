package com.namebattler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.namebattler.adapter.CharacterSelectListAdapter;
import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.battle.party.Party;

import java.util.ArrayList;
import java.util.List;

import com.namebattler.battle.GameManager;
import com.namebattler.database.GetCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;

public class CharacterOrganization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_organization);

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "パーティー編成", TopScreen.class);

        View startButtonView = findViewById(R.id.character_organization_start);

        if (GameManager.myParty.getmenbers().size() != 0) {
            GameManager.myParty = new Party("味方");
        }

        Cursor cursor = new GetCharacterData(getApplicationContext()).getAllData();

        List<Status> list = new ArrayList<>();

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name = cursor.getString(0);
            String job = AllJob.values()[cursor.getInt(1)].getName();
            String status = makeStatusText(cursor);
            list.add(new Status(name, job, status));
            cursor.moveToNext();
        }


        BaseAdapter adapter = new CharacterSelectListAdapter(
                this,
                startButtonView,
                list

        );

        ListView listView = findViewById(R.id.character_organization_ListView);
        listView.setAdapter(adapter);


        findViewById(R.id.character_organization_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameManager.myParty.getmenbers().size() == Option.partyPlayerNum) {
                    Intent intent = new Intent(getApplication(), BattleStart.class);
                    startActivity(intent);
                }
            }
        });

    }

    public String makeStatusText(Cursor cursor) {

        String statusText = "HP" + cursor.getString(2) +
                " MP" + cursor.getString(3) +
                " STR" + cursor.getString(4) +
                " DEF" + cursor.getString(5) +
                " LUCK" + cursor.getString(6) +
                " AGI" + cursor.getString(7);
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

        public boolean getIsClick(){
            return this.isClicked;
        }

        public void setClick(boolean isClicked){
            this.isClicked = isClicked;
        }

    }
}

