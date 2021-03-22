package com.namebattler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.GetCharacterData;
import com.namebattler.fragment.TitleFragment;
import com.namebattler.option.Option;

public class CharacterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        TitleFragment.displayTitleFragment(getSupportFragmentManager(), "キャラ詳細", CharacterList.class);

        final Intent intent = getIntent();

        Cursor cursor = new GetCharacterData(getApplicationContext())
                .getCharacter(intent.getStringExtra("name"));

        if (cursor.moveToFirst()) {
            TextView text;

            text = findViewById(R.id.characterDetails_name);
            text.setText(cursor.getString(0));

            text = findViewById(R.id.characterDetails_job);
            text.setText(AllJob.Job.values()[cursor.getInt(1)].getName());

            text = findViewById(R.id.characterDetails_hp);
            text.setText(cursor.getString(2));

            text = findViewById(R.id.characterDetails_mp);
            text.setText(cursor.getString(3));

            text = findViewById(R.id.characterDetails_str);
            text.setText(cursor.getString(4));

            text = findViewById(R.id.characterDetails_def);
            text.setText(cursor.getString(5));

            text = findViewById(R.id.characterDetails_luck);
            text.setText(cursor.getString(6));

            text = findViewById(R.id.characterDetails_agi);
            text.setText(cursor.getString(7));

            text = findViewById(R.id.characterDetails_MakeDay);
            text.setText("作成日 : " + cursor.getString(8));
        }


        findViewById(R.id.characterDetails_DeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = intent.getStringExtra("name");

                new GetCharacterData(getApplicationContext()).deleteCharacter(name);

                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });

    }

}