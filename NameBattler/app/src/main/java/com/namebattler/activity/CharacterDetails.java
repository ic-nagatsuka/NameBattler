package com.namebattler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.namebattler.R;
import com.namebattler.battle.player.AllJob;
import com.namebattler.database.OperationCharacterData;
import com.namebattler.fragment.TitleFragment;

public class CharacterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        TitleFragment.displayTitleFragment(
                getSupportFragmentManager(), "キャラ詳細", CharacterList.class);

        final Intent intent = getIntent();

        Cursor cursor = new OperationCharacterData(getApplicationContext())
                .getCharacter(intent.getStringExtra("name"));

        if (cursor.moveToFirst()) {
            TextView textView = findViewById(R.id.characterDetails_name);
            textView.setText(cursor.getString(cursor.getColumnIndex("NAME")));

            textView = findViewById(R.id.characterDetails_job);
            textView.setText(AllJob.values()[cursor.getInt(cursor.getColumnIndex("JOB"))].getName());

            textView = findViewById(R.id.characterDetails_hp);
            textView.setText(cursor.getString(cursor.getColumnIndex("HP")));

            textView = findViewById(R.id.characterDetails_mp);
            textView.setText(cursor.getString(cursor.getColumnIndex("MP")));

            textView = findViewById(R.id.characterDetails_str);
            textView.setText(cursor.getString(cursor.getColumnIndex("STR")));

            textView = findViewById(R.id.characterDetails_def);
            textView.setText(cursor.getString(cursor.getColumnIndex("DEF")));

            textView = findViewById(R.id.characterDetails_luck);
            textView.setText(cursor.getString(cursor.getColumnIndex("LUCK")));

            textView = findViewById(R.id.characterDetails_agi);
            textView.setText(cursor.getString(cursor.getColumnIndex("AGI")));

            textView = findViewById(R.id.characterDetails_MakeDay_data);
            textView.setText(cursor.getString(cursor.getColumnIndex("CREATE_AT")));
        }

        findViewById(R.id.characterDetails_DeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = intent.getStringExtra("name");

                new OperationCharacterData(getApplicationContext()).deleteCharacter(name);

                Intent intent = new Intent(getApplication(), CharacterList.class);
                startActivity(intent);
            }
        });

    }

}